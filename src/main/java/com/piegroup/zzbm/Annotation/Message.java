package com.piegroup.zzbm.Annotation;



import com.piegroup.zzbm.Enums.MessageEnum;

import java.lang.annotation.*;


@Repeatable(Messages.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public  @interface Message {
    MessageEnum MESSAGE_ENUM() default MessageEnum.Sms_Message;

}

