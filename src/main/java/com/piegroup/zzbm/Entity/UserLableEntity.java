package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-24 
 */

@Data
public class UserLableEntity  implements Serializable {

	private static final long serialVersionUID =  769769114566500297L;

	 /** 用户id . */ 
	private String userid;

	 /** 标签名 . */ 
	private String user_Issue_Labelid;

}
