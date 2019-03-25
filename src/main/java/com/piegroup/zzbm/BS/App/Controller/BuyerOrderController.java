package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.Annotation.Authorization;
import com.piegroup.zzbm.Annotation.CurrentUser;
import com.piegroup.zzbm.BS.App.Service.OrderServiceIF;
import com.piegroup.zzbm.DTO.OrderDTO;
import com.piegroup.zzbm.DTO.ProductDTO;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import com.piegroup.zzbm.VO.SubC.ProductSubC;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BuyerOrderController
 * @Description TODO 买家订单
 * @Author DDLD
 * @Date 2019/3/24 11:57
 * @ModifyDate 2019/3/24 11:57
 * @Version 1.0
 */

@Controller
@RequestMapping("/buyerOrder")
@CrossOrigin
@Slf4j
public class BuyerOrderController {

    @Autowired
    OrderServiceIF orderServiceIF;

    //创建订单

    @PostMapping("/create")
    @ResponseBody
    @Authorization
    public DataVO create(@CurrentUser UserEntity userEntity, @RequestBody ProductDTO productDTO) {
        Assert.notNull(productDTO, "商品id 不能为空!");
        log.info("productDTO={}", productDTO);
        if (productDTO.getProductSubCs() == null || productDTO.getProductSubCs().get(0).getProduct_id().equals("")) {
            return ResultUtil.error(new DataPageSubc<>(), ExceptionEnum.Create_Order_Exception);
        }
        //判断用户是否是微信用户
        if (userEntity.getUser_Wcid() == null || userEntity.getUser_Wcid().equals("")){
            return ResultUtil.error(new DataPageSubc<>(), ExceptionEnum.User_Paid_Not_Stand_Exception);
        }

//        ProductDTO productDTO1 = new ProductDTO();
//
//        List<ProductSubC> productSubCS = new ArrayList<>();
//        ProductSubC productSubC = new ProductSubC("1",1);
//        ProductSubC productSubC1 = new ProductSubC("2",12);
//        productSubCS.add(productSubC);
//        productSubCS.add(productSubC1);productDTO1.setProductSubCs(productSubCS);
        OrderDTO orderDTO = orderServiceIF.create(userEntity, productDTO);
        DataPageSubc dataPageSubc = new DataPageSubc();

        Map map = new HashMap();
        map.put("orderId", orderDTO.getOrderMasterEntity().getOrder_Master_Id());

        dataPageSubc.setData(map);
        return ResultUtil.success(dataPageSubc);

//        return productDTO1;

    }

    //订单列表

    @GetMapping("/list")
    @ResponseBody
    @Authorization
    public DataVO list(@CurrentUser UserEntity userEntity, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        if (userEntity == null) {
            return ResultUtil.error(new DataPageSubc<>(), ExceptionEnum.No_Login_Exception);
        }

        return ResultUtil.success(orderServiceIF.findList(userEntity.getUser_Id(), pageNum, pageSize));

    }

    //订单详情
    @GetMapping("/detail")
    @ResponseBody
    @Authorization
    public DataVO detail(@CurrentUser UserEntity userEntity, @RequestParam("orderId") String orderId) {

        if (userEntity == null) {
            return ResultUtil.error(new DataPageSubc<>(), ExceptionEnum.No_Login_Exception);
        }


        OrderDTO orderDTO = orderServiceIF.findOne(orderId);

        //匹配用户
        if (!userEntity.getUser_Id().equals(orderDTO.getOrderMasterEntity().getOrder_Master_Id())){
            return ResultUtil.error(new DataPageSubc<>(),ExceptionEnum.Order_Not_Match_User_Error);
        }

        DataPageSubc dataPageSubc = new DataPageSubc();

        dataPageSubc.setData(orderDTO);

        return ResultUtil.success(dataPageSubc);

    }

    //取消订单
    @PostMapping("/cancel")
    @ResponseBody
    @Authorization
    public DataVO cancel(@CurrentUser UserEntity userEntity, @RequestParam("orderId") String orderId) {

        OrderDTO orderDTO = orderServiceIF.findOne(orderId);

        //匹配用户
        if (!userEntity.getUser_Id().equals(orderDTO.getOrderMasterEntity().getOrder_Master_Id())){
            return ResultUtil.error(new DataPageSubc<>(),ExceptionEnum.Order_Not_Match_User_Error);
        }
        orderServiceIF.cancel(orderDTO);
        return ResultUtil.success(new DataPageSubc<>(),ExceptionEnum.Success);
    }

}
