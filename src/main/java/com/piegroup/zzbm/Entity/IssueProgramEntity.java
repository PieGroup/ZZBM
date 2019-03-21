package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-21 
 */

@Data
public class IssueProgramEntity  implements Serializable {

	private static final long serialVersionUID =  3002064377595539456L;

	 // 方案id
	private String issue_Program_Id;

	 // 发布人id
	private String issue_Program_Userid;

	 // 时间
	private String issue_Program_Time;

	 // 标题
	private String issue_Program_Title;

	private String issue_Program_Table;

	 // 内容
	private String issue_Program_Content;

	 // 照片附件id
	private String issue_Program_Pic;

	 // 是否匿名
	private int issue_Program_Anonymous;

	 // 浏览量
	private int issue_Program_Read;

	 // 点赞数
	private int issue_Program_Like;

	 // 打赏金额
	private int issue_Program_Reward;

	 // 方案状态
	private int issue_Program_Issuestatusid;

}
