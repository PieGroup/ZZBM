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

        userDao.editUser(userEntity.getUser_Id(), editUser.getUser_Login_Name(), editUser.getUser_Sex());

        return dataPageSubc;
    }

    //设置用户刚兴趣的标签
    @Transactional
    @Override
    public ExceptionEnum SetUserLabel(String user_id, UserLabelDTO userLabelDTO, int type) {

        //type 默认插入
        if (type == 0) {
            System.out.println("用户选择的标签" + userLabelDTO);
            setLable(user_id, userLabelDTO);
        }

        return ExceptionEnum.Success;
    }

    //插入
    private void setLable(String user_id, UserLabelDTO userLabelDTO) {
        if (userLabelDTO.getId1() != null && !userLabelDTO.getId1().equals(""))
            if (!userDao.existUserLabel(user_id, userLabelDTO.getId1()))
                userDao.setuserlable(user_id, userLabelDTO.getId1());

        if (userLabelDTO.getId2() != null && !userLabelDTO.getId2().equals(""))
            if (!userDao.existUserLabel(user_id, userLabelDTO.getId2()))
                userDao.setuserlable(user_id, userLabelDTO.getId2());

        if (userLabelDTO.getId3() != null && !userLabelDTO.getId3().equals(""))
            if (!userDao.existUserLabel(user_id, userLabelDTO.getId3()))
                userDao.setuserlable(user_id, userLabelDTO.getId3());

        if (userLabelDTO.getId4() != null && !userLabelDTO.getId4().equals(""))
            if (!userDao.existUserLabel(user_id, userLabelDTO.getId4()))
                userDao.setuserlable(user_id, userLabelDTO.getId4());

        if (userLabelDTO.getId5() != null && !userLabelDTO.getId5().equals(""))
            if (!userDao.existUserLabel(user_id, userLabelDTO.getId5()))
                userDao.setuserlable(user_id, userLabelDTO.getId5());
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
        userEntity.setUser_Login_Name(Constants.user_login_name);
        //1男
        userEntity.setUser_Sex(1);
        String user_phone = "";
        if (phone != null && !phone.equals(""))
            userEntity.setUser_Phone(phone);
        userEntity.setUser_Password(RandomNumberUtil.createRandom(true, 6));

        //1代表正常
        UserStatusEntity userStatusEntity = userStatusDao.queryById(1);
        userEntity.setUser_Statusid(userStatusEntity.getUser_Status_Id());
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
        List<UserLableEntity> userLableEntities = userDao.loadUserLabel(userEntity.getUser_Id());
        if (userLableEntities != null) {
            for (UserLableEntity i : userLableEntities) {
                list.add(issueLableDao.findOneIssueId(i.getUser_Issue_Labelid()).getIssue_lable_name());
            }
        }

        userEntity.setUser_Id("");
        userEntity.setUser_Wcid("");
        userDetailEntity.setUserid("");
        map.put("user",userEntity);
        map.put("userdetail",userDetailEntity);
        map.put("label",list);

        dataPageSubc.setData(map);

        return dataPageSubc;
    }
}
