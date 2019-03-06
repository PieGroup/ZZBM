package com.piegroup.zzbm.BS.Bg.Service;


import com.piegroup.zzbm.BS.Bg.Abstracts.OrderAS;
import com.piegroup.zzbm.Dao.OrderMasterDao;
import com.piegroup.zzbm.Enums.OrderStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderService extends OrderAS {

    /**
     * @param orderId   订单id
     * @param orderEnum 订单要修改的状态
     * @return 返回是否修改成功
     */
    @Resource
    private OrderMasterDao orderMasterDao;

    //使用父类中的方法，所以不能重写，不然会出现死循环 使用父类方法  ModifyOrderStates()
//    @Cacheable(key = "'redis_' + #id", cacheNames = "redis-cache")
    @Transactional(isolation = Isolation.DEFAULT)// 使用读写提交隔离级别，适用于高并发场景
    public boolean ModifyOrderStateSv(String orderId, OrderStatusEnum orderStatusEnum) {
        //根据id查找订单然后进行修改

       return ModifyOrderState(orderId, orderStatusEnum);
    }






}
