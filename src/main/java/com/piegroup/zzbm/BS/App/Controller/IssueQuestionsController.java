package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.BS.App.Service.Impl.IssueQuestionsServiceImpl;
import com.piegroup.zzbm.Entity.IssueQuestionsEntity;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.Utils.TimeUtil2;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import lombok.Value;
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
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public DataVO consultList(@RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
                                    @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum) throws  Exception{
        DataPageSubc list = issueQuestionsService.list(pageSize, pageNum);

        return ResultUtil.success(list);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public DataVO addConsult(@RequestParam(value = "userid",required = false,defaultValue = "0000")String userid,
                             @RequestParam(value = "title",required = false,defaultValue = "")String title,
                             @RequestParam(value = "generalize",required = false,defaultValue = "")String generalize,
                             @RequestParam(value = "value",required = false,defaultValue = "0")int value,
                             @RequestParam(value = "anonymous",required = false,defaultValue = "")int anonymous,
                             @RequestParam(value = "annexid",required = false,defaultValue = "")String annexid) throws Exception{
        IssueQuestionsEntity i=new IssueQuestionsEntity();
        String id= "q"+TimeUtil2.TimestampNow()+ RandomNumberUtil.createRandom(true,5);
        i.setIssue_Questions_Id(id);
        i.setIssue_Questions_Userid(userid);
        i.setIssue_Questions_Title(title);
        i.setIssue_Questions_Generalize(generalize);
        i.setIssue_Questions_Value(value);
        i.setIssue_Questions_Anonymous(anonymous);
        i.setIssue_Questions_Annexid(annexid);
        DataPageSubc datas = issueQuestionsService.Insert(i);
        return ResultUtil.success(datas);
    }

    @RequestMapping("/changeStatus")
    @ResponseBody
    public DataVO changeStatus(int status,String id) throws Exception{
        DataPageSubc change = issueQuestionsService.change(status,id);
        return ResultUtil.success(change);
    }

    @RequestMapping("/caina")
    @ResponseBody
    public DataVO caina(@RequestParam(value = "replyId",required = false,defaultValue = "0000")String reid,
                        @RequestParam(value = "questionId",required = false,defaultValue = "0000")String qid)throws Exception{
        DataPageSubc caina = issueQuestionsService.caina(reid, qid);

        return ResultUtil.success(caina);

    }

    @RequestMapping("/questionByQid")
    @ResponseBody
    public DataVO queryQbyQid(@RequestParam(value = "qid",required = false,defaultValue = "0000")String qid)throws Exception{
        DataPageSubc dataPageSubc = issueQuestionsService.queryByQuestionId(qid);

        return ResultUtil.success(dataPageSubc);

    }
}
