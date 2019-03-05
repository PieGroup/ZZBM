package com.piegroup.zzbm.Enums;

import com.piegroup.zzbm.BS.Bg.Interfaces.CodeIF;

public enum MessageEnum  {

    Sms_Message("200000", "短信通知"),
    Sign_Up_Sms_Code_Message("200001", "注册验证码"),
    Reset_Password_Sms_Code_Message("200002","重置密码验证码"),
    Push_Message("200003", "推送通知")//使用个推作为推送通知
    ;


    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    MessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
