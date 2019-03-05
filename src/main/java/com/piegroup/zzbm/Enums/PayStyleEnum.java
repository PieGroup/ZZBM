package com.piegroup.zzbm.Enums;


import com.piegroup.zzbm.BS.Bg.Interfaces.CodeIF;

/**
 * 支付方式枚举
 */
public enum PayStyleEnum  {

    ALIPay_App_PayStyle("600000","支付宝app"),
    ALIPay_PC_PayStyle("600001","支付宝pc"),
    WCPay_H5_PayStyle("600002","微信公众账号支付");


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

    PayStyleEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
