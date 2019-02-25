package com.piegroup.zzbm.BS.Bg.UnitIF;


import com.piegroup.zzbm.Annotation.Message;
import com.piegroup.zzbm.BS.Bg.Abstracts.MessageAS;
import com.piegroup.zzbm.Enums.MessageStyleEnum;


//短信验证码通知
@Message(MESSAGE_ENUM = MessageStyleEnum.SIGNUP_SMSCODENOTICE)        // 注册验证码
public class SMSCodeSignUpUnit extends MessageAS {


    public boolean SmsCodeMessage(String phone) {

        System.out.println(phone+"发送注册短信验证码成功。");
        return true;
//        return SMSCodeUtils.SendCode(phone,session,accessId,accessId,accessKeySecre,SignName,SMSTemplateCode);

    }


}
