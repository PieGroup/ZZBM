package com.piegroup.zzbm.BS.App.Service.Impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.piegroup.zzbm.BS.App.Service.OrderServiceIF;
import com.piegroup.zzbm.BS.App.Service.PayServiceIF;
import com.piegroup.zzbm.BS.Bg.Exceptions.Exceptions;
import com.piegroup.zzbm.DTO.OrderDTO;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Utils.AmountTransformUtil;
import com.piegroup.zzbm.Utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.math.BigDecimal;

/**
 * @ClassName PayServiceImpl
 * @Description TODO
 * @Author DDLD
 * @Date 2019/3/25 20:38
 * @ModifyDate 2019/3/25 20:38
 * @Version 1.0
 */
@Service
@Slf4j
public class PayServiceImpl implements PayServiceIF {

    private static final String ORDER_NAME = "微信购买睿币";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    OrderServiceIF orderServiceIF;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getOrderMasterEntity().getOrder_Master_Buyer_Openid());
        payRequest.setOrderAmount(AmountTransformUtil.StringToDouble(orderDTO.getOrderMasterEntity().getOrder_Master_Money()));
        payRequest.setOrderId(orderDTO.getOrderMasterEntity().getOrder_Master_Id());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        PayResponse payResponse = bestPayService.pay(payRequest);
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1、验证签名
        //2、支付的状态
        //3、支付金额
        //4、支付人（下单人 == 支付人）看需求

        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知：payResponse={}",payResponse);


       //查询订单
        OrderDTO orderDTO = orderServiceIF.findOne(payResponse.getOrderId());

        //判断订单是否存在
        if (orderDTO == null){
            log.info("【微信支付】异步通知 订单不存在：OrderId={}",payResponse.getOrderId());
            throw new Exceptions(ExceptionEnum.Order_Null_Exception);
        }

        //判断金额是否一致 先将分转元 再比较
        if (!MathUtil.equals(payResponse.getOrderAmount(), AmountTransformUtil.StringToDouble(orderDTO.getOrderMasterEntity().getOrder_Master_Money()))){
            log.info("【微信支付】异步通知 金额不一致：OrderId={},微信通知金额={}，系统通知金额={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderDTO.getOrderMasterEntity().getOrder_Master_Money());
            throw new Exceptions(ExceptionEnum.WC_Pay_Notify_Verify_Error);
        }

        //修改订单支付状态

        orderServiceIF.paid(orderDTO);

        return payResponse;

    }

}
