package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.BS.App.Service.Impl.IssueDemandServiceImpl;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.DataVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("IssueDemand")
@CrossOrigin
@Slf4j
public class IssueDemandController {

    @Autowired
    IssueDemandServiceImpl issueDemandService;


    /**
     * @// TODO: 2019/3/6 需求list
     * @param pageNum 当前请求页码
     * @param pageSize 界面资源条数
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataVO IssueDemand(@Param("pageSize") int pageSize,@Param("pageNum") int pageNum) throws Exception{
        //throw Exception 直接抛出
        return ResultUtil.success(issueDemandService.list(pageSize,pageNum));
    }

}
