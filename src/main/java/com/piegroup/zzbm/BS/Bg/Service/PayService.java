package com.piegroup.zzbm.BS.Bg.Service;

import com.piegroup.zzbm.BS.Bg.Abstracts.PayAS;
import com.piegroup.zzbm.BS.Bg.DTO.OrderDTO;
import com.piegroup.zzbm.Enums.OrderStatusEnum;
import com.piegroup.zzbm.Enums.PayStyleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 不管是什么方式支付，支付成功后，都要修改订单状态，所以将订单的状态修改为已支付
 */
@Service
public class PayService extends PayAS {

    //以继承的方式调用父类中的实现方法，不是重写
    @Autowired
    private OrderService orderService ;


    public boolean PayGoodsSv(String OrderId, PayStyleEnum payStyleEnum) {

        OrderDTO orderDTO = new OrderDTO();

        boolean b =  PayGoods(orderDTO, payStyleEnum);
        if (b){
            System.out.println("购买成功！");
            //调用service中的修改订单状态
            orderService.ModifyOrderStateSv(OrderId, OrderStatusEnum.ORDERSTATE_SUCCESS);
        }else{
            System.out.println("购买失败！");
            return false;
        }

        return true;
    }

}
