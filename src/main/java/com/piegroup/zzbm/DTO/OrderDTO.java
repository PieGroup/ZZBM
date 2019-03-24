package com.piegroup.zzbm.DTO;


import com.piegroup.zzbm.Entity.OrderDetailEntity;
import com.piegroup.zzbm.Entity.OrderMasterEntity;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    private OrderMasterEntity orderMasterEntity;

    private List<OrderDetailEntity> orderDetailEntities;


}
