package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.Annotation.Authorization;
import com.piegroup.zzbm.Annotation.CurrentUser;
import com.piegroup.zzbm.BS.App.Service.Impl.UserServiceImpl;
import com.piegroup.zzbm.BS.App.TokenManager.RedisTokenManager;
import com.piegroup.zzbm.Configs.TokenConfig;
import com.piegroup.zzbm.DTO.TokenDTO;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.adapter.HttpWebHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取和删除token的请求地址，在Restful设计中其实就对应着登录和退出登录的资源映射
 * @author ScienJus
 * @date 2015/7/30.
 */
@RestController
@RequestMapping("/tokens")
@Slf4j
public class TokenController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RedisTokenManager tokenManager;

    @RequestMapping(method = RequestMethod.POST)
//    @ApiOperation(value = "登录")
    public DataVO login(@RequestParam String phone, @RequestParam String password,HttpServletResponse response) {
        Assert.notNull(phone, "username can not be empty");
        Assert.notNull(password, "password can not be empty");
        Map map = new HashMap();
        DataPageSubc dataPageSubc = new DataPageSubc();

        log.info("打印用户：手机"+phone+"密码："+password);

        UserEntity userEntity = userService.queryByUserPhone(phone);
        if (userEntity == null){
            //去注册
            return ResultUtil.error(null,ExceptionEnum.No_Register_Exception);
        }

        if (!userEntity.getUser_Password().equals(password)) {  //密码错误
            //提示用户名或密码错误
            return ResultUtil.error(null, ExceptionEnum.Login_Password_Error_Exception);
        }
        //生成一个token，保存用户登录状态
        TokenDTO tokenDTO = tokenManager.createToken(userEntity.getUser_Id());

        response.setHeader(TokenConfig.TOKEN,tokenDTO.getToken());
        map.put("user",userEntity);
        map.put("token",tokenDTO.getToken());
        dataPageSubc.setData(map);
        return  ResultUtil.success(dataPageSubc);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Authorization
//    @ApiOperation(value = "退出登录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
//    })
    public DataVO logout(@CurrentUser UserEntity userEntity,HttpServletResponse response) {
        System.out.println("退出登录");
        tokenManager.deleteToken(userEntity.getUser_Id());
        response.setHeader(TokenConfig.TOKEN,"");
        return ResultUtil.success();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/test")
//    @ApiOperation(value = "测试接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
//    })
    @Authorization
    public String test (@CurrentUser UserEntity userEntity){
        System.out.println("controller:"+userEntity.toString());
        return "ok";
    }
    @RequestMapping(method = RequestMethod.GET,value = "/noLogin")
    @ResponseBody
    public DataVO noLogin(){
        return ResultUtil.error(null,ExceptionEnum.No_Login_Exception);
    }

}
