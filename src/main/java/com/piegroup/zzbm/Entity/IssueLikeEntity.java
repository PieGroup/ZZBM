package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-20 
 */

@Data
public class IssueLikeEntity  implements Serializable {

	private static final long serialVersionUID =  5752572125124634133L;

	private String like_Id;

	private String like_Item;

	private String like_User;

	public IssueLikeEntity(String like_Item, String like_User) {
		this.like_Item = like_Item;
		this.like_User = like_User;
	}

	// 点赞时间
	private String like_Time;

}
