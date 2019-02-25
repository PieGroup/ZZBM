package com.piegroup.zzbm.BS.Bg.UnitIF;

import com.piegroup.zzbm.Annotation.PayStyle;
import com.piegroup.zzbm.BS.Bg.Abstracts.PayAS;
import com.piegroup.zzbm.BS.Bg.DTO.OrderDTO;
import com.piegroup.zzbm.Enums.PayStyleEnum;

/**
 * 支付宝app支付
 */
@PayStyle(PAY_ENUM = PayStyleEnum.PAYSTYLE_ALIPAY_APP)
public class AlipayUnit extends PayAS {

    //支付宝支付
    public boolean PayGoods(OrderDTO orderDTO, PayStyleEnum payStyleEnum) {


//        System.out.println(payStyleEnum.getMessage()+":"+"订单："+orderDTO.getOrderId()+"支付金额："+orderDTO.getOrderAmount()+"--成功");
        return true;
    }
}
