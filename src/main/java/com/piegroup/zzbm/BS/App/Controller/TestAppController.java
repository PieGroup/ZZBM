package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.Annotation.NoRepeatSubmit;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.SMSCodeUtil;
import com.piegroup.zzbm.Utils.TimeUtil2;
import com.piegroup.zzbm.VO.DataVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("")
@CrossOrigin
@Slf4j
public class TestAppController {


    @Autowired
    SMSCodeUtil smsCodeUtil;

    @RequestMapping("")
    @ResponseBody
    public String login(@Param("user")String user, @Param("password")String pwd, HttpServletRequest request){
        String token= TimeUtil2.TimestampNow()+ RandomNumberUtil.createRandom(true,5);
        request.getSession().getServletContext().setAttribute("user",token);

        return token+"--for user"+user;

    }

    @RequestMapping(method = RequestMethod.GET,value = "/testSms")
    @ResponseBody
    @NoRepeatSubmit
    public String testSms(HttpServletResponse response){
        String phone = "123456";
        String code = "123456";
//        smsCodeUtil.saveCode(phone,code);
//
//        try {
//            response.sendRedirect("/tokens/LBC?phone="+phone+"&code="+code);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



        return "你好";

    }

}
