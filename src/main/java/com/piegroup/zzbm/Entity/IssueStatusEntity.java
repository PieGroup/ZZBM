package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-19 
 */

@Data
public class IssueStatusEntity  implements Serializable {

	private static final long serialVersionUID =  6340461602982835478L;

	 // 状态id
	private int issue_Status_Id;

	 // 状态名
	private String issue_Status_Name;

}
