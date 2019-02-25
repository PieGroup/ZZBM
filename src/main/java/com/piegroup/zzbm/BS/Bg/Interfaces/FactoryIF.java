package com.piegroup.zzbm.BS.Bg.Interfaces;

import com.piegroup.zzbm.BS.Bg.Abstracts.MessageAS;
import com.piegroup.zzbm.BS.Bg.Abstracts.OrderAS;
import com.piegroup.zzbm.BS.Bg.Abstracts.PayAS;
import com.piegroup.zzbm.Enums.MessageStyleEnum;
import com.piegroup.zzbm.Enums.OrderStatusEnum;
import com.piegroup.zzbm.Enums.PayStyleEnum;


//工厂管理接口
public interface FactoryIF {

    //订单状态工厂
    public OrderAS CreateModifyOrderStatusIF(OrderStatusEnum orderStatusEnum);

    //支付方式工厂
    public PayAS CreatePayStyleIF(PayStyleEnum payStyleEnum);

    //消息工厂
    public MessageAS CreateMessageIF(MessageStyleEnum messageStyleEnum);


}
