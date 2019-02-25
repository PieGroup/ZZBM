package com.piegroup.zzbm.BS.Bg.Factories;

import com.piegroup.zzbm.Annotation.OrderState;
import com.piegroup.zzbm.BS.Bg.Abstracts.FactoryAS;
import com.piegroup.zzbm.BS.Bg.Abstracts.OrderAS;
import com.piegroup.zzbm.Enums.OrderStatusEnum;
import com.piegroup.zzbm.Utils.EnjoyUtil;
import com.piegroup.zzbm.Utils.UnitIFListUtil;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;


//生成一个策略工厂去完成


public class OrderStatusFactory extends FactoryAS {

    //所在包名
    private static final String UNITIFPACKAGE = "com.piegroup.zzbm.BS.Bg.UnitIF";


    private ClassLoader classLoader = getClass().getClassLoader();

    private static List<Class<? extends OrderAS>> ModifyOrderStatusIFList;//策略列表

    private static Map<Annotation,Class<? extends OrderAS>> ClazzAnnotations; // 保存继承对象和注解对象


    //根据玩家金额产生相对应的策略
    public OrderAS CreateModifyOrderStatusIF(OrderStatusEnum orderStatusEnum) {
        //在策略组里面查找合适的策略
        for (Class<? extends OrderAS> clazz : ModifyOrderStatusIFList) {
            OrderState orderState = handleAnnotation(clazz);//获得该策略的注解
            //判断OrderState的状态，生成相对应的Unit
            if (orderState != null) {
                if (orderState.ORDER_STATE().getCode() == orderStatusEnum.getCode()) {

                    //使用享元模式(享元工具类)，生成一个对象

                    try {
                        return (OrderAS) EnjoyUtil.getObject(orderStatusEnum.getCode(), clazz.newInstance());
                    } catch (Exception e) {
                        throw new RuntimeException("策略获取失败。");
                    }

                }
            }
        }
        throw new RuntimeException("策略获取失败。");
    }

    //单例
    public OrderStatusFactory() {
        init();
    }

    //处理注解，我们传入一个策略类，返回它的注解
    private static OrderState handleAnnotation(Class<? extends OrderAS> clazz) {
        Annotation[] annotations = clazz.getDeclaredAnnotationsByType(OrderState.class);
        if (annotations == null || annotations.length == 0) {
            return null;
        }
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof OrderState) {
                return (OrderState) annotations[i];
            }
        }
        return null;
    }

    //在工厂初始化的时候要初始化策略列表
    private void init() {
        UnitIFListUtil<OrderAS> unitIFListUtil = new UnitIFListUtil();
        ModifyOrderStatusIFList = unitIFListUtil.getObjectUnits(UNITIFPACKAGE);
    }


}

