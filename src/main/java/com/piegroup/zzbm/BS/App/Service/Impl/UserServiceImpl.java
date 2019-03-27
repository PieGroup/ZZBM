package com.piegroup.zzbm.BS.App.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.piegroup.zzbm.BS.App.Service.UserServiceIF;
import com.piegroup.zzbm.BS.Bg.Exceptions.Exceptions;
import com.piegroup.zzbm.Configs.Constants;
import com.piegroup.zzbm.DTO.UserLabelDTO;
import com.piegroup.zzbm.Dao.IssueLableDao;
import com.piegroup.zzbm.Dao.UserDao;
import com.piegroup.zzbm.Dao.UserStatusDao;
import com.piegroup.zzbm.Dao.WcUserDao;
import com.piegroup.zzbm.Entity.*;
import com.piegroup.zzbm.Enums.CertificateEnum;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Utils.HttpClientUtil;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.TimeUtil2;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.WcUserInfoSubC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author DDLD
 * @Date 2019/3/16 22:14
 * @ModifyDate 2019/3/16 22:14
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserServiceIF {

    @Resource
    UserDao userDao;

    @Resource
    UserStatusDao userStatusDao;

    @Resource
    WcUserDao wcUserDao;

    @Resource
    IssueLableDao issueLableDao;

    @Autowired
    private IssueQuestionsServiceImpl issueQuestionsService;

    @Autowired
    private IssueCommentServiceImpl issueCommentService;

    @Autowired
    private IssueDemandServiceImpl issueDemandService;

    @Autowired
    private IssueConsultServiceImpl issueConsultService;

    @Autowired
    private IssueProgramServiceImpl issueProgramService;


    @Override
    public Map queryByUserId(String UserId) {

        Map map = new HashMap();

        UserEntity userEntity = new UserEntity();

        userEntity = userDao.queryByUserId(UserId);

        userEntity.setUser_Password("");
        //1代表正常
        UserStatusEntity userStatusEntity = userStatusDao.queryById(userEntity.getUser_Statusid());


        map.put("user", userEntity);
        map.put("user_status", userStatusEntity);
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

        UserEntity userEntity = defaultUser(phone, null);

        UserStatusEntity userStatusEntity = userStatusDao.queryById(userEntity.getUser_Statusid());

        userDao.addUser(userEntity.getUser_Id(),
                userEntity.getUser_Login_Name(),
                userEntity.getUser_Phone(),
                userEntity.getUser_Password(),
                userEntity.getUser_Sex(),
                userEntity.getUser_Statusid(),
                userEntity.getUser_Point(),
                userEntity.getUser_Money(),
                userEntity.getUser_Credit(),
                userEntity.getUser_Experience(),
                userEntity.getUser_Create_Time(),
                userEntity.getUser_Wcid());

        //不显示密码
        userEntity.setUser_Password("");
        map.put("user", userEntity);
        map.put("user_status", userStatusEntity);

        return map;
    }

    //查询用户的提问、咨询等
    @Override
    public DataPageSubc issue(String user_id, String type, int pageSize, int pageNum) {

        //发表的问题
        if (type.equals("questions")) {
            log.info("拿去用户问题");
            return issueQuestionsService.queryById(user_id, pageSize, pageNum);
        }//发布的需求
        else if (type.equals("demand")) {
            log.info("拿用户的需求");
            return issueDemandService.loadByUserId(user_id, pageSize, pageNum);

        } //发布的咨询
        else if (type.equals("consult")) {
            log.info("拿用户的咨询");
            return issueConsultService.loadByUserId(user_id, pageSize, pageNum);
        } else if (type.equals("program")) {
            log.info("拿用户的方案");
            return issueProgramService.loadByUserId(user_id, pageSize, pageNum);
        } else {

            DataPageSubc dataPageSubc = new DataPageSubc();
            dataPageSubc.setData("正在开发");
            return dataPageSubc;
        }

    }

    //编辑用户信息
    public DataPageSubc editUser(UserEntity userEntity, UserEntity editUser) {
        DataPageSubc dataPageSubc = new DataPageSubc();

        if (editUser.getUser_Sex() == null || editUser.getUser_Sex().equals(""))
            editUser.setUser_Sex(userEntity.getUser_Sex());
        if (editUser.getUser_Login_Name() == null || editUser.getUser_Login_Name().equals(""))
            editUser.setUser_Login_Name(userEntity.getUser_Login_Name());

        userDao.editUser(userEntity.getUser_Id(), editUser.getUser_Login_Name(), editUser.getUser_Sex(), editUser.getUser_Introduction());

        return dataPageSubc;
    }

    //设置用户刚兴趣的标签
    @Transactional
    @Override
    public ExceptionEnum SetUserLabel(String user_id, UserLabelDTO userLabelDTO) {


        System.out.println("用户选择的标签" + userLabelDTO);

        List<String> list = userLabelDTO.getLabel_id();
        userDao.delUserlable(user_id);
        for (String i : list) {
            if (i != null && !i.equals("")) {
                if (!userDao.existUserLabel(user_id, i)) {
                    if (issueLableDao.findOneIssueId(i) != null) {
                        //先查是否满了5条，如果不满再加，满的话，先删除再加
//                        int num = userDao.SizeUserLabel(user_id);
//                        if (num < 6) {
//                            userDao.setuserlable(user_id, i);
//                        } else {

                        userDao.setuserlable(user_id, i);
//                        }
                    } else
                        return ExceptionEnum.Label_Null_Exception;
                }
            } else
                return ExceptionEnum.Param_Exception;
        }

        return ExceptionEnum.Success;
    }


    //微信用户请求登录
    @Transactional
    public Map WcLogin(String code) {

        WcUserInfoSubC wcUserInfoSubC = new WcUserInfoSubC();

        Map maps = new HashMap();

        //设置请求参数
        Map<String, String> map = new HashMap<>();

        map.put("appid", Constants.appid);
        map.put("secret", Constants.secret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        JSONObject o = new JSONObject();
        try {
            try {

                o = HttpClientUtil.sendGet(Constants.WcLoginUrl, HttpClientUtil.getParams(map));
                System.out.println(o.toJSONString());

            } catch (Exception e) {
                throw new Exceptions(ExceptionEnum.Wc_User_Request_Exception);
            }
            if (o.getString("openid") != null) {
                System.out.println(o.toJSONString());
                wcUserInfoSubC.setOpenid(o.getString("openid"));
                wcUserInfoSubC.setSession_key(o.getString("session_key"));


                //先判断user 表里面存在该微信用户
                UserEntity user = userDao.existWcid(wcUserInfoSubC.getOpenid());
                //不存在添加userid,返回userid
                if (user == null)
                    user = addWcUser(wcUserInfoSubC.getOpenid());

                boolean wcuser = wcUserDao.existWcOpenid(wcUserInfoSubC.getOpenid());

                //不存在wcuser 个人资料添加
                if (!wcuser) {
                    wcUserDao.addWcUser(wcUserInfoSubC.getOpenid(), wcUserInfoSubC.getSession_key());
                }
                //设置返回密码为空
                user.setUser_Password("");
                UserStatusEntity userStatusEntity = userStatusDao.queryById(user.getUser_Statusid());

                maps.put("entity", user);
                maps.put("status", userStatusEntity);

            }

            return maps;

        } catch (Exception e) {
            throw e;
        }

    }

    //添加微信用户
    //返回新建用户的userid
    @Transactional
    @Override
    public UserEntity addWcUser(String openid) {
        log.info("开始注册微信用户");
        UserEntity userEntity = defaultUser(null, openid);
        userDao.addUser(userEntity.getUser_Id(),
                userEntity.getUser_Login_Name(),
                userEntity.getUser_Phone(),
                userEntity.getUser_Password(),
                userEntity.getUser_Sex(),
                userEntity.getUser_Statusid(),
                userEntity.getUser_Point(),
                userEntity.getUser_Money(),
                userEntity.getUser_Credit(),
                userEntity.getUser_Experience(),
                userEntity.getUser_Create_Time(),
                userEntity.getUser_Wcid());
        log.info("结束注册微信用户");
        return userEntity;
    }

    //返回生成一个默认的用户对象
    private UserEntity defaultUser(String phone, String openid) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUser_Id("U" + RandomNumberUtil.createRandom(false, 16));
        userEntity.setUser_Login_Name(phone.substring(phone.length()-4,phone.length()));
        //1男
        userEntity.setUser_Sex("男");
        String user_phone = "";
        if (phone != null && !phone.equals(""))
            userEntity.setUser_Phone(phone);
        userEntity.setUser_Password(RandomNumberUtil.createRandom(true, 6));

        //1代表正常
        UserStatusEntity userStatusEntity = userStatusDao.queryById(1);
        userEntity.setUser_Statusid(userStatusEntity.getUser_Status_Id());
        userEntity.setUser_Head_Url(Constants.httpUrl+Constants.IconUrl+"/"+Constants.hrad);
        //积分
        userEntity.setUser_Point("0");
        //睿币
        userEntity.setUser_Money("0");
        //用户信用
        userEntity.setUser_Credit("100");
        userEntity.setUser_Experience("0");
        userEntity.setUser_Create_Time(TimeUtil2.SQLTimestampNow());
        String user_wcid = "";
        if (openid != null && !openid.equals(""))
            userEntity.setUser_Wcid(openid);
        return userEntity;

    }

    //查看用户的钱包
    public DataPageSubc wallet(String user_id) {
        DataPageSubc dataPageSubc = new DataPageSubc();
        String user_money = userDao.loadWalletById(user_id);

        Map map = new HashMap();
        map.put("user_money", user_money);
        dataPageSubc.setData(map);
        return dataPageSubc;
    }


    //app 专家认证
    @Override
    public DataPageSubc certification(String user_id, HttpServletRequest request) {


        request.getParameter("");

        return null;
    }

    //个人细节
    @Override
    public DataPageSubc detail(UserEntity userEntity) {

        UserDetailEntity userDetailEntity = userDao.loadByUserId(userEntity.getUser_Id());
        List<String> list = new ArrayList<String>();
        Map map = new HashMap();
        DataPageSubc dataPageSubc = new DataPageSubc();


        //查询用户个人认证的标签
        CertificationEntity certificationEntity = userDao.loadUserLabel(userEntity.getUser_Id());
        if (certificationEntity != null) {

            list.add(issueLableDao.findOneIssueId(certificationEntity.getUser_Labelid()).getIssue_lable_name());
            map.put("labelStatus", CertificateEnum.valueOf(String.valueOf(certificationEntity.getCertification_Statusid())));
        }

        userEntity.setUser_Id("");
        userEntity.setUser_Wcid("");
        if (userDetailEntity != null)
            userDetailEntity.setUserid("");
        map.put("user", userEntity);
        map.put("userdetail", userDetailEntity);
        map.put("label", list);


        dataPageSubc.setData(map);

        return dataPageSubc;
    }

    //显示用户感兴趣的标签
    @Override
    public DataPageSubc listUserLabel(String user_id) {

        DataPageSubc dataPageSubc = new DataPageSubc();
        List<UserMtmIssueLableEntity> l = userDao.listUserLabelById(user_id);
        List list = new ArrayList();
        Map map = new HashMap();
        if (l != null) {
            for (UserMtmIssueLableEntity u : l) {
                list.add(issueLableDao.findOneIssueId(u.getIssue_Lableid()).getIssue_lable_name());
            }
            map.put("label", list);
            dataPageSubc.setData(map);
        }

        return dataPageSubc;
    }

    //保存微信用户的头像和用户名
    @Override
    public void setWcUserInfo(UserEntity userEntity, String userhead, String loginname) {

        //是否属于微信用户
        if (userEntity.getUser_Wcid() == null || userEntity.getUser_Wcid().equals("")) {
            throw new Exceptions(ExceptionEnum.Wc_User_Request_Exception);
        }

        //保存
        boolean b = userDao.setWcUserInfo(userEntity.getUser_Id(), userhead, loginname);
        if (!b) {
            log.error("【微信保存用户】头像保存错误，userid={}", userEntity.getUser_Id());
            throw new Exceptions(ExceptionEnum.Wc_User_Info_Error);
        }


    }
}
