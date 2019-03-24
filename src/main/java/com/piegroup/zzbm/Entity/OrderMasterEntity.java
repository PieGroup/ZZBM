package com.piegroup.zzbm.Entity;
import com.piegroup.zzbm.Enums.OrderStatusEnum;
import com.piegroup.zzbm.Enums.PayStatusEnum;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description  
 * @Author DDLD 
 * @Date 2019-03-24 
 */

@Data
public class OrderMasterEntity  implements Serializable {

	private static final long serialVersionUID =  3343645549874551651L;

	 /** 订单id . */ 
	private String order_Master_Id;

	 /** 用户id . */ 
	private String order_Master_Userid;

	 /** 下单金额 . */ 
	private String order_Master_Money;

	 /** 支付方式 . */ 
	private String order_Master_Piad_Type;

	 /** 订单状态,默认为新下单 . */ 
	private int order_Master_Status = Integer.parseInt(OrderStatusEnum.Unpaid_OrderState.getCode());

	 /** 支付状态,默认未支付 . */ 
	private int order_Master_Pay_Status = Integer.parseInt(PayStatusEnum.Pay_Wait_PayStatus.getCode());

	 /** 创建时间 . */ 
	private Timestamp order_Master_Create_Time;

	 /** 修改时间 . */ 
	private Timestamp order_Master_Update_Time;

	 /** 买家微信openid . */ 
	private String order_Master_Buyer_Openid;

	 /** 买方手机 . */ 
	private String order_Master_Phone;

	public OrderMasterEntity(String order_Master_Id, String order_Master_Userid, String order_Master_Money, String order_Master_Piad_Type, Timestamp order_Master_Create_Time, Timestamp order_Master_Update_Time) {
		this.order_Master_Id = order_Master_Id;
		this.order_Master_Userid = order_Master_Userid;
		this.order_Master_Money = order_Master_Money;
		this.order_Master_Piad_Type = order_Master_Piad_Type;
		this.order_Master_Create_Time = order_Master_Create_Time;
		this.order_Master_Update_Time = order_Master_Update_Time;
	}

	public OrderMasterEntity(){

	}
}
