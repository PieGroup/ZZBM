package com.piegroup.zzbm.Utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.piegroup.zzbm.Enums.SMSNoticeEnum;
import com.piegroup.zzbm.VO.R_SmsCode;

import javax.servlet.http.HttpSession;

/**
 * 获取验证码
        * 1、发送验证码成功
        * 4、验证码获取频繁
        * 3、发送失败
        */


public class SMSCodeUtil {

    /**
     * @param phone   手机
     * @param session
     * @param id
     * @return
     */
    public static Boolean SendCode(String phone, HttpSession session, SMSNoticeEnum id, SMSNoticeEnum accessId, SMSNoticeEnum accessKeySecre, SMSNoticeEnum SignName, SMSNoticeEnum SMSTemplateCode) {


        SMSNoticeEnum SMSNoticeEnumAccessId = SMSNoticeEnum.AccessId;
        SMSNoticeEnum SMSNoticeEnumAccessKeySecre = SMSNoticeEnum.AccessKeySecre;
        SMSNoticeEnum SMSNoticeEnumSignName = SMSNoticeEnum.SignName;
        SMSNoticeEnum codeEnumSMSTemplateSMSNotice = SMSNoticeEnum.SMSTemplateCode;





        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = SMSNoticeEnumAccessId.getValue();//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = SMSNoticeEnumAccessKeySecre.getValue();//你的accessKeySecret，参考本文档步骤2



        //获得随机验证码
        String usercode = RandomNumberUtil.createRandom(true, 6);
        //保存phone code到session中
        if (SMSCodeUtil.Save(phone, usercode, session) == false) {
            //4代表获取验证码频繁
            System.out.println("获取验证码频繁");
            return false;
        }
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(SMSNoticeEnumSignName.getValue());
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode(codeEnumSMSTemplateSMSNotice.getValue());
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"" + usercode + "\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常

        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            System.out.println("网络异常");
            return false;
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
            System.out.println("验证码发送成功！");
            return true;
        }

        return false;
    }

    //保存验证码
    public static boolean Save(String phone, String code, HttpSession session) {

        if (session.getAttribute(phone) != null) {
            return false;
        }
        try {
            R_SmsCode r_smsCode = (R_SmsCode) EnjoyUtil.getObject(phone,new R_SmsCode());
            r_smsCode.setPhone(phone);
            r_smsCode.setCode(MD5Util.MD5Encode(code, "utf8"));

            session.setAttribute(phone, r_smsCode);
        } catch (Exception e) {

            return false;
        }
        return true;
    }


    //判断验证码

    /**
     * @param userPhone
     * @param code
     * @param session
     * @return "5"验证码过期 ， ”4“系统异常, "1"验证码成功，”2“验证码错误
     */
    public static String JudgeCode(String userPhone, String code, HttpSession session) {


        R_SmsCode r_smsCode = (R_SmsCode) session.getAttribute(userPhone);
        if (r_smsCode == null) {
            return "5";
        } else {
            String mdCode;
            try {
                mdCode = MD5Util.MD5Encode(code, "utf8");

            } catch (Exception e) {
                System.out.println("加密失败");
                return "4";
            }
            if (mdCode.equals(r_smsCode.getCode())) {
                return "1";
            } else {
                //验证码错误
                return "2";
            }
        }
    }


}
