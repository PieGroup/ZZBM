package com.piegroup.zzbm.BS.Bg.Interfaces;


import com.piegroup.zzbm.Enums.OrderStatusEnum;

public interface OrderIF {
    //需要知道订单id才能惊醒修改,然后传一个order状态
    boolean ModifyOrderState(String orderId, OrderStatusEnum orderStatusEnum);


}
