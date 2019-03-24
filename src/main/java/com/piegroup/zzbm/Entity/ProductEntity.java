package com.piegroup.zzbm.Entity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-24 
 */

@Data
public class ProductEntity  implements Serializable {

	private static final long serialVersionUID =  6651052553630628065L;

	 /** 产品id . */ 
	private String product_Id;

	 /** 产品名 . */ 
	private String product_Name;

	 /** 产品小图url . */ 
	private String product_Icon_Url;

	 /** 金额 . */ 
	private String product_Money;

	 /** 库存 . */ 
	private int product_Stock;

	 /** 商品状态 1-正常 0 -下架 . */ 
	private int product_Status;

	 /** 创建时间 . */ 
	private Timestamp product_Create_Time;

	 /** 商品更新时间 . */ 
	private Timestamp product_Update_Time;

}
