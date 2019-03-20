package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-20 
 */

@Data
public class IssueNotifyEntity  implements Serializable {

	private static final long serialVersionUID =  5763237419308424686L;

	private String notifyid;

	private String notifyfromid;

	private String notifytype;

	private String notifytitle;

	private String notifycontent;

	private String notifytime;

}
