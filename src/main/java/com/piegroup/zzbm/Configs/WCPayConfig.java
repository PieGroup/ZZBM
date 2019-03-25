package com.piegroup.zzbm.Configs;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WCPayConfig {

    @Autowired
    private WCAccountConfig wcAccountConfig;

    @Bean
    public BestPayServiceImpl bestPayService() {

        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wcAccountConfig.getMpAppId());
        wxPayH5Config.setAppSecret(wcAccountConfig.getMpAppSecret());
        wxPayH5Config.setMchId(wcAccountConfig.getMchId());
        wxPayH5Config.setMchKey(wcAccountConfig.getMchKey());
        wxPayH5Config.setKeyPath(wcAccountConfig.getKeyPath());
        wxPayH5Config.setNotifyUrl(wcAccountConfig.getNotify_url());
        return wxPayH5Config;
    }
}
