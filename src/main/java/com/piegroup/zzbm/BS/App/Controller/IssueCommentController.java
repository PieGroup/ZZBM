package com.piegroup.zzbm.BS.App.Controller;

import com.piegroup.zzbm.BS.App.Service.Impl.IssueCommentServiceImpl;
import com.piegroup.zzbm.Entity.CommentEntity;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.Utils.TimeUtil2;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import javafx.scene.chart.ValueAxis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/IssueComment")
@CrossOrigin
@Slf4j
public class IssueCommentController {

    @Autowired
    IssueCommentServiceImpl issueCommentService;

    @RequestMapping("/insert")
    @ResponseBody
    public DataVO insert(@RequestParam(value = "itemid",required = false,defaultValue = "0000")String itemid,
                         @RequestParam(value = "userid",required = false,defaultValue = "0000")String userid,
                         @RequestParam(value = "fatherid",required = false,defaultValue = "0000")String fatherid,
                         @RequestParam(value = "content",required = false,defaultValue = "0000")String content,
                         @RequestParam(value = "pic",required = false,defaultValue = "0000")String pic,
                         @RequestParam(value = "type",required = false,defaultValue = "1")int type)throws Exception{
        CommentEntity c=new CommentEntity();
        String id="c"+ TimeUtil2.TimestampNow()+ RandomNumberUtil.createRandom(true,5);
        c.setComment_Id(id);
        c.setComment_Item_Id(itemid);
        c.setComment_User_Id(userid);
        c.setComment_Father_Id(fatherid);
        c.setComment_Content(content);
        c.setComment_Pic(pic);
        c.setComment_Type(type);
        c.setComment_Status(1);
        DataPageSubc dataPageSubc = issueCommentService.addOneComment(c);

        return ResultUtil.success(dataPageSubc);
    }

    @RequestMapping("/commentList")
    @ResponseBody
    public DataVO list(@RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
                       @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "itemid",required = false,defaultValue = "0000")String itemid,
                       @RequestParam(value = "uid",required = false,defaultValue = "0000")String uid)throws  Exception{
        DataPageSubc list = issueCommentService.list(pageSize, pageNum, itemid,uid);
        return ResultUtil.success(list);
    }

    @RequestMapping("/questionReplyList")
    @ResponseBody
    public DataVO list2(@RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
                       @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum,
                       @RequestParam(value = "itemid",required = false,defaultValue = "0000")String itemid,
                        @RequestParam(value = "uid",required = false,defaultValue = "0000")String uid)throws  Exception{
        DataPageSubc list = issueCommentService.QuestionRelist(pageSize, pageNum, itemid,uid);
        return ResultUtil.success(list);
    }

    @RequestMapping("/change")
    @ResponseBody
    public DataVO changeStatus(@RequestParam(value = "cId",required = false,defaultValue = "0000")String cid,
                               @RequestParam(value = "status",required = false,defaultValue = "1")int status)throws Exception{
        DataPageSubc dataPageSubc = issueCommentService.changeStatus(cid, status);

        return ResultUtil.success(dataPageSubc);
    }

    @RequestMapping("/like")
    @ResponseBody
    public DataVO like(@RequestParam(value = "cid",required = false,defaultValue = "0000")String cid,
                       @RequestParam(value = "uid",required = false,defaultValue = "0000")String uid)throws Exception{
        DataPageSubc like = issueCommentService.like(cid,uid);
        return ResultUtil.success(like);
    }

    @RequestMapping("/dislike")
    @ResponseBody
    public DataVO dislike(@RequestParam(value = "cid",required = false,defaultValue = "0000")String cid,
                          @RequestParam(value = "uid",required = false,defaultValue = "0000")String uid)throws Exception{
        DataPageSubc dislike = issueCommentService.dislike(cid,uid);
        return ResultUtil.success(dislike);
    }

}
