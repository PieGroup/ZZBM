package com.piegroup.zzbm.Annotation;


import com.piegroup.zzbm.Enums.OrderStatusEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.piegroup.zzbm.Enums.OrderStatusEnum.Unpaid_OrderState;


//使用注解来判断具体使用的是订单改变状态的方法使用方法
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderState {

    OrderStatusEnum ORDER_STATE() default Unpaid_OrderState;
}


