package com.piegroup.zzbm.BS.Bg.Service;

import com.piegroup.zzbm.BS.Bg.Abstracts.MessageAS;
import com.piegroup.zzbm.Enums.MessageStyleEnum;
import org.springframework.stereotype.Service;


@Service
public  class MessageService extends MessageAS {

    //
    public  boolean SendSMSCodeNoticeSv(MessageStyleEnum messageStyleEnum){
       return   SendMsg(messageStyleEnum).SmsCodeMessage("13870080064");
    }


}
