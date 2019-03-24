package com.piegroup.zzbm.Enums;

import com.piegroup.zzbm.BS.Bg.Interfaces.CodeIF;

public enum OrderStatusEnum  {
    Default_OrderState("400000","错误"),
    Unpaid_OrderState("400001", "未支付"),
    Success_OrderState("400002", "交易成功"),
    Fail_OrderState("400003", "交易失败"),
    Cancel_OrderState("400004", "取消订单");

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

    OrderStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
