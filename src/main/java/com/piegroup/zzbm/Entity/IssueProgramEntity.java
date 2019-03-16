package com.piegroup.zzbm.Entity;


import lombok.Data;

import java.io.Serializable;

/**
 * @// TODO: 2019/3/1  方案表
 */


public class IssueProgramEntity implements Serializable {

    private static final long serialVersionUID = 4606837559466956679L;

    private String issue_program_id; //方案id
    private String issue_program_userid; //方案发布人id
    private String issue_program_time; //方案发布时间
    private String issue_program_title; //方案标签
    private String issue_program_content; //方案内容
    private int issue_program_anonymous; //是否匿名
    private String issue_program_reward; //打赏
    private int issue_program_issueStatusid; //方案状态id
    private String issue_program_annexid; //照片附件id

    @Override
    public String toString() {
        return "IssueProgramEntity{" +
                "issue_program_id='" + issue_program_id + '\'' +
                ", issue_program_userid='" + issue_program_userid + '\'' +
                ", issue_program_time='" + issue_program_time + '\'' +
                ", issue_program_title='" + issue_program_title + '\'' +
                ", issue_program_content='" + issue_program_content + '\'' +
                ", issue_program_anonymous=" + issue_program_anonymous +
                ", issue_program_reward='" + issue_program_reward + '\'' +
                ", issue_program_issueStatusid=" + issue_program_issueStatusid +
                ", issue_program_annexid='" + issue_program_annexid + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIssue_program_id() {
        return issue_program_id;
    }

    public void setIssue_program_id(String issue_program_id) {
        this.issue_program_id = issue_program_id;
    }

    public String getIssue_program_userid() {
        return issue_program_userid;
    }

    public void setIssue_program_userid(String issue_program_userid) {
        this.issue_program_userid = issue_program_userid;
    }

    public String getIssue_program_time() {
        return issue_program_time;
    }

    public void setIssue_program_time(String issue_program_time) {
        this.issue_program_time = issue_program_time;
    }

    public String getIssue_program_title() {
        return issue_program_title;
    }

    public void setIssue_program_title(String issue_program_title) {
        this.issue_program_title = issue_program_title;
    }

    public String getIssue_program_content() {
        return issue_program_content;
    }

    public void setIssue_program_content(String issue_program_content) {
        this.issue_program_content = issue_program_content;
    }

    public int getIssue_program_anonymous() {
        return issue_program_anonymous;
    }

    public void setIssue_program_anonymous(int issue_program_anonymous) {
        this.issue_program_anonymous = issue_program_anonymous;
    }

    public String getIssue_program_reward() {
        return issue_program_reward;
    }

    public void setIssue_program_reward(String issue_program_reward) {
        this.issue_program_reward = issue_program_reward;
    }

    public int getIssue_program_issueStatusid() {
        return issue_program_issueStatusid;
    }

    public void setIssue_program_issueStatusid(int issue_program_issueStatusid) {
        this.issue_program_issueStatusid = issue_program_issueStatusid;
    }

    public String getIssue_program_annexid() {
        return issue_program_annexid;
    }

    public void setIssue_program_annexid(String issue_program_annexid) {
        this.issue_program_annexid = issue_program_annexid;
    }
}
