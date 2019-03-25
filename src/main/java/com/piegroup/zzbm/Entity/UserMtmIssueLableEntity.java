package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-25 
 */

@Data
public class UserMtmIssueLableEntity  implements Serializable {

	private static final long serialVersionUID =  1418194338312229679L;

	 /** 用户id . */ 
	private String userid;

	 /** 用户感兴趣的问题标签id . */ 
	private String issue_Lableid;

}
