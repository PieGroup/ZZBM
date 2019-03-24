package com.piegroup.zzbm.Entity;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-16 
 */

@Data
public class UserEntity  implements Serializable {

	private static final long serialVersionUID =  6414314270484693589L;

//	public interface  NotView{} //不可视
//	public interface Visible extends NotView{}  //可视

	// 用户id
//	@JsonView(Visible.class)
	private String user_Id;

	 // 登录名
//	 @JsonView(Visible.class)
	private String user_Login_Name;

	 // 真实姓名
//	 @JsonView(Visible.class)
	private String user_Real_Name;

	 // 手机号
//	 @JsonView(Visible.class)
	private String user_Phone;

	 // 密码
//	@JsonView(NotView.class)
	private String user_Password;

	 // 性别
//	 @JsonView(Visible.class)
	private String user_Sex;

	 // 身份证
//	 @JsonView(Visible.class)
	private String user_Id_Card;

	 // 身份证照片
//	 @JsonView(Visible.class)
	private String user_Id_Card_Url;

//	@JsonView(Visible.class)
	private String user_Head_Url;

	 // qq验证id
//	 @JsonView(NotView.class)
	private String user_Qqid;

	 // 微信openid
//	 @JsonView(NotView.class)
	private String user_Wcid;

	 // 领英验证id
//	 @JsonView(NotView.class)
	private String user_Inid;

	 // 状态id
//	 @JsonView(Visible.class)
	private int user_Statusid;

	 // 积分
//	 @JsonView(Visible.class)
	private String user_Point;

	 // 平台货币
//	 @JsonView(Visible.class)
	private String user_Money;

	//描述
//	@JsonView(Visible.class)
	private String user_Introduction;

	 // 信用
//	 @JsonView(Visible.class)
	private String user_Credit;

	 // 经验
//	 @JsonView(Visible.class)
	private String user_Experience;

	 // 注册时间
//	 @JsonView(Visible.class)
	private Timestamp user_Create_Time;

}
