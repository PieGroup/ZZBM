package com.piegroup.zzbm.BS.Bg.Interfaces;


//消息通知接口


import com.piegroup.zzbm.BS.Bg.Abstracts.MessageAS;
import com.piegroup.zzbm.Enums.MessageStyleEnum;

public interface MessageIF {

    public MessageAS SendMsg(MessageStyleEnum messageStyleEnum);

}
