package com.piegroup.zzbm.VO;

import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import lombok.Data;

import java.io.Serializable;

/**
 * 定义一个返回前台数据模板
 * @param <T>
 */
@Data
public class DataVO<T> implements Serializable {


    private static final long serialVersionUID = -6642078494588377182L;

    private String code;   //返回规定的 code
    private String message; //数据信息
    private DataPageSubc data; //返回数据


}
