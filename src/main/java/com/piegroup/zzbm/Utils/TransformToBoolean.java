package com.piegroup.zzbm.Utils;

public class TransformToBoolean {

    /**
     * integer 转 Boolean
     * @param integer
     * @return
     */
    public static boolean IntegerToBoolean(Integer integer){
        return integer > 0 ? true:false;
    }

    /**
     * string 转Boolean
     * @param s
     * @return
     */
    public static boolean StringToBoolean(String s){
        if ("".equals(s))
            return false;
        else if (s == null){
            return false;
        }
        return true;
    }
}
