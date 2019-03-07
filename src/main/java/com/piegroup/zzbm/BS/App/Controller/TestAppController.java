package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.TimeUtil;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Login")
@CrossOrigin
@Slf4j
public class TestAppController {

    @RequestMapping("")
    @ResponseBody
    public String login(@Param("user")String user, @Param("password")String pwd, HttpServletRequest request){
        String token= TimeUtil.TimestampNow()+ RandomNumberUtil.createRandom(true,5);
        request.getSession().getServletContext().setAttribute("user",token);

        return token+"--for user"+user;

    }

}
