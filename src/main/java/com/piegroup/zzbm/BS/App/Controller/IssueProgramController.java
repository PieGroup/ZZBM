package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.BS.App.Service.Impl.IssueProgramServiceImpl;
import com.piegroup.zzbm.Entity.IssueProgramEntity;
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
@RequestMapping("/IssueProgram")
@CrossOrigin
@Slf4j
public class IssueProgramController {

    @Autowired
    IssueProgramServiceImpl issueProgramService;

    /**
     * 首页请求问题列表
     * @param pageSize
     * @param pageNum
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataVO programList(@RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
                                    @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum) throws  Exception{
        DataPageSubc list = issueProgramService.list(pageSize, pageNum);

        return ResultUtil.success(list);
    }

    @RequestMapping("/suggestlist")
    @ResponseBody
    public DataVO programListSuggest(@RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
                              @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum) throws  Exception{
        DataPageSubc list = issueProgramService.list(pageSize, pageNum);

        return ResultUtil.success(list);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public DataVO addProgram(@RequestParam(value = "userid",required = false,defaultValue = "defaultid")String userid,
                             @RequestParam(value = "title",required = false,defaultValue = "未设置方案标题")String title,
                             @RequestParam(value = "content",required = false,defaultValue = "方案内容未写入")String content,
                             @RequestParam(value = "anonymous",required = false,defaultValue = "0")int anonymous,
                             @RequestParam(value = "table",required = false)String table,
                             @RequestParam(value = "reward",required = false,defaultValue = "0")int reward,
                             @RequestParam(value = "pic",required = false)String pic) throws Exception{
        IssueProgramEntity i=new IssueProgramEntity();
        String id= "p"+TimeUtil2.TimestampNow()+ RandomNumberUtil.createRandom(true,5);
        i.setIssue_Program_Id(id);
        i.setIssue_Program_Userid(userid);
        i.setIssue_Program_Title(title);
        i.setIssue_Program_Content(content);
        i.setIssue_Program_Anonymous(anonymous);
        i.setIssue_Program_Reward(reward);
        i.setIssue_Program_Pic(pic);
        i.setIssue_Program_Table(table);
        DataPageSubc datas = issueProgramService.Insert(i);

        return ResultUtil.success(datas);
    }

    @RequestMapping("/changeStatus")
    @ResponseBody
    public DataVO changeStatus(int status,String id) throws Exception{
        DataPageSubc change = issueProgramService.change(status, id);
        return ResultUtil.success(change);
    }


    @RequestMapping("/proByProId")
    @ResponseBody
    public DataVO loadByProId(@RequestParam(value = "pid",required = false,defaultValue = "000")String pid,
                              @RequestParam(value = "uid",required = false,defaultValue = "000")String uid) throws Exception{
        DataPageSubc dataPageSubc = issueProgramService.loadByProId(pid,uid);
        return ResultUtil.success(dataPageSubc);
    }

    @RequestMapping("/like")
    @ResponseBody
    public DataVO like(@RequestParam(value = "pid",required = false,defaultValue = "0000")String pid,
                       @RequestParam(value = "uid",required = false,defaultValue = "000")String uid) throws Exception{
        DataPageSubc dataPageSubc = issueProgramService.like(pid,uid);
        return ResultUtil.success(dataPageSubc);
    }
}
