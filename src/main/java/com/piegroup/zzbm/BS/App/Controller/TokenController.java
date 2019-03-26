package com.piegroup.zzbm.BS.App.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.piegroup.zzbm.Annotation.Authorization;
import com.piegroup.zzbm.Annotation.CurrentUser;
import com.piegroup.zzbm.Annotation.NoRepeatSubmit;
import com.piegroup.zzbm.BS.App.Service.Adapter.MessageAt;
import com.piegroup.zzbm.BS.App.Service.Impl.UserServiceImpl;
import com.piegroup.zzbm.BS.App.Service.Units.SmsCodeUnit;
import com.piegroup.zzbm.BS.App.TokenManager.RedisTokenManager;
import com.piegroup.zzbm.BS.Bg.Exceptions.Exceptions;
import com.piegroup.zzbm.Configs.Constants;
import com.piegroup.zzbm.DTO.TokenDTO;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.Utils.SMSCodeUtil;
import com.piegroup.zzbm.Utils.SpringUtil;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.experimental.PackagePrivate;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.adapter.HttpWebHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PATCH;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取和删除token的请求地址，在Restful设计中其实就对应着登录和退出登录的资源映射
 *
 * @author ScienJus
 * @date 2015/7/30.
 */
@Controller
@RequestMapping("/tokens")
@Slf4j
@CrossOrigin
public class TokenController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RedisTokenManager tokenManager;

    @Autowired
    private MessageAt messageAt;

    @Autowired
    SmsCodeUnit smsCodeUnit;


    //通过密码登录
    @RequestMapping(method = RequestMethod.POST, value = "/LBP")
    @ResponseBody
    @ApiOperation(value = "密码登录")
    public DataVO loginByPassword(@ApiParam("手机号") @RequestParam String phone, @ApiParam("密码") @RequestParam String password, HttpServletResponse response) {
        Assert.notNull(phone, "username can not be empty");
        Assert.notNull(password, "password can not be empty");
        Map map = new HashMap();
        DataPageSubc dataPageSubc = new DataPageSubc();

        log.info("打印用户：手机" + phone + "密码：" + password);

        UserEntity userEntity = userService.queryByUserPhone(phone);
        if (userEntity == null) {
            //去注册
            return ResultUtil.error("没有注册过", ExceptionEnum.No_Register_Exception);
        }

        if (!userEntity.getUser_Password().equals(password)) {  //密码错误
            //提示用户名或密码错误
            return ResultUtil.error("密码错误", ExceptionEnum.Login_Password_Error_Exception);
        }
        //生成一个token，保存用户登录状态
        TokenDTO tokenDTO = tokenManager.createToken(userEntity.getUser_Id());

        response.setHeader(Constants.TOKEN, tokenDTO.getToken());
        map.put("user", userEntity);
        map.put("token", tokenDTO.getToken());
        dataPageSubc.setData(map);
        return ResultUtil.success(dataPageSubc);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/LBC")
    @ResponseBody
    @ApiOperation("验证码登录 || 注册")
    @NoRepeatSubmit
    public DataVO LoginSignUpByCode(@ApiParam("手机号") @RequestParam String phone, @ApiParam("验证码") @RequestParam String code, HttpServletResponse response) {
        Assert.notNull(phone, "phone can not be empty");
        Assert.notNull(code, "phone can not be empty");
        log.info("用户手机号：" + phone + "--验证码：" + code);

//        phone = "123456";
//        code = "123456";

        DataPageSubc dataPageSubc = new DataPageSubc();
        Map map = new HashMap();

        //验证验证码
        if (smsCodeUnit==null){
            System.out.println("123456");
        }
        ExceptionEnum exceptionEnum = smsCodeUnit.check(phone, code);
        if (exceptionEnum == ExceptionEnum.Success) {

            UserEntity userEntity = userService.queryByUserPhone(phone);

            //如果该用户没有注册过，直接完成注册
            if (userEntity == null) {
                //去注册
                log.info("没有注册过，现在开始注册。");
                map = userService.addUser(phone);
                log.info("注册成功！");
                userEntity = (UserEntity) map.get("user");

            }

            //生成一个token，保存用户登录状态
            TokenDTO tokenDTO = tokenManager.createToken(userEntity.getUser_Id());

            map = userService.queryByUserId(userEntity.getUser_Id());

            response.setHeader(Constants.CURRENT_USER_ID, tokenDTO.getToken());


            map.put("token", tokenDTO.getToken());
            dataPageSubc.setData(map);
            return ResultUtil.success(dataPageSubc);
        }
        return ResultUtil.error(new DataPageSubc<>(), exceptionEnum);
    }
    //通过验证码登录

    @RequestMapping(method = RequestMethod.POST)
    @Authorization
    @ApiOperation(value = "退出登录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
//    })
    public DataVO logout(@CurrentUser UserEntity userEntity, HttpServletResponse response) {
        System.out.println("退出登录");
        tokenManager.deleteToken(userEntity.getUser_Id());
        response.setHeader(Constants.TOKEN, "");
        return ResultUtil.success(new DataPageSubc<>(), ExceptionEnum.No_Login_Exception);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/test")
//    @ApiOperation(value = "测试接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
//    })
    @Authorization
    public String test(@CurrentUser UserEntity userEntity) {
        System.out.println("controller:" + userEntity.toString());
        return "ok";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/noLogin")
    @ResponseBody
    @ApiOperation("没有登录")
    public DataVO noLogin() {
        return ResultUtil.error(new DataPageSubc<>(), ExceptionEnum.No_Login_Exception);
    }


    //微信登录验证 请求次数不要重复 （2秒）
    @RequestMapping(value = "/jscode2session", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("微信登录")
    @NoRepeatSubmit
    public DataVO jscode2session(@RequestParam("code")String code , HttpServletResponse response,HttpServletRequest request) {


        System.out.println("请求的code::"+code);
        Map map = userService.WcLogin(code);
        Map map1 = new HashMap();
        DataPageSubc dataPageSubc = new DataPageSubc();
        if (map.get("entity") != null) {
            //生成一个token，保存用户登录状态
            UserEntity userEntity = (UserEntity) map.get("entity");
            TokenDTO tokenDTO = tokenManager.createToken(userEntity.getUser_Id());

            response.setHeader(Constants.CURRENT_USER_ID, tokenDTO.getToken());

            map1.put("token", tokenDTO.getToken());

            dataPageSubc.setData(map1);

            return ResultUtil.success(dataPageSubc);
        }
        return ResultUtil.error(dataPageSubc,ExceptionEnum.Wc_Login_Exception);
    }

    @PostMapping("/WcUser/Set")
    @ResponseBody
    @ApiOperation("微信用户完善")
    @Authorization
    public DataVO setWcUserInfo(@CurrentUser UserEntity userEntity,
                                @RequestParam("userhead") String userhead,
                                @RequestParam("loginname") String loginname){
        if (userhead == null || loginname == null || userhead.equals("")|| loginname.equals("") ){
            throw new Exceptions(ExceptionEnum.Param_Exception);
        }
        if (userEntity == null){
            throw new Exceptions(ExceptionEnum.No_Login_Exception);
        }
        userService.setWcUserInfo(userEntity,userhead,loginname);

        return ResultUtil.success(new DataPageSubc<>());
    }


}
