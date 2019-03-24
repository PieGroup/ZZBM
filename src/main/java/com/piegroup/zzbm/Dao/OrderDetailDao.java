package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.OrderDetailEntity;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;

/**
 * @ClassName OrderDetailDao
 * @Description TODO
 * @Author DDLD
 * @Date 2019/3/24 12:53
 * @ModifyDate 2019/3/24 12:53
 * @Version 1.0
 */

@Mapper
public interface OrderDetailDao {

    @Insert("insert into order_detail(" +
            "order_detail_id,\n" +
            "order_detail_orderid,\n" +
            "order_detail_productid,\n" +
            "order_detail_create_time,\n" +
            "order_detail_update_time,\n" +
            "order_detail_quantity,\n" +
            "order_detail_price) values (" +
            "#{order_Detail_Id},\n" +
            "#{order_Detail_Orderid},\n" +
            "#{order_Detail_Productid},\n" +
            "#{order_Detail_Create_Time},\n" +
            "#{order_Detail_Update_Time},\n" +
            "#{order_Detail_Quantity},\n" +
            "#{order_Detail_Price})")
    public boolean save( OrderDetailEntity orderDetailEntity);
}
