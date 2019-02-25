package com.piegroup.zzbm.Utils;

//享元工具类,对传进来的对象，判断是pool里面是否存在

import java.util.HashMap;
import java.util.Map;

public class EnjoyUtil {

    private static Map<String, Object> pool = new HashMap<String, Object>();

    public static Object getObject(String code, Object object) {
        if (pool.containsKey(code) && pool.get(code) != null) {
            System.out.println("使用缓存，key为：" + code);
            return pool.get(code);
        } else {

            pool.put(code, object);
            System.out.println("创建状态，key为：" + code);

            return object;

        }
    }
}
