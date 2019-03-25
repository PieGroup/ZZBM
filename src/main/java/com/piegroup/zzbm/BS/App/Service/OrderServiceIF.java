package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.DTO.OrderDTO;
import com.piegroup.zzbm.DTO.ProductDTO;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
*@ClassName     OrderService
*@Description   TODO 订单
*@Author        DDLD
*@Date          2019/3/24 12:22
*@ModifyDate    2019/3/24 12:22
*@Version       1.0
*/
public interface OrderServiceIF {

    /** 创建订单. */
    OrderDTO create(UserEntity userEntity,ProductDTO productDTO);

    /** 查询单个订单. */
    OrderDTO findOne(String orderId);

    /** 查询订单列表. */
    DataPageSubc findList(String user_id, int pageNum, int pageSize);

    /** 取消订单. */
    OrderDTO cancel(OrderDTO orderDTO);

    /** 完结订单. */
    OrderDTO finish(OrderDTO orderDTO);

    /** 支付订单. */
    OrderDTO paid(OrderDTO orderDTO);

    /** 查询订单列表. */
    Page<OrderDTO> findList(Pageable pageable);
}
