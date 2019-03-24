package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.UserDetailEntity;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Entity.UserLableEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface UserDao {

    @Select("select user_id,user_login_name,user_real_name,user_phone,user_sex,user_head_url,user_qqid," +
            "user_wcid,user_inid,user_statusid,user_point,user_money,user_introduction,user_credit,user_experience from user where user_id = #{0}")
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
            "                 user_create_time," +
            "                 user_wcid) values(" +
            "                  #{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8},#{9},#{10},#{11})")
    boolean addUser(@Param("0") String user_id,
                    @Param("1") String user_login_name,
                    @Param("2") String phone,
                    @Param("3") String user_password,
                    @Param("4") String user_sex,
                    @Param("5") int user_statusid,
                    @Param("6") String user_point,
                    @Param("7") String user_money,
                    @Param("8") String user_credit,
                    @Param("9") String user_experience,
                    @Param("10") Timestamp user_create_time,
                    @Param("11") String openid);

    @Update("update user set user_login_name = #{1} ,user_sex = #{2}, user_introduction = #{3} where user_id = #{0}")
    boolean editUser( @Param("0") String user_id, @Param("1") String userLoginName, @Param("2") String user_sex, @Param("3") String Introduction );

    //更新用户头像
    @Update("update user set user_head_url = #{1} where user_id =#{0} ")
    boolean updateIcon(@Param("0") String user_id,@Param("1") String url);

    //判断用户兴趣标签表里面是否存在该标签
    @Select("select count(*) from user_mtm_issue_lable where userid =#{0} and issue_lableid = #{1}")
    boolean existUserLabel(@Param("0") String user_id,@Param("1") String id1);

    //插入用户兴趣标签
    @Insert("insert into user_mtm_issue_lable(userid, issue_lableid) VALUES (#{0},#{1})")
    boolean setuserlable(@Param("0") String user_id,@Param("1") String labelId);

    //判断该用户openid
    @Select("select * from user where user_wcid = #{0}")
    UserEntity existWcid(@Param("0") String openid);

    @Select("select user_money from user where user_id = #{0}")
    String loadWalletById(@Param("0") String user_id);


    //查找用户细节信息
    @Select("select * from user_detail where userid = #{0}")
    UserDetailEntity loadByUserId(@Param("0") String user_id);

    @Select("select * from user_lable where userid = #{0}")
    List<UserLableEntity> loadUserLabel(@Param("0") String user_id);
}
