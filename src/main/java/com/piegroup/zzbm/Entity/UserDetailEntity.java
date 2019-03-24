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
public class UserDetailEntity  implements Serializable {

	private static final long serialVersionUID =  9143279472111386835L;

	 /** 用户id . */ 
	private String userid;

	 /** 赞同 . */ 
	private int approve;

	 /** 收藏 . */ 
	private int collect;

	 /** 关注 . */ 
	private int attention;

	 /** 粉丝 . */ 
	private int fan;

}
