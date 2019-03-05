package com.piegroup.zzbm.BS.Bg.Abstracts;

import com.piegroup.zzbm.BS.Bg.Interfaces.MessageIF;
import com.piegroup.zzbm.Enums.MessageEnum;

public abstract class MessageAS extends FactoryAS implements MessageIF {

    private static MessageAS messageAS;
    public MessageAS SendMsg(MessageEnum messageEnum){
        messageAS =  CreateMessageIF(messageEnum);
        return messageAS;

    }
    public boolean SmsCodeMessage(String phone){
       return messageAS.SmsCodeMessage(phone);
    }

}
