package com.piegroup.zzbm.Enums;


import com.piegroup.zzbm.BS.Bg.Interfaces.CodeIF;

public enum ExceptionEnum implements CodeIF {

    NoAuthority_Exception("100","没有该权限"),
    NoLogin_Exception("101","没有登录"),
    UNKNOW_Exception("-1","未知错误"),
    DATALINK_Exception("500","数据库连接错误"),
    SUCCESS("200","成功！"),
    LOCK_FALSE("501","加锁失败！"),
    TRANSFORM_ENUM_ERROR("502","枚举转换出现问题！"),
    PAY_ERROR("503","支付失败")
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

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
