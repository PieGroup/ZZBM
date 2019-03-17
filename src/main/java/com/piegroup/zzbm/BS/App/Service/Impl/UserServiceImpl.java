package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.UserServiceIF;
import com.piegroup.zzbm.Dao.UserDao;
import com.piegroup.zzbm.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

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

    @Override
    public UserEntity queryByUserId(String UserId) {
        return userDao.queryByUserId(UserId);
    }

    @Override
    public UserEntity queryByUserPhone(String phone) {
        return userDao.queryByUserPhone(phone);
    }

}
