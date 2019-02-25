package com.piegroup.zzbm.BS.Bg.Factories;

import com.piegroup.zzbm.Annotation.Message;
import com.piegroup.zzbm.BS.Bg.Abstracts.MessageAS;
import com.piegroup.zzbm.Enums.MessageStyleEnum;
import com.piegroup.zzbm.Utils.EnjoyUtil;
import com.piegroup.zzbm.Utils.UnitIFListUtil;

import java.lang.annotation.Annotation;
import java.util.List;

public class MessagesFactory extends MessageAS {

    //所在包名
    private static final String PAYSTYLEPACKAGE = "com.piegroup.zzbm.BS.Bg.UnitIF";


    private ClassLoader classLoader = getClass().getClassLoader();

    private static List<Class<? extends MessageAS>> MessageList;//策略列表
    public MessageAS CreateMessageIF(MessageStyleEnum messageStyleEnum) {
        //在策略组里面查找合适的策略
        for (Class<? extends MessageAS> clazz : MessageList) {
            Message messages = handleAnnotation(clazz);//获得该策略的注解
            //判断OrderState的状态，生成相对应的Unit
            if (messages != null) {
                if (messages.MESSAGE_ENUM().getCode() == messageStyleEnum.getCode()) {
                    //使用享元模式(享元工具类)，生成一个对象
                    try {
                        return (MessageAS) EnjoyUtil.getObject(messageStyleEnum.getCode(), clazz.newInstance());
                    } catch (Exception e) {
                        throw new RuntimeException("策略获取失败。");
                    }

                }
            }
        }
        throw new RuntimeException("策略获取失败。");
    }
    //处理注解，我们传入一个策略类，返回它的注解
    private static Message handleAnnotation(Class<? extends MessageAS> clazz) {
        //getDeclaredAnnotations() 只能拿单个注释并且，不拿它继承父类的注释
        Annotation[] annotations = clazz.getDeclaredAnnotationsByType(Message.class);
        if (annotations == null || annotations.length == 0) {
            return null;
        }
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof Message) {
                return (Message) annotations[i];
            }
        }
        return null;
    }

    //单例
    public MessagesFactory() {
        init();
    }


    //在工厂初始化的时候要初始化策略列表
    private void init() {
        UnitIFListUtil<MessageAS> unitIFListUtil = new UnitIFListUtil();
        MessageList = unitIFListUtil.getObjectUnits(PAYSTYLEPACKAGE);
    }

}
