package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.OrderMasterEntity;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;


@Mapper
public interface OrderMasterDao {


    //查找orders表中有多少条记录
    @Select("SELECT COUNT(*) FROM orders")
   public int OrderSize();

    //分页获取Orders表中的数据
    @Select("SELECT * FROM order_master WHERE order_master_userid = #{2} LIMIT  #{0},#{1}")
    public List<OrderMasterEntity> findList(@Param("2") String userId, @Param("0") int fromIndex, @Param("1") int pageSize);

    //获取单个订单
    @Select("select * from order_master where order_master_id = #{0}")
    OrderMasterEntity findOne(@Param("0") String orderId);

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

    @Select("select count(*) from order_master where order_master_userid = #{0}")
    public int SizeByUserId(@Param("0") String User_id);

    //更新订单状态
    @Update("update order_master set order_master_status = #{2}, order_master_update_time = #{0} where order_master_id = #{1}")
    boolean UpOrderStatus(@Param("0") Timestamp timestamp,@Param("1") String order_master_id,@Param("2") int parseInt);

    //更新支付状态
    @Update("update order_master set order_master_pay_status = #{2}, order_master_update_time = #{0} where order_master_id = #{1}")
    boolean UpPaytatus(@Param("0") Timestamp sqlTimestampNow, @Param("1") String order_master_id,@Param("2") int parseInt);
}
