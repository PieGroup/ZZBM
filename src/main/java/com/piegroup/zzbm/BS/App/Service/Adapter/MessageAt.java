package com.piegroup.zzbm.BS.App.Service.Adapter;

import com.piegroup.zzbm.BS.App.Service.MessageServiceIF;
import com.piegroup.zzbm.BS.App.Service.Units.SmsCodeUnit;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MessageAt
 * @Description TODO  接口适配器 Unit 里面实现完成 这个只是调用
 * @Author DDLD
 * @Date 2019/3/17 20:50
 * @ModifyDate 2019/3/17 20:50
 * @Version 1.0
 */
@Service
public class MessageAt implements MessageServiceIF {

    @Autowired
    private SmsCodeUnit smsCodeUnit;

    @Override
    public ExceptionEnum send(String phone) {

        return smsCodeUnit.send(phone);
    }
}
