package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.BS.App.Service.Impl.IssueDemandServiceImpl;
import com.piegroup.zzbm.Entity.IssueDemandEntity;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.Utils.TimeUtil;
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
@RequestMapping("/IssueDemand")
@CrossOrigin
@Slf4j
public class IssueDemandController {
    @Autowired
    IssueDemandServiceImpl issueDemandService;


    /**
     * 需求列表
     * @param pageSize
     * @param pageNum
     * @param all  1表示显示全部需求，其他值代表 系统推荐（非必需）
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataVO consultList(@RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
                                    @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum,
                              @RequestParam(value="all",required = false,defaultValue = "1")int all) throws  Exception{
        DataPageSubc list = issueDemandService.list(pageSize, pageNum);

        return ResultUtil.success(list);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public DataVO addConsult(String title,String content,int status,int anonymous,String userid,String annexid) throws Exception{
        IssueDemandEntity i=new IssueDemandEntity();
        String id= TimeUtil.TimestampNow()+ RandomNumberUtil.createRandom(true,5);

        i.setIssue_demand_id(id);
        i.setIssue_demand_title(title);
        i.setIssue_demand_content(content);
        i.setIssue_demand_issueStatusid(status);
        i.setIssue_demand_anonymous(anonymous);
        i.setIssue_demand_userid(userid);
        i.setIssue_demand_annexid(annexid);
        DataPageSubc datas = issueDemandService.Insert(i);

        return ResultUtil.success(datas);
    }

    @RequestMapping("/changeStatus")
    @ResponseBody
    public DataVO changeStatus(int status,String id) throws Exception{
        DataPageSubc change = issueDemandService.change(status, id);
        return ResultUtil.success(change);
    }

}
