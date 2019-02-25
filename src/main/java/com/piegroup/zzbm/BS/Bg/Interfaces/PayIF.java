package com.piegroup.zzbm.BS.Bg.Interfaces;

import com.piegroup.zzbm.BS.Bg.DTO.OrderDTO;
import com.piegroup.zzbm.Enums.PayStyleEnum;

public interface PayIF {

    //购买商品OrderId订单id  Amount 金额 ,PayEnum 支付方式
    boolean PayGoods(OrderDTO orderDTO, PayStyleEnum payStyleEnum);

}
