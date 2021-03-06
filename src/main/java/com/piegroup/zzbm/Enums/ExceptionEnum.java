package com.piegroup.zzbm.Enums;


import com.piegroup.zzbm.BS.Bg.Interfaces.CodeIF;
import lombok.Data;


public enum ExceptionEnum  {

    No_Permission_Exception("1000000","没有该权限"),
    No_Login_Exception("1000001","没有登录"),
    Unknown_Exception("-1","未知错误"),
    Data_Link_Exception("100002","数据库连接错误"),
    Success("0","成功！"),
    Lock_False_Exception("100003","加锁失败！"),
    Transform_Enum_Exception("100004","枚举转换出现问题！"),
    Pay_Error_Exception("100005","支付失败"),
    Login_Out_Time_Exception("100006","登录过时"),
    Login_Password_Error_Exception("100007","登录密码错误"),
    No_Register_Exception("100008","没有注册"),
    Black_Data_Exception("100009","返回数据出现问题"),
    Sms_Code_Expire_Exception("100010","验证码过期"),
    Sms_Code_Error_Exception("100011","验证码错误"),
    Netword_Exception("100012","网络异常"),
    Phone_Null_Exception("100013","手机号不能为空"),
    Send_Fail_Exception("100014","发送失败"),
    Upload_Success("100015","上传成功"),
    Upload_Fail("100016","上传失败"),
    Param_Exception("100017","参数问题"),
    Sms_Code_Frequently_Exception("100018","验证码获取频繁"),
    Request_Frequently_Exception("100019","请求频繁"),
    Request_Frequently_Error_Exception("100020","重复提交出现异常"),
    Request_Type_Error_Exception("100021","请求方式有问题"),
    Wc_Login_Exception("100022","微信默认登录出问题"),
    Wc_User_Request_Exception("100023","微信获取用户信息问题"),
    Upload_Null("100024","文件为空"),
    Create_Order_Exception("100025","下单失败"),
    Product_Null_Exception("100026","商品不存在"),
    Product_Stock_Error("100027","商品库存异常"),
    Label_Null_Exception("100028","标签不存在"),
    Order_Null_Exception("100029","订单不存在"),
    Order_Status_Error("100030","订单状态异常"),
    Order_UP_Status_Error("100031","更新订单状态失败"),
    Order_Not_Match_User_Error("100032","该订单不属于该用户"),
    User_Paid_Not_Stand_Exception("100033","该版本不能支持支付"),
    WC_Pay_Notify_Verify_Error("100034","微信支付异步校验金额不通过"),
    Wc_User_Info_Error("100035","保存用户信息错误")
    ;

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
