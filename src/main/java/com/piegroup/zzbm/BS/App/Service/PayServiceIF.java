package com.piegroup.zzbm.BS.App.Service;

import com.lly835.bestpay.model.PayResponse;
import com.piegroup.zzbm.DTO.OrderDTO;

/**
 * @ClassName PayService
 * @Description TODO
 * @Author DDLD
 * @Date 2019/3/25 20:37
 * @ModifyDate 2019/3/25 20:37
 * @Version 1.0
 */
public interface PayServiceIF {
    //下单
    PayResponse create(OrderDTO orderDTO);

    //异步返回数据
    PayResponse notify(String notifyData);
}
