package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-16 
 */

@Data
public class UserEntity  implements Serializable {

	private static final long serialVersionUID =  4993653180656144194L;

	 // 用户id
	private String user_Id;

	 // 登录名
	private String user_Login_Name;

	 // 真实姓名
	private String user_Real_Name;

	 // 手机号
	private String user_Phone;

	 // 密码

	private String user_Password;

	 // 性别
	private int user_Sex;

	 // 身份证
	private String user_Id_Card;

	 // 身份证照片
	private String user_Id_Card_Url;

	 // qq验证id
	private String user_Qqid;

	 // 微信验证id
	private String user_Wcid;

	 // 领英验证id
	private String user_Inid;

	 // 状态id
	private int user_Statusid;

	 // 积分
	private String user_Point;

	 // 平台货币
	private String user_Money;

	 // 信用
	private String user_Credit;

	 // 经验
	private String user_Experience;

	 // 注册时间
	private String user_Create_Time;

}
