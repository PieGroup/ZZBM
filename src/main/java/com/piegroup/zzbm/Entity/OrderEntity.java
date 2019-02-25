package com.piegroup.zzbm.Entity;

import lombok.Data;

import java.io.Serializable;

//订单实体类，只写了部分属性
@Data
public class OrderEntity implements Serializable {


//    private static final long serialVersionUID = 76488831495869328L;

    private String OrderId;//订单id
    private String name;   //商品名
    private String uid;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return uid;
    }

    public void setUserid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "OrderId='" + OrderId + '\'' +
                ", name='" + name + '\'' +
                ", userid='" + uid + '\'' +
                '}';
    }
}
