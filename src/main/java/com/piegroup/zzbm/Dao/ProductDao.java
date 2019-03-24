package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @ClassName ProductDao
 * @Description TODO
 * @Author DDLD
 * @Date 2019/3/24 12:34
 * @ModifyDate 2019/3/24 12:34
 * @Version 1.0
 */
@Mapper
public interface ProductDao {

    @Select("select * from product where product_id = #{0}")
    public ProductEntity loadByProId(@Param("0") String product_id);

    //更新库存
    @Update("update product set product_stock = #{1} where product_id = #{0}")
    boolean updateStock(@Param("0") String product_id,@Param("1") int stock);
}
