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
     * @param all  1表示显示全部问题，其他值代表 系统推荐（非必需）
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataVO programList(@RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
                                    @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum,
                              @RequestParam(value="all",required = false,defaultValue = "1")int all) throws  Exception{
        DataPageSubc list = issueProgramService.list(pageSize, pageNum);

        return ResultUtil.success(list);
    }

    @RequestMapping("/")
    @ResponseBody
    public String test(@RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
                              @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum) throws  Exception{

        return "pageSize:"+pageSize+" & pageNum:"+pageNum;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public DataVO addProgram(String userid,String title,String content,int anonymous,String reward,int statusid,String annexid) throws Exception{
        IssueProgramEntity i=new IssueProgramEntity();
        String id= TimeUtil2.TimestampNow()+ RandomNumberUtil.createRandom(true,5);
        i.setIssue_program_id(id);
        i.setIssue_program_userid(userid);
        i.setIssue_program_title(title);
        i.setIssue_program_content(content);
        i.setIssue_program_anonymous(anonymous);
        i.setIssue_program_reward(reward);
        i.setIssue_program_issueStatusid(statusid);
        i.setIssue_program_annexid(annexid);
        DataPageSubc datas = issueProgramService.Insert(i);

        return ResultUtil.success(datas);
    }

    @RequestMapping("/changeStatus")
    @ResponseBody
    public DataVO changeStatus(int status,String id) throws Exception{
        DataPageSubc change = issueProgramService.change(status, id);
        return ResultUtil.success(change);
    }

}
