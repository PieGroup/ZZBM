package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-20 
 */

@Data
public class IssueDemandEntity  implements Serializable {

	private static final long serialVersionUID =  4130996315660790681L;

	 // 需求id
	private String issue_Demand_Id;

	 // 需求时间
	private String issue_Demand_Time;

	 // 需求标题
	private String issue_Demand_Title;

	 // 需求内容
	private String issue_Demand_Content;

	 // 阅读量
	private int issue_Demand_Read;

	 // 点赞量
	private int issue_Demand_Like;

	 // 需求状态
	private int issue_Demand_Issuestatusid;

	 // 是否匿名
	private int issue_Demand_Anonymous;

	 // 提出人
	private String issue_Demand_Userid;

	 // 照片附件id
	private String issue_Demand_Annexid;

}
