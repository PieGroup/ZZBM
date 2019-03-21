package com.piegroup.zzbm.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName WcUserDao
 * @Description TODO
 * @Author DDLD
 * @Date 2019/3/21 10:32
 * @ModifyDate 2019/3/21 10:32
 * @Version 1.0
 */
@Mapper
public interface WcUserDao {

    @Select("select count(*) from wcuser where openid = #{0}")
    public boolean existWcOpenid(@Param("0") String openid);

    @Insert("insert into wcuser(openid,session_key) values (#{0},#{1})")
    boolean addWcUser(@Param("0") String openid,@Param("1") String session_key);
}
