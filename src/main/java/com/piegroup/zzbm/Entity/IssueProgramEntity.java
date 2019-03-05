package com.piegroup.zzbm.Entity;


import lombok.Data;

import java.io.Serializable;

/**
 * @// TODO: 2019/3/1  方案表
 */

@Data
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
}
