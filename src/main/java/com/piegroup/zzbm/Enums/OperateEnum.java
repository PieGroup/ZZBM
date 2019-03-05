package com.piegroup.zzbm.Enums;

import com.piegroup.zzbm.BS.Bg.Interfaces.CodeIF;

public enum OperateEnum  {

    Delete_Operate("300000","删除"),
    Update_Operate("300001","更新"),
    Insert_Operate("300002","添加");


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
