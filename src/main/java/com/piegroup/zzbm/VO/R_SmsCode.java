package com.piegroup.zzbm.VO;

public class R_SmsCode {
    private String phone;
    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "R_code{" +
                "phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
