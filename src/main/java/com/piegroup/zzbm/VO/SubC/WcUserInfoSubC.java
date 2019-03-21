package com.piegroup.zzbm.VO.SubC;

import lombok.Data;

/**
 * @ClassName WcTempUser
 * @Description TODO 微信
 * @Author DDLD
 * @Date 2019/3/21 9:17
 * @ModifyDate 2019/3/21 9:17
 * @Version 1.0
 */
@Data
public class WcUserInfoSubC {


    //用户标识
    private String openid;
    //会话密钥
    private String session_key;
    //用户在平台的唯一标识
    private String unionid;
}

