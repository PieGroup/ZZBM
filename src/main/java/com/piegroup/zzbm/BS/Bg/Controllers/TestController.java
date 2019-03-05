package com.piegroup.zzbm.BS.Bg.Controllers;

import com.piegroup.zzbm.BS.Bg.Service.Impl.OrderServiceImpl;
import com.piegroup.zzbm.BS.Bg.Service.MessageService;
import com.piegroup.zzbm.BS.Bg.Service.PayService;
import com.piegroup.zzbm.Entity.OrderMasterEntity;
import com.piegroup.zzbm.Enums.MessageStyleEnum;
import com.piegroup.zzbm.Enums.OrderStatusEnum;
import com.piegroup.zzbm.Enums.PayStyleEnum;
import com.piegroup.zzbm.Utils.CookiesUtil;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("test/")
//@CacheConfig(cacheNames = "productInfo")
@Slf4j
@CrossOrigin
public class TestController {


    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    MessageService messageService;
    @Autowired
    PayService payService;

    /**
     * @// TODO: 通过用户人id 分页查询，显示结果
     */
    @RequestMapping("test1")
    @ResponseBody
//    @Cacheable(key = "'redis_'+1",unless = "#result.getCode() != '200'")
    public DataVO<List<OrderMasterEntity>> OrderList() throws Exception{
       //UserId 用户人Id / pageNum 第几页 默认第一页 /pageSize 需要多长的数据默认一条
        DataPageSubc dataPageSubc = orderService.OrderListSvImpl("林昊",1,5);
       return ResultUtil.success(dataPageSubc);

    }

    /**
     * @// TODO:  通过订单Id 修改订单状态
     * @return 随便显示数据，自行可以修改
     */
    @RequestMapping("test2")
    @ResponseBody
    public String ModifyOrderStateSv() throws Exception{
        //orderId 订单Id  / orderEnum 订单枚举


        return "修改订单状态："+orderService.ModifyOrderStateSv("123456", OrderStatusEnum.ORDERSTATE_CANCEL);
    }

    /**
     * @// TODO:  通过要发的信息类型，匹配，然后调用函数
     * @return 随便显示数据，自行可以修改
     */
    @RequestMapping("test3")
    @ResponseBody
    public String MessageSmsCode(){
        return "验证码发送："+messageService.SendSMSCodeNoticeSv(MessageStyleEnum.SIGNUP_SMSCODENOTICE);
    }

    /**
     * @// TODO:  通过订单id 金额支付，或许不需要传入金额，可以去数据库读取订单的金额
     * @return 随便显示数据，自行可以修改
     */
    @RequestMapping("test4")
    @ResponseBody
    public String Pay() throws Exception{
        //OrderId 订单id / Amount 金额 单位为分 / payEnum 支付方式枚举


        return "支付："+payService.PayGoodsSv("123456", PayStyleEnum.PAYSTYLE_WCPAY_H5);
    }

    @RequestMapping("test5")
    @ResponseBody
    public Object session(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("map"));
        System.out.println("8081端口：");
        return map;
    }

    @RequestMapping("test6")
    public String index(Model model) throws Exception{
        String success = "调用成功！";
        model.addAttribute("test",success);
        return "index";
    }

    @GetMapping("test7")
    @ResponseBody
    public String cookies(@Param("s") int s, HttpServletRequest request, HttpServletResponse response){

        if (s==1){
           return CookiesUtil.saveCookies("123",response,request) ? "true": "fales";
        }
        else if (s==2){
            return CookiesUtil.printCookies("123",request);
        }
        else if (s==3){
           return CookiesUtil.clearCookies("123",response,request) ? "true": "fales";
        }

        return "else";

    }


    @RequestMapping("login")
    public String login() throws Exception{
        return "extra-login-light.html";
    }
    @RequestMapping("index")
    public String login2()throws Exception{
        return "index";
    }

    @RequestMapping("test10")
    @ResponseBody
    public int test10(){
        return orderService.test();
    }

}
