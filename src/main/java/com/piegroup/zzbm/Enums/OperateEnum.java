package com.piegroup.zzbm.Enums;

import com.piegroup.zzbm.BS.Bg.Interfaces.CodeIF;

public enum OperateEnum implements CodeIF {

    DELETE("11","删除"),
    UPDATE("12","更新"),
    INSERT("13","添加");


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

    OperateEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
