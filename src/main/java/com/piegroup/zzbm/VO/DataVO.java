package com.piegroup.zzbm.VO;

import com.piegroup.zzbm.VO.SubC.DataPageSubc;

import java.io.Serializable;

/**
 * 定义一个返回前台数据模板
 * @param <T>
 */
public class DataVO<T> implements Serializable {


    private static final long serialVersionUID = 8550772628657990481L;


    private String code;   //返回规定的 code
    private String message; //数据信息
    private DataPageSubc data; //返回数据


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public DataPageSubc getData() {
        return data;
    }

    public void setData(DataPageSubc data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "VO_data{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
