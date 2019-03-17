package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
public interface UserDao {

    @Select("select * from user where user_id = #{0}")
    public UserEntity queryByUserId(@Param("0") String UserId);

    @Select("select * from user where user_phone = #{0}")
    UserEntity queryByUserPhone(@Param("0") String phone);
}
