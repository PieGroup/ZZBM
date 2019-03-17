package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.Entity.UserEntity;

import java.util.List;

public interface UserServiceIF {

    //通过id 身份
    public UserEntity queryByUserId(String UserId);

    //通过手机拿身份
    UserEntity queryByUserPhone(String phone);
}
