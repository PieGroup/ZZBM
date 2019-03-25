package com.piegroup.zzbm.Enums;
/**
*@ClassName     CertificateEnum //认证状态
*@Description   TODO
*@Author        DDLD
*@Date          2019/3/25 19:19
*@ModifyDate    2019/3/25 19:19
*@Version       1.0
*/
public enum  CertificateEnum {

    With_Cer("700000","等待验证"),
    Success_Cer("700001","验证成功"),
    Error_Cer("700002","验证失败")

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

    CertificateEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
