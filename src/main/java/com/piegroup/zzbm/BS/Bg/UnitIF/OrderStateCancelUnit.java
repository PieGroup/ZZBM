package com.piegroup.zzbm.BS.Bg.UnitIF;

import com.piegroup.zzbm.Annotation.OrderState;
import com.piegroup.zzbm.BS.Bg.Abstracts.OrderAS;
import com.piegroup.zzbm.BS.Bg.Exceptions.Exceptions;
import com.piegroup.zzbm.BS.Bg.Service.RedisLockService;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Enums.OrderStatusEnum;
import com.piegroup.zzbm.Utils.SpringUtil;

//取消订单注解
@OrderState(ORDER_STATE = OrderStatusEnum.ORDERSTATE_CANCEL )
public class OrderStateCancelUnit extends OrderAS {

    /**
     * 取消订单
     * @param orderId
     * @param orderEnum
     * @return
     */
    private RedisLockService redisLockService = SpringUtil.getBean(RedisLockService.class);


    @Override
    public boolean ModifyOrderState(String orderId, OrderStatusEnum orderStatusEnum) {
        //在Dao层查询orderId ,修改订单状态
        //商品id 暂用时间  模拟，真实项目中也是如此
        String productid = "123";
        int timeout = 10*1000;
        //给某件商品加上锁，或者钱加上锁
        if (!redisLockService.lock(productid,String.valueOf(timeout))){
            throw new Exceptions(ExceptionEnum.LOCK_FALSE);
        }
        System.out.println(orderId+"正在"+ orderStatusEnum.getMessage()+"订单--");
        System.out.println(orderStatusEnum.getMessage()+"成功！");

        //解锁
        redisLockService.unlock(productid,String.valueOf(timeout));
        return true;
    }
}
