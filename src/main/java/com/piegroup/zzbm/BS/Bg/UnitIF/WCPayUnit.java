package com.piegroup.zzbm.BS.Bg.UnitIF;

import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.piegroup.zzbm.Annotation.PayStyle;
import com.piegroup.zzbm.BS.Bg.Abstracts.PayAS;
import com.piegroup.zzbm.DTO.OrderDTO;
import com.piegroup.zzbm.Enums.PayStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//微信公众号支付
@Component
@PayStyle(PAY_ENUM = PayStyleEnum.WCPay_H5_PayStyle)
@Slf4j
public class WCPayUnit extends PayAS {


    private static final String ORDER_NAME = "微信支付";

    @Autowired
    private BestPayServiceImpl bestPayService;

    public boolean PayGoods(OrderDTO orderDTO, PayStyleEnum payStyleEnum){
       /* try {
            PayRequest payRequest = new PayRequest();
            payRequest.setOrderId(orderDTO.getOrderId());
            payRequest.setOrderAmount(AmountTransformUtil.StringToDouble(orderDTO.getOrderAmount()));
            payRequest.setOpenid(orderDTO.getBuyerOpenid());
            payRequest.setOrderName(ORDER_NAME);
            payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);


            PayResponse response =  bestPayService.pay(payRequest);
            log.info("【微信支付response】={}", JsonUtil.toJson(response));

        }catch (Exception e){
            throw new Exceptions(ExceptionEnum.PAY_ERROR);
        }*/

//        System.out.println(payStyleEnum.getMessage()+":"+"订单："+OrderId+"支付金额："+Amount+"--成功");
        return true;
    }
}
