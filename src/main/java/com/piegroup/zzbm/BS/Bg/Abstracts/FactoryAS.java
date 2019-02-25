package com.piegroup.zzbm.BS.Bg.Abstracts;


import com.piegroup.zzbm.BS.Bg.Factories.MessagesFactory;
import com.piegroup.zzbm.BS.Bg.Factories.OrderStatusFactory;
import com.piegroup.zzbm.BS.Bg.Factories.PayStyleFactory;
import com.piegroup.zzbm.BS.Bg.Interfaces.FactoryIF;
import com.piegroup.zzbm.Enums.MessageStyleEnum;
import com.piegroup.zzbm.Enums.OrderStatusEnum;
import com.piegroup.zzbm.Enums.PayStyleEnum;

public abstract class FactoryAS implements FactoryIF {

    private static OrderStatusFactory orderStatusFactory;
    private static PayStyleFactory payStyleFactory;
    private static MessagesFactory messagesFactory;

    @Override
    public OrderAS CreateModifyOrderStatusIF(OrderStatusEnum orderStatusEnum) {
        orderStatusFactory = new OrderStatusFactory();
       return orderStatusFactory.CreateModifyOrderStatusIF(orderStatusEnum);
    }

    @Override
    public PayAS CreatePayStyleIF(PayStyleEnum payStyleEnum) {

        payStyleFactory = new PayStyleFactory();
        return payStyleFactory.CreatePayStyleIF(payStyleEnum);
    }

    public MessageAS CreateMessageIF(MessageStyleEnum messageStyleEnum){
        messagesFactory = new MessagesFactory();
        return messagesFactory.CreateMessageIF(messageStyleEnum);
    }
}
