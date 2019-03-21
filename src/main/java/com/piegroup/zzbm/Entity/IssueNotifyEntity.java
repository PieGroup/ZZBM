package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-21 
 */

@Data
public class IssueNotifyEntity  implements Serializable {

	private static final long serialVersionUID =  972237759326027010L;

	private String notifyid;

	private String userid;

	private String notifyfromid;

	private String notifytype;

	private String notifytitle;

	private String notifycontent;

	private String notifytime;

}
