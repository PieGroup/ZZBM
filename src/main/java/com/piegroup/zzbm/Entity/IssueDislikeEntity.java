package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-20 
 */

@Data
public class IssueDislikeEntity  implements Serializable {

	private static final long serialVersionUID =  8604114467556794714L;

	private String dislike_Id;

	private String dislike_Item;

	private String dislike_User;

	public IssueDislikeEntity(String dislike_Item, String dislike_User) {
		this.dislike_Item = dislike_Item;
		this.dislike_User = dislike_User;
	}

	// 点赞时间
	private String dislike_Time;

}
