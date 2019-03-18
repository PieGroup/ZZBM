package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.BS.App.Service.Adapter.MessageAt;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.DataVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MessageController
 * @Description TODO 消息发送控制
 * @Author DDLD
 * @Date 2019/3/17 20:34
 * @ModifyDate 2019/3/17 20:34
 * @Version 1.0
 */
@Controller
@RequestMapping("/message")
@CrossOrigin
public class MessageController {

    @Autowired
    private MessageAt messageAt;


    @RequestMapping(method = RequestMethod.POST, value = "/getCode")
    @ResponseBody
    @ApiOperation("发送验证码")
    public DataVO send(@ApiParam("手机号") @RequestParam("phone") String phone) {
        Assert.notNull(phone, "phone can not be empty ");
        System.out.println("发送手机" + phone);
//        phone = "13870080064";

        return ResultUtil.success(null, messageAt.send(phone));
    }
}
