package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.Annotation.NoRepeatSubmit;
import com.piegroup.zzbm.DTO.UserLabelDTO;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.SMSCodeUtil;
import com.piegroup.zzbm.Utils.TimeUtil2;
import com.piegroup.zzbm.VO.DataVO;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(method = RequestMethod.GET,value = "/test")
    @ResponseBody
    @NoRepeatSubmit
    public Map testSms(@RequestBody UserLabelDTO labelDTO){
        String phone = "123456";
        String code = "123456";
//        smsCodeUtil.saveCode(phone,code);
//
//        try {
//            response.sendRedirect("/tokens/LBC?phone="+phone+"&code="+code);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(labelDTO);
        List list = new ArrayList<>();

        list.add("123");
        list.add("78978465");

        Map map = new HashMap();
        map.put("label_id",list);

       return map;

    }

}
