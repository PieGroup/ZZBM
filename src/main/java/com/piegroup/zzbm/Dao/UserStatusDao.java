package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.UserStatusEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserStatusDao {

    @Select("select * from user_status where user_status_id = #{0}")
    UserStatusEntity queryById(@Param("0") int user_status_id);
}
