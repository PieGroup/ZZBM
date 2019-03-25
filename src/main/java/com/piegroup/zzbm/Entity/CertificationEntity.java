package com.piegroup.zzbm.Entity;
import com.piegroup.zzbm.Enums.CertificateEnum;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-25 
 */

@Data
public class CertificationEntity  implements Serializable {

	private static final long serialVersionUID =  4336415950744863505L;

	 /** 认证id . */ 
	private String certification_Id;

	 /** 用户id . */ 
	private String userid;

	 /** 用户认证领域标签 . */ 
	private String user_Labelid;

	 /** 工作单位 . */ 
	private String certification_Employer;

	 /** 认证照片 . */ 
	private String certification_Img;

	 /** 认证介绍 . */ 
	private String certification_Introduce;

	 /** 认证状态 . */ 
	private int certification_Statusid = Integer.parseInt(CertificateEnum.With_Cer.getCode());

	 /** 认证类型 . */ 
	private String certitication_Typeid;

}
