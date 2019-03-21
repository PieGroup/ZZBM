package com.piegroup.zzbm.Configs;

/**
 * 常量
 *
 * @author ScienJus
 * @date 2015/7/31.
 */
public class Constants {

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * 用户验证码注册 -- 未定义登录名
     */
    public static final String user_login_name = "小白脸";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 720;

    /**
     * 存放Authorization的header字段
     */
    public static final String TOKEN = "token";

    /**
     * 验证码有效时间 秒
     */
    public static final int CODE_TIME = 180;

    /**
     * token 更新时间 秒
     */
    public static final int UPDATE_TIME_TOKEN = 2592000;

    /**
     * 头像路径保存
     */
    public static final String IconUrl = "/zzbmImg/userHead";


    /**
     * 微信appId
     */
    public static final String appid = "wxf21c2f2905962198";


    /**
     * 微信secret
     */
    public static final String secret = "bef929e25267f35baec521314d4138b4";

    /**
     * 微信 登录凭证校验 url。
     */
    public static final String WcLoginUrl = "https://api.weixin.qq.com/sns/jscode2session";


}
