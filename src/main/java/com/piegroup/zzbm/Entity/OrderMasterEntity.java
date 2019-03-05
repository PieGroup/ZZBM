package com.piegroup.zzbm.Entity;

import lombok.Data;

import java.io.Serializable;

//订单实体类，只写了部分属性
@Data
public class OrderMasterEntity implements Serializable {


    private static final long serialVersionUID = 76488831495869328L;

    private String order_master_id;//订单id
    private String order_master_userid;   //用户id
    private String order_master_time;  //下单时间
    private String order_master_money;  //下单金额
    private String order_master_piad_type; //下单方式，直接方式
    private int    order_master_statusid; //订单状态
    private int    order_master_pay_statusid; ///支付方式
    private String order_master_create_time; //创建时间
    private String order_master_update_time; //更新时间
    private String order_master_buyer_openid; //微信openid

}
