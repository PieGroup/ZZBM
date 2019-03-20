package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.BS.App.Service.Impl.IssueDemandServiceImpl;
import com.piegroup.zzbm.Entity.IssueDemandEntity;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.Utils.TimeUtil2;
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
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataVO consultList(@RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
                                    @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum) throws  Exception{
        DataPageSubc list = issueDemandService.list(pageSize, pageNum);

        return ResultUtil.success(list);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public DataVO addConsult(@RequestParam(value = "userid",required = false,defaultValue = "0000")String userid,
                             @RequestParam(value = "title",required = false,defaultValue = "0000")String title,
                             @RequestParam(value = "content",required = false,defaultValue = "0000")String content,
                             @RequestParam(value = "anonymous",required = false,defaultValue = "0")int anonymous,
                             @RequestParam(value = "annexid",required = false,defaultValue = "0000")String annexid) throws Exception{
        IssueDemandEntity i=new IssueDemandEntity();
        String id= "d"+TimeUtil2.TimestampNow()+ RandomNumberUtil.createRandom(true,5);

        i.setIssue_Demand_Id(id);
        i.setIssue_Demand_Userid(userid);
        i.setIssue_Demand_Title(title);
        i.setIssue_Demand_Content(content);
        i.setIssue_Demand_Anonymous(anonymous);
        i.setIssue_Demand_Annexid(annexid);
        DataPageSubc datas = issueDemandService.Insert(i);

        return ResultUtil.success(datas);
    }

    @RequestMapping("/changeStatus")
    @ResponseBody
    public DataVO changeStatus(int status,String id) throws Exception{
        DataPageSubc change = issueDemandService.change(status, id);
        return ResultUtil.success(change);
    }

    @RequestMapping("/demandById")
    @ResponseBody
    public DataVO loadByDId(@RequestParam(value = "did",required = false,defaultValue = "0000")String id) throws Exception{
        DataPageSubc dataPageSubc = issueDemandService.loadByDemandId(id);
        return ResultUtil.success(dataPageSubc);
    }

    @RequestMapping("/like")
    @ResponseBody
    public DataVO like(@RequestParam(value = "did",required = false,defaultValue = "0000")String id) throws Exception{
        DataPageSubc like = issueDemandService.like(id);
        return ResultUtil.success(like);
    }

}
