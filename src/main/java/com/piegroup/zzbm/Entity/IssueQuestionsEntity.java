package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-19 
 */

@Data
public class IssueQuestionsEntity  implements Serializable {

	private static final long serialVersionUID =  57256515016521507L;

	 // 问题id
	private String issue_Questions_Id;

	 // 用户id
	private String issue_Questions_Userid;

	 // 发布时间
	private Timestamp issue_Questions_Time;

	 // 问题标题
	private String issue_Questions_Title;

	 // 问题概括
	private String issue_Questions_Generalize;

	 // 是否被采纳 0没有采纳答案  1已经采纳答案
	private int issue_Questions_Accept;

	 // 赏金
	private int issue_Questions_Value;

	 // 积分
	private int issue_Questions_Points;

	 // 问题状态
	private int issue_Questions_Issuestatusid;

	 // 采纳答案id
	private String issue_Questions_Replyid;

	 // 回答数
	private int issue_Questions_Answernum;

	 // 是否匿名
	private int issue_Questions_Anonymous;

	 // 照片附件id
	private String issue_Questions_Annexid;

}
