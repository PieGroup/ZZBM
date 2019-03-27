package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.BS.App.Service.Impl.IssueConsultServiceImpl;
import com.piegroup.zzbm.Entity.IssueConsultEntity;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.Utils.TimeUtil2;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/IssueConsult")
@CrossOrigin
@Slf4j
@CacheConfig(cacheNames = "IssueConsul")
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
    @Cacheable(key = "'consultList_'+#pageNum",unless = "#result.getCode() != '0'")
    public DataVO consultList(@RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
                                    @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum,
                              @RequestParam(value="all",required = false,defaultValue = "1")int all) throws  Exception{
        DataPageSubc list = issueConsultService.list(pageSize, pageNum);

        return ResultUtil.success(list);
    }

    @RequestMapping("/suggestlist")
    @ResponseBody
    public DataVO consultListSuggest(@RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
                              @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum,
                              @RequestParam(value="all",required = false,defaultValue = "1")int all) throws  Exception{
        DataPageSubc list = issueConsultService.list(pageSize, pageNum);

        return ResultUtil.success(list);
    }


    @RequestMapping("/insert")
    @ResponseBody
    public DataVO addConsult(@RequestParam(value = "token",required = false,defaultValue = "32132132") String token,
                             @RequestParam(value = "userid",required = false,defaultValue = "defaultid") String userid,
                             @RequestParam(value = "type",required = false,defaultValue = "1")int type,
                             @RequestParam(value = "buserid",required = false,defaultValue = "defaultid")String buserid,
                             @RequestParam(value = "ispay",required = false,defaultValue = "1")int ispay,
                             @RequestParam(value = "status",required = false,defaultValue = "1")int status,
                             @RequestParam(value = "points",required = false,defaultValue = "10")String points,
                             @RequestParam(value = "title",required = false,defaultValue = "未设置咨询标题名")String title,
                             @RequestParam(value = "content",required = false,defaultValue = "咨询内容未写入")String content,
                             @RequestParam(value = "anonymous",required = false,defaultValue = "0")int anonymous,
                             @RequestParam(value = "annexid",required = false)String annexid,
                             @RequestParam(value = "value",required = false,defaultValue = "100")int value) throws Exception
    {
        IssueConsultEntity i=new IssueConsultEntity();
        String id= TimeUtil2.TimestampNow()+ RandomNumberUtil.createRandom(true,5);
        i.setIssue_Consult_Id(id);
        i.setIssue_Consult_Userid(userid);
        i.setIssue_Consult_Type(type);
        i.setIssue_Consult_Buserid(buserid);
        i.setIssue_Consult_Paidlookreply(ispay);
        i.setIssue_Consult_Points(points);
        i.setIssue_Consult_Title(title);
        i.setIssue_Consult_Content(content);
        i.setIssue_Consult_Anonymous(anonymous);
        i.setIssue_Consult_Annexid(annexid);
        DataPageSubc datas = issueConsultService.Insert(i);
        return ResultUtil.success(datas);
    }

    @RequestMapping("/changeStatus")
    @ResponseBody
    public DataVO changeStatus(int status,String id) throws Exception{
        DataPageSubc change = issueConsultService.change(status, id);
        return ResultUtil.success(change);
    }

    @RequestMapping("/listById")
    @ResponseBody
    public DataVO changeStatus(@RequestParam(value = "cid",required = false,defaultValue = "0000")String cid,
                               @RequestParam(value = "uid",required = false,defaultValue = "0000")String uid) throws Exception{
        DataPageSubc dataPageSubc = issueConsultService.loadById(cid,uid);
        return ResultUtil.success(dataPageSubc);
    }

    @RequestMapping("/like")
    @ResponseBody
    public DataVO like(@RequestParam(value = "cid",required = false,defaultValue = "0000")String cid,
                        @RequestParam(value = "uid",required = false,defaultValue = "0000")String uid) throws Exception{
        DataPageSubc like = issueConsultService.like(cid, uid);
        return ResultUtil.success(like);
    }
}
