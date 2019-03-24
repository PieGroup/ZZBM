package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.Annotation.Authorization;
import com.piegroup.zzbm.Annotation.CurrentUser;
import com.piegroup.zzbm.BS.App.Service.Impl.UserServiceImpl;
import com.piegroup.zzbm.DTO.UserLabelDTO;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
*@ClassName     UserController
*@Description   TODO 获取用户信息
*@Author        DDLD
*@Date          2019/3/17 11:29
*@ModifyDate    2019/3/17 11:29
*@Version       1.0
*/
@Slf4j
@Controller
@RequestMapping("/mine")
@CrossOrigin
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(method = RequestMethod.POST )
    @ResponseBody
    @Authorization
    @ApiOperation("用户查看自己")
    public DataVO mine(@CurrentUser UserEntity userEntity){
        log.info("获取到用户id:"+userEntity.getUser_Id());
        DataPageSubc dataPageSubc = new DataPageSubc();
        dataPageSubc.setData(userEntity);
       return ResultUtil.success(dataPageSubc);
    }


    @RequestMapping(method = RequestMethod.GET,value = "/issue")
    @ResponseBody
    @Authorization
    public DataVO issue(@CurrentUser UserEntity userEntity,@RequestParam(value = "type",defaultValue = "questions") String type,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum ){
        Assert.notNull(type,"类型不能为空");
        log.info("查询的用户id："+userEntity.getUser_Id());
        return  ResultUtil.success( userService.issue(userEntity.getUser_Id(),type,pageSize,pageNum));
    }

    //

    @RequestMapping(method = RequestMethod.POST ,value = "/editUser")
    @ResponseBody
    @Authorization
    public DataVO editUser(@CurrentUser UserEntity userEntity, UserEntity editUser){

        log.info("编辑用户id"+userEntity.getUser_Id());

        return ResultUtil.success(userService.editUser(userEntity,editUser));


    }

    //用户感兴趣的标签
    @RequestMapping(method = RequestMethod.POST,value = "/setuserlabel")
    @ResponseBody
    @ApiOperation("用户感兴趣的标签选择")
    @Authorization
    public DataVO SetUserLabel(@CurrentUser UserEntity userEntity, UserLabelDTO userLabelDTO,@ApiParam("0 为设置 1 为修改") @RequestParam(value = "type",defaultValue = "0") int type){

        return ResultUtil.success(new DataPageSubc<>(),userService.SetUserLabel(userEntity.getUser_Id(),userLabelDTO,type));

    }

    //我的钱包
    @RequestMapping(method = RequestMethod.GET,value = "/wallet")
    @ResponseBody
    @Authorization
    public DataVO wallet(@CurrentUser UserEntity userEntity){
        if (userEntity == null){
            return ResultUtil.error(new DataPageSubc<>(), ExceptionEnum.No_Login_Exception);
        }
        return ResultUtil.success(userService.wallet(userEntity.getUser_Id()));
    }

    //app请求认证接口
    @RequestMapping(method = RequestMethod.POST,value = "/certification")
    @ResponseBody
    @Authorization
    public DataVO certification(@CurrentUser UserEntity userEntity, HttpServletRequest request){

        if (userEntity == null){
            return ResultUtil.error(new DataPageSubc<>(), ExceptionEnum.No_Login_Exception);
        }
        return ResultUtil.success(userService.certification(userEntity.getUser_Id(),request));
    }

    @RequestMapping(method = RequestMethod.POST,value = "/detail")
    @ResponseBody
    @Authorization
    public DataVO detail(@CurrentUser UserEntity userEntity){
        if (userEntity == null){
            return ResultUtil.error(new DataPageSubc<>(), ExceptionEnum.No_Login_Exception);
        }
        return ResultUtil.success(userService.detail(userEntity));

    }




}
