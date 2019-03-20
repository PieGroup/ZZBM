package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-20 
 */

@Data
public class IssueConsultEntity  implements Serializable {

	private static final long serialVersionUID =  4685759918588179930L;

	 // 咨询id
	private String issue_Consult_Id;

	 // 咨询人id
	private String issue_Consult_Userid;

	 // 咨询时间
	private String issue_Consult_Time;

	 // 咨询类型(公开或者私密)
	private int issue_Consult_Type;

	 // 被咨询人的id
	private String issue_Consult_Buserid;

	 // 是否付费查看答案1是0否
	private int issue_Consult_Paidlookreply;

	private int issue_Consult_Read;

	private int issue_Consult_Like;

	 // 咨询状态1：未接受2：回答中3：已完成：4其他
	private int issue_Consult_Issuestatusid;

	 // 咨询积分
	private String issue_Consult_Points;

	 // 咨询标题
	private String issue_Consult_Title;

	 // 咨询内容
	private String issue_Consult_Content;

	 // 是否匿名1是0否
	private int issue_Consult_Anonymous;

	 // 照片附件id
	private String issue_Consult_Annexid;

	 // 需要多少钱查看
	private int issue_Consult_Paid;

	 // 当初花了多少钱咨询
	private int issue_Consult_Value;

	 // 评分
	private String issue_Consult_Mark;

	 // 旁听人数
	private int issue_Consult_Buynum;

}
