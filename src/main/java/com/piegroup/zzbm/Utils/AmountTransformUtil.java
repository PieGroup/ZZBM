package com.piegroup.zzbm.Utils;

import java.text.DecimalFormat;

/**
 * 金钱转换
 */
public class AmountTransformUtil {

    /**
     * String 单位为分 转出去为 Double 元
     *
     * @param amount 金额分
     * @return
     */
    public static Double StringToDouble(String amount) {
        return Double.valueOf(amount) / 100;
    }

    /**
     * Double 元  转出去为  String 单位为分
     *
     * @param amount
     * @return
     */
    public static String DoubleToString(Double amount) {

        Double a = amount * 100;
        DecimalFormat format = new DecimalFormat("#");
        return format.format(a);//进行格式化处理，将double转换成string类型
    }

    /**
     * 整数 元  转出去为  String 单位为分
     *
     * @param amount
     * @return
     */
    public static String DoubleToString(int amount) {
        return String.valueOf(amount * 100);
    }
}
