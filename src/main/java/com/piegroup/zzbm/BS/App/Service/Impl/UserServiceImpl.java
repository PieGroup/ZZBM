package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.UserServiceIF;
import com.piegroup.zzbm.Configs.Constants;
import com.piegroup.zzbm.Dao.UserDao;
import com.piegroup.zzbm.Dao.UserStatusDao;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Entity.UserStatusEntity;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.TimeUtil2;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.awt.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;


/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author DDLD
 * @Date 2019/3/16 22:14
 * @ModifyDate 2019/3/16 22:14
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserServiceIF {

    @Resource
    UserDao userDao;

    @Resource
    UserStatusDao userStatusDao;

    @Override
    public Map queryByUserId(String UserId) {

        Map map = new HashMap();

        UserEntity userEntity = new UserEntity();

        userEntity = userDao.queryByUserId(UserId);

        userEntity.setUser_Password("");
        //1代表正常
        UserStatusEntity userStatusEntity = userStatusDao.queryById(userEntity.getUser_Statusid());


        map.put("user",userEntity);
        map.put("user_status",userStatusEntity);
        return map;
    }

    @Override
    public UserEntity queryByUserPhone(String phone) {
        return userDao.queryByUserPhone(phone);
    }

    @Override
    @Transactional
    public Map addUser(String phone) {

        DataPageSubc dataPageSubc = new DataPageSubc();

        Map map = new HashMap();

        UserEntity userEntity = new UserEntity();
        String user_id = "U" + RandomNumberUtil.createRandom(false, 16);
        String user_login_name = Constants.user_login_name;
        //1男
        int user_sex = 1;
        String user_phone = phone;
        String user_password = RandomNumberUtil.createRandom(true, 6);

        //1代表正常
        UserStatusEntity userStatusEntity = userStatusDao.queryById(1);
        int user_statusid = userStatusEntity.getUser_Status_Id();
        //积分
        String user_point = "0";
        //睿币
        String user_money = "0";
        //用户信用
        String user_credit = "100";

        String user_experience = "0";

        Timestamp user_create_time =TimeUtil2.SQLTimestampNow();


        userDao.addUser(user_id,
                user_login_name,
                user_phone,
                user_password,
                user_sex,
                user_statusid,
                user_point,
                user_money,
                user_credit,
                user_experience,
                user_create_time);


        userEntity.setUser_Id(user_id);
        userEntity.setUser_Create_Time(user_create_time);
        userEntity.setUser_Experience(user_experience);
        userEntity.setUser_Login_Name(user_login_name);
        userEntity.setUser_Money(user_money);
        userEntity.setUser_Phone(user_phone);
        userEntity.setUser_Point(user_point);
        userEntity.setUser_Sex(user_sex);
        userEntity.setUser_Statusid(user_statusid);
        userEntity.setUser_Credit(user_credit);
        userEntity.setUser_Password("");


        map.put("user", userEntity);
        map.put("user_status", userStatusEntity);

        return map;
    }

}
