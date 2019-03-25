package com.piegroup.zzbm.Entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-24 
 */

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailEntity  implements Serializable {

	private static final long serialVersionUID =  5269826502710793233L;

	 /** 订单详情id . */ 
	private String order_Detail_Id;

	 /** 订单id . */ 
	private String order_Detail_Orderid;

	 /** 产品id . */ 
	private String order_Detail_Productid;

	 /** 创建时间 . */ 
	private Timestamp order_Detail_Create_Time;

	 /** 更新时间 . */ 
	private Timestamp order_Detail_Update_Time;

	 /** 数量 . */ 
	private int order_Detail_Quantity;

	 /** 单价(单位为分) . */ 
	private String order_Detail_Price;

	public OrderDetailEntity(String order_Detail_Id, String order_Detail_Orderid, String order_Detail_Productid, Timestamp order_Detail_Create_Time, Timestamp order_Detail_Update_Time, int order_Detail_Quantity, String order_Detail_Price) {
		this.order_Detail_Id = order_Detail_Id;
		this.order_Detail_Orderid = order_Detail_Orderid;
		this.order_Detail_Productid = order_Detail_Productid;
		this.order_Detail_Create_Time = order_Detail_Create_Time;
		this.order_Detail_Update_Time = order_Detail_Update_Time;
		this.order_Detail_Quantity = order_Detail_Quantity;
		this.order_Detail_Price = order_Detail_Price;
	}

	public OrderDetailEntity(){

	}
}
