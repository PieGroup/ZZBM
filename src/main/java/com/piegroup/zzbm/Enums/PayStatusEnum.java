package com.piegroup.zzbm.Enums;


import com.piegroup.zzbm.BS.Bg.Interfaces.CodeIF;

//支付状态
public enum  PayStatusEnum  {

    Pay_Wait_PayStatus("500000","等待支付"),
    Pay_Success_PayStatus("500001","支付成功");

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

    PayStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
