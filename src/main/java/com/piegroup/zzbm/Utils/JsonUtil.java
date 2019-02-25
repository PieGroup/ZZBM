package com.piegroup.zzbm.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * json 格式输出
 */
public class JsonUtil {

    /**
     * 转成json格式输出
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
