package com.piegroup.zzbm.BS.Bg.Service;

import com.piegroup.zzbm.BS.Bg.Abstracts.MessageAS;
import com.piegroup.zzbm.Enums.MessageEnum;
import org.springframework.stereotype.Service;


@Service
public  class MessageService extends MessageAS {

    //
    public  boolean SendSMSCodeNoticeSv(MessageEnum messageEnum){
       return   SendMsg(messageEnum).SmsCodeMessage("13870080064");
    }


}
