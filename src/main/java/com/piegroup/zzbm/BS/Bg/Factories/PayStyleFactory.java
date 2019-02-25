package com.piegroup.zzbm.BS.Bg.Factories;

import com.piegroup.zzbm.Annotation.PayStyle;
import com.piegroup.zzbm.BS.Bg.Abstracts.FactoryAS;
import com.piegroup.zzbm.BS.Bg.Abstracts.PayAS;
import com.piegroup.zzbm.Enums.PayStyleEnum;
import com.piegroup.zzbm.Utils.EnjoyUtil;
import com.piegroup.zzbm.Utils.UnitIFListUtil;

import java.lang.annotation.Annotation;
import java.util.List;

public class PayStyleFactory extends FactoryAS {

    //所在包名
    private static final String PAYSTYLEPACKAGE = "com.piegroup.zzbm.BS.Bg.UnitIF";


    private ClassLoader classLoader = getClass().getClassLoader();

    private static List<Class<? extends PayStyle>> payStyleList;//策略列表


    //根据支付类型相对应的策略
    public PayAS CreatePayStyleIF(PayStyleEnum payStyleEnum) {
        //在策略组里面查找合适的策略
        for (Class<? extends PayStyle> clazz : payStyleList) {
            PayStyle payStyle = handleAnnotation(clazz);//获得该策略的注解
            //判断OrderState的状态，生成相对应的Unit
            if (payStyle != null) {
                if (payStyle.PAY_ENUM().getCode() == payStyleEnum.getCode()) {
                    //使用享元模式(享元工具类)，生成一个对象
                    try {
                        return (PayAS) EnjoyUtil.getObject(payStyleEnum.getCode(), clazz.newInstance());
                    } catch (Exception e) {
                        throw new RuntimeException("策略获取失败。");
                    }

                }
            }
        }
        throw new RuntimeException("策略获取失败。");
    }

    //处理注解，我们传入一个策略类，返回它的注解
    private static PayStyle handleAnnotation(Class<? extends PayStyle> clazz) {
        Annotation[] annotations = clazz.getDeclaredAnnotationsByType(PayStyle.class);
        if (annotations == null || annotations.length == 0) {
            return null;
        }
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof PayStyle) {
                return (PayStyle) annotations[i];
            }
        }
        return null;
    }

    //单例
    public PayStyleFactory() {
        init();
    }


    //在工厂初始化的时候要初始化策略列表
    private void init() {
        UnitIFListUtil<PayStyle> unitIFListUtil = new UnitIFListUtil();
        payStyleList = unitIFListUtil.getObjectUnits(PAYSTYLEPACKAGE);
    }

}
