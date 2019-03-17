package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-16 
 */

@Data
public class CommentEntity  implements Serializable {

	private static final long serialVersionUID =  3277437757209407688L;

	private String comment_Id;

	 // 谁平论的
	private String comment_User_Id;

	 // 评论的谁，就填谁的id
	private String comment_Father_Id;

	private String comment_Content;

	 // 1评论2回复
	private int comment_Type;

	private String comment_Time;

	 // 踩
	private int comment_Dislike;

	 // 赞
	private int comment_Like;

	 // 1正常2删除
	private int comment_Status;

}
