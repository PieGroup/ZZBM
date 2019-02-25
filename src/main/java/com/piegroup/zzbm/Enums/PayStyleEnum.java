package com.piegroup.zzbm.Enums;


import com.piegroup.zzbm.BS.Bg.Interfaces.CodeIF;

/**
 * 支付方式枚举
 */
public enum PayStyleEnum implements CodeIF {

    PAYSTYLE_ALIPAY_APP("450","支付宝app"),
    PAYSTYLE_ALIPAY_PC("451","支付宝pc"),
    PAYSTYLE_WCPAY_H5("452","微信公众账号支付");


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
