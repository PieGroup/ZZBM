package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.Annotation.Authorization;
import com.piegroup.zzbm.Annotation.CurrentUser;
import com.piegroup.zzbm.BS.App.Service.Impl.UserServiceImpl;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.VO.DataVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public DataVO mine(@CurrentUser UserEntity userEntity){
        log.info("获取到用户id:"+userEntity.getUser_Id());
       return null ;
    }

}
