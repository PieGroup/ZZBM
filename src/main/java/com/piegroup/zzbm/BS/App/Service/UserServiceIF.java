package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;

import java.util.Map;

public interface UserServiceIF {

    //通过id 身份
    public Map queryByUserId(String UserId);

    //通过手机拿身份
    UserEntity queryByUserPhone(String phone);

    //添加用户
    Map addUser(String phone);

    //我的发布
    DataPageSubc issue(String user_id, String type, int pageSize, int pageNum);
    //编辑个人资料
    public DataPageSubc editUser(UserEntity userEntity, UserEntity editUser);

}
