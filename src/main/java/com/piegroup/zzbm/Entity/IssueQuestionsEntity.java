package com.piegroup.zzbm.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class IssueQuestionsEntity implements Serializable {
    private static final long serialVersionUID = 1638611012968716051L;
    private String issue_questions_id; //问题id
    private String issue_questions_userid; //发布问题人id
    private String issue_questions_time; //发布时间
    private String issue_questions_title; //问题标签
    private String issue_questions_generalize; //问题概括
    private int    issue_questions_accept; //问题是否被采纳
    private String issue_questions_points; //问题积分
    private int    issue_questions_issueStatusid; //问题状态id
    private String issue_questions_replyid; //问题回复id
    private String issue_questions_paidLookReply; //是否付费查看回答
    private int    issue_questions_anonymous; //是否匿名
    private String issue_questions_annexid; //问题附件id
}
