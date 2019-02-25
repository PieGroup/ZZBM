package com.piegroup.zzbm.Annotation;



import com.piegroup.zzbm.Enums.MessageStyleEnum;

import java.lang.annotation.*;


@Repeatable(Messages.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public  @interface Message {
    MessageStyleEnum MESSAGE_ENUM() default MessageStyleEnum.SMSNOTICE;

}

