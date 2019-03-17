package com.piegroup.zzbm.BS.Bg.Abstracts;

import com.piegroup.zzbm.DTO.OrderDTO;
import com.piegroup.zzbm.BS.Bg.Interfaces.PayIF;
import com.piegroup.zzbm.Enums.PayStyleEnum;

public abstract class PayAS extends FactoryAS implements PayIF {

    public boolean PayGoods(OrderDTO orderDTO, PayStyleEnum payStyleEnum) {

        PayAS payAS = CreatePayStyleIF(payStyleEnum); // new Alipay()
        boolean b = payAS.PayGoods(orderDTO, payStyleEnum);
        return b;
    }
}
