package com.piegroup.zzbm.BS.Bg.Abstracts;

import com.piegroup.zzbm.BS.Bg.Interfaces.OrderIF;
import com.piegroup.zzbm.Enums.OrderStatusEnum;

public abstract class OrderAS extends FactoryAS implements OrderIF {

    @Override
    public boolean ModifyOrderState(String orderId, OrderStatusEnum orderStatusEnum) {

        OrderAS OrderAS = CreateModifyOrderStatusIF(orderStatusEnum);
        boolean b = OrderAS.ModifyOrderState(orderId, orderStatusEnum);
        if (b == true) System.out.println("修改成功！");
        else System.out.println("修改失败！");
        return b;
    }


}
