package com.piegroup.zzbm.Enums;


import com.piegroup.zzbm.BS.Bg.Interfaces.CodeIF;
import lombok.Data;


public enum ExceptionEnum  {

    No_Permission_Exception("1000000","没有该权限"),
    No_Login_Exception("1000001","没有登录"),
    Unknown_Exception("-1","未知错误"),
    Data_Link_Exception("100002","数据库连接错误"),
    Success("0","成功！"),
    Lock_False_Exception("100003","加锁失败！"),
    Transform_Enum_Exception("100004","枚举转换出现问题！"),
    Pay_Error_Exception("100005","支付失败")
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
