package com.piegroup.zzbm.Configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信支付配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WCAccountConfig {

   private String mpAppId;
   private String mpAppSecret;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 商户密钥
     */
    private String mchKey;
    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通知
     */
    private String notify_url;
}
