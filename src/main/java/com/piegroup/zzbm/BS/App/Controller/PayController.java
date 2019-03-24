package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.Annotation.Authorization;
import com.piegroup.zzbm.Annotation.CurrentUser;
import com.piegroup.zzbm.BS.App.Service.OrderServiceIF;
import com.piegroup.zzbm.DTO.ProductDTO;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName PayController
 * @Description TODO 支付
 * @Author DDLD
 * @Date 2019/3/24 11:49
 * @ModifyDate 2019/3/24 11:49
 * @Version 1.0
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    OrderServiceIF orderServiceIF;


    @RequestMapping("/create")
    @ResponseBody
    @Authorization
    public DataVO WC_H5_Pay(@CurrentUser UserEntity userEntity, ProductDTO productDTO) {
       return null;
    }

}
