package com.piegroup.zzbm.Enums;

import com.piegroup.zzbm.BS.Bg.Interfaces.CodeIF;

public enum OrderStatusEnum implements CodeIF {
    ORDERSTATE_DEFAULT("300","错误"),
    ORDERSTATE_UNPAY("301", "未支付"),
    ORDERSTATE_READY("302", "正在配货"),
    ORDERSTATE_SHIPPED("303", "待收货"),
    ORDERSTATE_ARRIVED("304", "已收货"),
    ORDERSTATE_SUCCESS("305", "交易成功"),
    ORDERSTATE_FAIL("306", "交易失败"),
    ORDERSTATE_CANCEL("307", "取消");

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
