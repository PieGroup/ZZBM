package com.piegroup.zzbm.Entity;


import lombok.Data;

/**
 * @// TODO: 2019/3/1 需求表
 */

@Data
public class IssueDemandEntity {
    private String issue_demand_id; //需求id
    private String issue_demand_time; //需求发布时间
    private String issue_demand_title; //需求标签
    private String issue_demand_content; //需求内容
    private int issue_demand_issueStatusid; //需求状态
    private int issue_demand_anonymous; //需求是否匿名
    private String issue_demand_userid; //需求发布人
    private String issue_demand_annexid; //需求照片附件id
}
