package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-19 
 */

@Data
public class IssueRecordEntity  implements Serializable {

	private static final long serialVersionUID =  7180705088742316489L;

	 // 问题id
	private String question_Record_Id;

	 // 用户id
	private String question_Record_Replynum;

	 // 点赞
	private int question_Record_Like;

	 // 举报
	private int question_Record_Report;

	 // 关注
	private int question_Record_Attention;

	 // 阅读量
	private String question_Record_Readingvolume;

}
