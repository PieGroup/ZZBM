package com.piegroup.zzbm.Entity;

import lombok.Data;

import java.io.Serializable;

/**
 *咨询表
 */

@Data
public class IssueConsultEntity implements Serializable {


    private String issue_consult_id;  //咨询id
    private String issue_consult_userid; //咨询人id
    private String issue_consult_time; //咨询时间
    private int issue_consult_type; //咨询类型
    private String issue_consult_buserid; //被咨询人id
    private int  issue_consult_paidLookReply; //是否付费查看答案
    private int issue_consult_issueStatusid; //咨询状态
    private String issue_consult_points ; //咨询积分
    private String issue_consult_title; //咨询标题
    private String issue_consult_content; //咨询内容
    private int issue_consult_anonymous; //是否匿名
    private String issue_consult_annexid;//照片附件id
    private String issue_consult_paid; //需要多少积分查看答案
    // 价值
    private int issue_consult_value;

    // 评分
    private String issue_consult_mark;

    // 旁听人数
    private int issue_consult_buynum;

}

