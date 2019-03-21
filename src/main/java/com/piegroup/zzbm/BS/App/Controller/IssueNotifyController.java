package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.BS.App.Service.Impl.IssueNotifyServiceImpl;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/IssueNotify")
@CrossOrigin
@Slf4j
public class IssueNotifyController {
    @Autowired
    IssueNotifyServiceImpl issueNotifyService;

    @RequestMapping("/notifyList")
    @ResponseBody
    public DataVO notifyList(@RequestParam(value = "uid",required = false,defaultValue = "0000")String uid,
                             @RequestParam(value = "type",required = false,defaultValue = "0")String type,
                             @RequestParam(value = "pageSize",required = false,defaultValue = "15")int pageSize,
                             @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum)throws Exception {
        DataPageSubc dataPageSubc = issueNotifyService.myNotify(uid, type, pageSize, pageNum);

        return ResultUtil.success(dataPageSubc);
    }


    @RequestMapping("/read")
    @ResponseBody
    public DataVO read(@RequestParam(value = "nid",required = false,defaultValue = "0000")String nid)throws Exception {
        DataPageSubc read = issueNotifyService.read(nid);

        return ResultUtil.success(read);
    }
}
