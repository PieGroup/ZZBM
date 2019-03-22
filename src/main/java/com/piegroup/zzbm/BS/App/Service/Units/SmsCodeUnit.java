package com.piegroup.zzbm.BS.App.Service.Units;

import com.piegroup.zzbm.BS.App.Service.Adapter.MessageAt;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Enums.SMSNoticeEnum;
import com.piegroup.zzbm.Utils.SMSCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
*@ClassName     SmsCodeUnit
*@Description   TODO  Code消息发送
*@Author        DDLD
*@Date          2019/3/17 20:57
*@ModifyDate    2019/3/17 20:57
*@Version       1.0
*/
@Component
public class SmsCodeUnit extends MessageAt {

    @Autowired
    public SMSCodeUtil smsCodeUtil;

    public ExceptionEnum send(String phone) {

        return smsCodeUtil.SendCode(
                phone,
                SMSNoticeEnum.AccessId,
                SMSNoticeEnum.AccessKeySecre,
                SMSNoticeEnum.SignName,
                SMSNoticeEnum.SMSTemplateCode);
    }
    public ExceptionEnum check(String userPhone,String code){
        return smsCodeUtil.checkCode(userPhone,code);
    }


}
