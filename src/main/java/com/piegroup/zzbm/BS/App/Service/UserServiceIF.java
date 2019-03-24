package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.DTO.UserLabelDTO;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserServiceIF {

    //通过id 身份
    public Map queryByUserId(String UserId);

    //通过手机拿身份
    UserEntity queryByUserPhone(String phone);

    //添加安卓用户
    Map addUser(String phone);

    //我的发布
    DataPageSubc issue(String user_id, String type, int pageSize, int pageNum);
    //编辑个人资料
    public DataPageSubc editUser(UserEntity userEntity, UserEntity editUser);

    ExceptionEnum SetUserLabel(String user_id, UserLabelDTO userLabelDTO);

    Map WcLogin(String code);

    //添加微信用户
    UserEntity addWcUser(String openid);

    //查看钱包
    DataPageSubc wallet(String user_id);

    DataPageSubc certification(String user_id, HttpServletRequest request);


    //显示个人详细信息
    DataPageSubc detail(UserEntity userEntity);
}
