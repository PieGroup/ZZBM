package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.OrderMasterEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface OrderMasterDao {


    //查找orders表中有多少条记录
    @Select("SELECT COUNT(*) FROM orders")
   public int OrderSize();

    //分页获取Orders表中的数据
    @Select("SELECT * FROM orders WHERE orders.uid = #{2} LIMIT  #{0},#{1}")
    public List<OrderMasterEntity> loadPage(@Param("2") String userId, @Param("0") int fromIndex, @Param("1") int pageSize);

    //获取单个订单
    OrderMasterEntity findOne(String orderId);

    @Select("select * from issue_status")
    public int test();


    //保存订单
    @Insert("insert into order_master values(#{order_Master_Id},\n" +
            "#{order_Master_Userid},\n" +
            "#{order_Master_Money},\n" +
            "#{order_Master_Piad_Type},\n" +
            "#{order_Master_Status},\n" +
            "#{order_Master_Pay_Status},\n" +
            "#{order_Master_Create_Time},\n" +
            "#{order_Master_Update_Time},\n" +
            "#{order_Master_Buyer_Openid},\n" +
            "#{order_Master_Phone})")
    boolean save( OrderMasterEntity orderMasterEntity);

}
