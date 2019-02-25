package com.piegroup.zzbm.Enums;

import com.piegroup.zzbm.BS.Bg.Interfaces.CodeIF;

public enum MessageStyleEnum implements CodeIF {

    SMSNOTICE("480", "短信通知"),
    SIGNUP_SMSCODENOTICE("481", "注册验证码"),
    RESETPASSWORD_SMSCODENOTICE("482","重置密码验证码"),
    PUSHNOTICE("483", "推送通知")//使用个推作为推送通知
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

    MessageStyleEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
