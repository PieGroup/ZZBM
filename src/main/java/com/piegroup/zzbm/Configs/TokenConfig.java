package com.piegroup.zzbm.Configs;
/**
*@ClassName     TokenConfig
*@Description   TODO
*@Author        DDLD
*@Date          2019/3/16 21:37
*@ModifyDate    2019/3/16 21:37
*@Version       1.0
*/
public class TokenConfig {
    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放token的header字段
     */
    public static final String TOKEN = "token";
}
