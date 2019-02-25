package com.piegroup.zzbm.BS.Bg.Abstracts;

import com.piegroup.zzbm.BS.Bg.Interfaces.MessageIF;
import com.piegroup.zzbm.Enums.MessageStyleEnum;

public abstract class MessageAS extends FactoryAS implements MessageIF {

    private static MessageAS messageAS;
    public MessageAS SendMsg(MessageStyleEnum messageStyleEnum){
        messageAS =  CreateMessageIF(messageStyleEnum);
        return messageAS;

    }
    public boolean SmsCodeMessage(String phone){
       return messageAS.SmsCodeMessage(phone);
    }

}
