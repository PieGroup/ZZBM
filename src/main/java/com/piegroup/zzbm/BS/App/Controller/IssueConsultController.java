package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.BS.App.Service.Impl.IssueConsultServiceImpl;
import com.piegroup.zzbm.Entity.IssueConsultEntity;
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
@RequestMapping("/IssueConsult")
@CrossOrigin
@Slf4j
public class IssueConsultController {
    @Autowired
    IssueConsultServiceImpl issueConsultService;


    /**
     * 咨询请求问题列表
     * @param pageSize
     * @param pageNum
     * @param all  1表示显示全部咨询，其他值代表 系统推荐（非必需）
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataVO consultList(@RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
                                    @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum,
                              @RequestParam(value="all",required = false,defaultValue = "1")int all) throws  Exception{
        DataPageSubc list = issueConsultService.list(pageSize, pageNum);

        return ResultUtil.success(list);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public DataVO addConsult(@RequestParam(value = "token",required = false,defaultValue = "32132132") String token,
                             @RequestParam(value = "userid",required = false,defaultValue = "11111") String userid,
                             @RequestParam(value = "type",required = false,defaultValue = "1")int type,
                             @RequestParam(value = "buserid",required = false,defaultValue = "11111")String buserid,
                             @RequestParam(value = "ispay",required = false,defaultValue = "1")int ispay,
                             @RequestParam(value = "status",required = false,defaultValue = "1")int status,
                             @RequestParam(value = "points",required = false,defaultValue = "10")String points,
                             @RequestParam(value = "title",required = false,defaultValue = "未设置标题名")String title,
                             @RequestParam(value = "content",required = false,defaultValue = "内容为空")String content,
                             @RequestParam(value = "anonymous",required = false,defaultValue = "0")int anonymous,
                             @RequestParam(value = "annexid",required = false,defaultValue = "00000")String annexid,
                             @RequestParam(value = "checkpoint",required = false,defaultValue = "100")String checkpoint) throws Exception
    {
        IssueConsultEntity i=new IssueConsultEntity();
        String id= TimeUtil.TimestampNow()+ RandomNumberUtil.createRandom(true,5);
        i.setIssue_consult_id(id);
        i.setIssue_consult_userid(userid);
        i.setIssue_consult_buserid(buserid);
        i.setIssue_consult_type(type);
        i.setIssue_consult_paidLookReply(ispay);
        i.setIssue_consult_issueStatusid(status);
        i.setIssue_consult_points(points);
        i.setIssue_consult_title(title);
        i.setIssue_consult_content(content);
        i.setIssue_consult_anonymous(anonymous);
        i.setIssue_consult_annexid(annexid);
        i.setIssue_consult_paid(checkpoint);
        DataPageSubc datas = issueConsultService.Insert(i);

        return ResultUtil.success(datas);
    }

    @RequestMapping("/changeStatus")
    @ResponseBody
    public DataVO changeStatus(int status,String id) throws Exception{
        DataPageSubc change = issueConsultService.change(status, id);
        return ResultUtil.success(change);
    }

}
