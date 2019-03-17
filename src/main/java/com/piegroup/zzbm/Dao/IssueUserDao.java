package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IssueUserDao {

    @Select("select * from user where user_id =#{1} limit 1;")
    UserEntity selectUById(@Param(value = "1")String uid);


}
