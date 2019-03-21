package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-21 
 */

@Data
public class WcuserEntity  implements Serializable {

	private static final long serialVersionUID =  496652935293703062L;

	private String openid;

	private String session_Key;

	private String unionid;

	private String headimgurl;

	private String subscribe;

	private String nickname;

	private String sex;

	private String language;

	private String city;

	private String province;

	private String country;

	private String subscribe_Time;

	private String remark;

	private String groupid;

	private String tagid_List;

	private String subscribe_Scene;

	private String qr_Scene;

	private String qr_Scene_Str;

}
