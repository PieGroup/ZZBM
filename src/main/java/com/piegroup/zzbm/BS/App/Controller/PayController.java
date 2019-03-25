package com.piegroup.zzbm.BS.App.Controller;

import com.lly835.bestpay.model.PayResponse;
import com.piegroup.zzbm.Annotation.Authorization;
import com.piegroup.zzbm.Annotation.CurrentUser;
import com.piegroup.zzbm.BS.App.Service.OrderServiceIF;
import com.piegroup.zzbm.BS.App.Service.PayServiceIF;
import com.piegroup.zzbm.BS.Bg.Exceptions.Exceptions;
import com.piegroup.zzbm.DTO.OrderDTO;
import com.piegroup.zzbm.DTO.ProductDTO;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import java.util.Map;

/**
 * @ClassName PayController
 * @Description TODO 支付
 * @Author DDLD
 * @Date 2019/3/24 11:49
 * @ModifyDate 2019/3/24 11:49
 * @Version 1.0
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderServiceIF orderServiceIF;

    @Autowired
    private PayServiceIF payServiceIF;


    @GetMapping("/create")
    @ResponseBody
    @Authorization
    public ModelAndView WC_H5_Pay(@CurrentUser UserEntity userEntity,
                                  @RequestParam("orderId") String orderId,
                                  @RequestParam("returnUrl") String returnUrl,
                                  Map<String,Object> map) {

        //查看订单
        OrderDTO orderDTO = orderServiceIF.findOne(orderId);
        if (orderDTO ==null){
            throw new Exceptions(ExceptionEnum.Order_Null_Exception);
        }
        //判断用户设备是否支持支付
        if (userEntity.getUser_Wcid()==null || userEntity.getUser_Wcid().equals("") || orderDTO.getOrderMasterEntity().getOrder_Master_Buyer_Openid() == null|| orderDTO.getOrderMasterEntity().getOrder_Master_Buyer_Openid().equals(""))
            throw new Exceptions(ExceptionEnum.User_Paid_Not_Stand_Exception);

        PayResponse payResponse = payServiceIF.create(orderDTO);

        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);

        return new ModelAndView("pay/create",map);
    }

    //异步通知
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payServiceIF.notify(notifyData);


        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }



}
