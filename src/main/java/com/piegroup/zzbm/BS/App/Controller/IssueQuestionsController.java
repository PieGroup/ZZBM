package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.BS.App.Service.Impl.IssueQuestionsServiceImpl;
import com.piegroup.zzbm.Entity.IssueQuestionsEntity;
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
@RequestMapping("/IssueQuention")
@CrossOrigin
@Slf4j
public class IssueQuestionsController {
    @Autowired
    IssueQuestionsServiceImpl issueQuestionsService;


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
        DataPageSubc list = issueQuestionsService.list(pageSize, pageNum);

        return ResultUtil.success(list);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public DataVO addConsult(String userid,String title,String generalize,int accept,String points,int status,String replyid,String ispay,int anonymous,String annexid) throws Exception{
        IssueQuestionsEntity i=new IssueQuestionsEntity();
        String id= TimeUtil2.TimestampNow()+ RandomNumberUtil.createRandom(true,5);
        i.setIssue_questions_id(id);
        i.setIssue_questions_userid(userid);
        i.setIssue_questions_title(title);
        i.setIssue_questions_generalize(generalize);
        i.setIssue_questions_accept(accept);
        i.setIssue_questions_points(points);
        i.setIssue_questions_issueStatusid(status);
        i.setIssue_questions_replyid(replyid);
        i.setIssue_questions_paidLookReply(ispay);
        i.setIssue_questions_anonymous(anonymous);
        i.setIssue_questions_annexid(annexid);

        DataPageSubc datas = issueQuestionsService.Insert(i);

        return ResultUtil.success(datas);
    }

    @RequestMapping("/changeStatus")
    @ResponseBody
    public DataVO changeStatus(int status,String id) throws Exception{
        DataPageSubc change = issueQuestionsService.change(status,id);
        return ResultUtil.success(change);
    }

}
