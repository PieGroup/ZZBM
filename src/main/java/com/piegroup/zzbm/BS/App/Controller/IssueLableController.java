package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.BS.App.Service.Impl.IssueLableServiceImpl;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.DataVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Lable")
@CrossOrigin
@Slf4j
public class IssueLableController {

    @Autowired
    IssueLableServiceImpl issueLableService;

    @RequestMapping("/allLable")
    @ResponseBody
    public DataVO all() throws Exception{
        return ResultUtil.success(issueLableService.allLable());

    }

    @RequestMapping("/subLable")
    @ResponseBody
    public DataVO sub(@RequestParam(value="pId",required = true,defaultValue = "10000")int pId) throws Exception{

        return ResultUtil.success(issueLableService.subLable(pId));

    }

    @RequestMapping("/parentLable")
    @ResponseBody
    public DataVO parent() throws Exception{
        return ResultUtil.success(issueLableService.allParentLable());
    }

}
