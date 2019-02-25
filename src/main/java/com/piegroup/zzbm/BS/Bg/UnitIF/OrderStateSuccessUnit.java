package com.piegroup.zzbm.BS.Bg.UnitIF;

import com.piegroup.zzbm.Annotation.OrderState;
import com.piegroup.zzbm.BS.Bg.Abstracts.OrderAS;
import com.piegroup.zzbm.Enums.OrderStatusEnum;

//订单成功
@OrderState(ORDER_STATE = OrderStatusEnum.ORDERSTATE_SUCCESS)
public class OrderStateSuccessUnit extends OrderAS {

    /**
     * 订单已完成。
     * @param orderId
     * @param orderStatusEnum
     * @return
     */
    @Override
    public boolean ModifyOrderState(String orderId, OrderStatusEnum orderStatusEnum) {
        System.out.println(orderId+"订单已完成。"+ orderStatusEnum.getMessage());
        return true;
    }
}
