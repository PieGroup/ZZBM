package com.piegroup.zzbm.Entity;


import lombok.Data;

import java.io.Serializable;

/**
 * @// TODO: 2019/3/1 需求表
 */

@Data
public class IssueLableEntity implements Serializable {


    private static final long serialVersionUID = -4062991994113369968L;

    private String issue_lable_id;      //id
    private String issue_lable_name;    //name
    private String issue_lable_url;     //图片地址
    private int issue_lable_sort;       //应用频率
}
