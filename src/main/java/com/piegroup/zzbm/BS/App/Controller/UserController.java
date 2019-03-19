package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.Annotation.Authorization;
import com.piegroup.zzbm.Annotation.CurrentUser;
import com.piegroup.zzbm.BS.App.Service.Impl.UserServiceImpl;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.DataVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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


       return ResultUtil.success(userEntity);
    }


    @RequestMapping(method = RequestMethod.GET,value = "/issue")
    @ResponseBody
    @Authorization
    public DataVO issue(@CurrentUser UserEntity userEntity,@RequestParam(value = "type",defaultValue = "questions") String type,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum ){
        Assert.notNull(type,"类型不能为空");
        log.info("查询的用户id："+userEntity.getUser_Id());
        return  ResultUtil.success( userService.issue(userEntity.getUser_Id(),type,pageSize,pageNum));
    }

    //编辑用户资料

    @RequestMapping(method = RequestMethod.POST ,value = "/editUser")
    @ResponseBody
    @Authorization
    public DataVO editUser(@CurrentUser UserEntity userEntity, UserEntity editUser){

        log.info("编辑用户id"+userEntity.getUser_Id());

        return ResultUtil.success(userService.editUser(userEntity,editUser));


    }





}
