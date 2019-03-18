package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;

import java.util.List;
import java.util.Map;

public interface UserServiceIF {

    //通过id 身份
    public Map queryByUserId(String UserId);

    //通过手机拿身份
    UserEntity queryByUserPhone(String phone);

    //添加用户
    Map addUser(String phone);



}
