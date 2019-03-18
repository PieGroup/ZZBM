package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-17 
 */

@Data
public class UserStatusEntity  implements Serializable {

	private static final long serialVersionUID =  1929192209387593300L;

	 // 用户状态id
	private int user_Status_Id;

	 // 状态名
	private String user_Status_Name;

	 // 更新时间
	private String user_Status_Update_Time;

	 // 添加时间
	private String user_Status_Create_Time;

	 // 操作人id
	private String user_Status_Adminid;

}
