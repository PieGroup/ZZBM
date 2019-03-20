package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Mapper
public interface UserDao {

    @Select("select * from user where user_id = #{0}")
    public UserEntity queryByUserId(@Param("0") String UserId);

    @Select("select * from user where user_phone = #{0}")
    UserEntity queryByUserPhone(@Param("0") String phone);

    @Insert("insert into user(user_id,\n" +
            "                 user_login_name,\n" +
            "                 user_phone,\n" +
            "                 user_password,\n" +
            "                 user_sex,\n" +
            "                 user_statusid,\n" +
            "                 user_point,\n" +
            "                 user_money,\n" +
            "                 user_credit,\n" +
            "                 user_experience," +
            "                 user_create_time) values(" +
            "                  #{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8},#{9},#{10} )")
    boolean addUser(@Param("0") String user_id,
                    @Param("1") String user_login_name,
                    @Param("2") String phone,
                    @Param("3") String user_password,
                    @Param("4") int user_sex,
                    @Param("5") int user_statusid,
                    @Param("6") String user_point,
                    @Param("7") String user_money,
                    @Param("8") String user_credit,
                    @Param("9") String user_experience,
                    @Param("10") Timestamp user_create_time);

    @Update("update user set user_login_name = #{1} ,user_sex = #{2} where user_id = #{0}")
    boolean editUser(@Param("0") String user_id,@Param("1") String userLoginName,@Param("2") int user_sex);

    //更新用户头像
    @Update("update user set user_head_url = #{1} where user_id =#{0} ")
    boolean updateIcon(@Param("0") String user_id,@Param("1") String url);
}
