package com.piegroup.zzbm.Entity;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-16 
 */

@Data
public class TokenEntity implements Serializable {

	private static final long serialVersionUID =  7907986230881001166L;

	private String userid;

	private String token;

	 // 创建时间
	private String create_Time;

	 // 更新时间
	private int update_Time = 30;



}
