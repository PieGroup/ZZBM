package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueCommentServiceIF;
import com.piegroup.zzbm.Dao.*;
import com.piegroup.zzbm.Entity.*;
import com.piegroup.zzbm.Utils.F2C;
import com.piegroup.zzbm.Utils.PaginationUtil;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.TimeUtil2;
import com.piegroup.zzbm.VO.CommentUserVo;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class IssueCommentServiceImpl implements IssueCommentServiceIF {
    @Resource
    commentDao commentDao;

    @Resource
    IssueUserDao issueUserDao;
    @Resource
    likeDao likedao;
    @Resource
    notifyDao notifyDao;
    @Resource
    IssueDemandDao demandDao;
    @Resource
    IssueProgramDao programDao;
    @Resource
    IssueConsultDao consultDao;
    @Resource
    IssueQuestionsDao questionsDao;



    public DataPageSubc list(int pageSize, int pageNum, String itemid,String uid) throws Exception {
        DataPageSubc d = new DataPageSubc();
        int count = commentDao.count(itemid);
        boolean nextp = (pageSize * pageNum) < count;
        PaginationSubC paginationSubC = new PaginationSubC(pageNum, pageSize, pageNum, nextp);

        List<CommentEntity> comments = commentDao.allComment(itemid, (paginationSubC.getFromIndex() - 1) * paginationSubC.getPageSize(),
                paginationSubC.getPageSize());
        List<CommentUserVo> cuv = new ArrayList<>();
        for (CommentEntity c : comments) {
            CommentUserVo temtc = new CommentUserVo();
            UserEntity userEntity = issueUserDao.selectUById(c.getComment_Father_Id());
            UserEntity userEntity1 = issueUserDao.selectUById(c.getComment_User_Id());
            F2C.father2child(c, temtc);
            temtc.setFather_user(userEntity);
            temtc.setUser(userEntity1);
            if(likedao.checkLike(uid,c.getComment_Id())!=null)
                temtc.setIslike(1);
            else if(likedao.checkDislike(uid,c.getComment_Id())!=null)
                temtc.setDislike(1);
            cuv.add(temtc);
        }
        d.setData(cuv);
        d.setPaginationSubC(paginationSubC);
        return d;
    }

    public DataPageSubc QuestionRelist(int pageSize, int pageNum, String itemid,String uid) throws Exception {
        DataPageSubc d = new DataPageSubc();
        int count = commentDao.count(itemid);
        boolean nextp = (pageSize * pageNum) < count;
        PaginationSubC paginationSubC = new PaginationSubC(pageNum, pageSize, pageNum, nextp);

        List<CommentEntity> comments = commentDao.allComment(itemid, (paginationSubC.getFromIndex() - 1) * paginationSubC.getPageSize(),
                paginationSubC.getPageSize());
        CommentEntity replyCom = commentDao.selectReply(itemid);
        List<CommentUserVo> cuv = new ArrayList<>();
        if (replyCom == null) {
            for (CommentEntity c : comments) {
                CommentUserVo temtc = new CommentUserVo();
                UserEntity userEntity1 = issueUserDao.selectUById(c.getComment_User_Id());
                F2C.father2child(c, temtc);
                temtc.setUser(userEntity1);
                if(likedao.checkLike(uid,c.getComment_Id())!=null)
                    temtc.setIslike(1);
                else if(likedao.checkDislike(uid,c.getComment_Id())!=null)
                    temtc.setDislike(1);
                cuv.add(temtc);
            }
        } else {
            {
                CommentUserVo temtc = new CommentUserVo();
                UserEntity userEntity1 = issueUserDao.selectUById(replyCom.getComment_User_Id());
                F2C.father2child(replyCom, temtc);
                temtc.setUser(userEntity1);
                if(likedao.checkLike(uid,replyCom.getComment_Id())!=null)
                    temtc.setIslike(1);
                else if(likedao.checkDislike(uid,replyCom.getComment_Id())!=null)
                    temtc.setDislike(1);
                cuv.add(temtc);
            }
            for (CommentEntity c : comments) {
                if (c.getComment_Id().equals(replyCom.getComment_Id()))
                    continue;
                CommentUserVo temtc = new CommentUserVo();
                UserEntity userEntity1 = issueUserDao.selectUById(c.getComment_User_Id());
                F2C.father2child(c, temtc);
                temtc.setUser(userEntity1);
                if(likedao.checkLike(uid,c.getComment_Id())!=null)
                    temtc.setIslike(1);
                else if(likedao.checkDislike(uid,c.getComment_Id())!=null)
                    temtc.setDislike(1);
                cuv.add(temtc);
            }
        }

        d.setData(cuv);
        d.setPaginationSubC(paginationSubC);
        return d;
    }

    public DataPageSubc addOneComment(CommentEntity c) {
        DataPageSubc dataPageSubc = new DataPageSubc();

        int i = commentDao.addComment(c);
        if(generateCommentNotify(c)){
            System.out.println("Generate notify successfully");
        }else
            System.out.println("Failed to generate notify");
        dataPageSubc.setData(i);
        return dataPageSubc;
    }

    public DataPageSubc changeStatus(String cid, int status) {
        DataPageSubc d = new DataPageSubc();
        int i = commentDao.changeCommentStatus(cid, status);
        d.setData(i);
        return d;

    }

    public DataPageSubc like(String cid,String uid) throws Exception {
        DataPageSubc d = new DataPageSubc();
        int like1=0;
        int like=0;
            IssueLikeEntity issueLikeEntity = likedao.checkLike(uid, cid);
            IssueLikeEntity issueDisEntity = likedao.checkDislike(uid, cid);
            if(issueLikeEntity==null&&issueDisEntity==null){
                like1=likedao.like(new IssueLikeEntity(cid,uid));
                like = commentDao.like(cid);
                if(generateLikeNotify(cid,uid,1))
                    System.out.println("通知成功1");
                else
                    System.out.println("通知不成功");
            }else if(issueLikeEntity!=null&&issueDisEntity==null){
                like1=likedao.deleteLike(cid,uid);
                like = commentDao.cancelLike(cid);
            }else{
                throw new Exception("You hava disliked the item!");
            }
        d.setData((like+like1)/2);
        return d;
    }

    public DataPageSubc dislike(String cid,String uid) throws Exception {
        DataPageSubc d = new DataPageSubc();
        int like1=0;
        int like=0;
        try {
            IssueLikeEntity issueLikeEntity = likedao.checkLike(uid, cid);
            IssueLikeEntity issueDisEntity = likedao.checkDislike(uid, cid);
            if(issueLikeEntity==null&&issueDisEntity==null){
                like1=likedao.dislike(new IssueDislikeEntity(cid,uid));
                like = commentDao.dislike(cid);
                if(generateLikeNotify(cid,uid,2))
                    System.out.println("通知成功");
            }else if(issueLikeEntity==null&&issueDisEntity!=null){
                like1=likedao.deleteDislike(cid,uid);
                like = commentDao.cancelDislike(cid);
            }else{
                throw new Exception("You hava liked the item!");
            }
        }catch (Exception eee){
            throw eee;
        }
        d.setData((like+like1)/2);
        return d;
    }


    private boolean generateLikeNotify(String cid,String uid,int type){
        /**
         * 1:点赞评论 2：踩评论  3评论
         */
        Integer notifyType=0;
        IssueNotifyEntity notify=new IssueNotifyEntity();
        String target="";
        String notifyFromId="";
        String notifyContent="";
        CommentEntity comment = commentDao.selectCommentById(cid);

        IssueQuestionsEntity issueQuestionsEntity;
        IssueConsultEntity issueConsultEntity;
        IssueProgramEntity issueProgramEntity;
        IssueDemandEntity issueDemandEntity;
        if((issueQuestionsEntity = questionsDao.loadByQid(comment.getComment_Item_Id()))!=null) {
            notifyFromId=issueQuestionsEntity.getIssue_Questions_Id();
            notifyContent=issueQuestionsEntity.getIssue_Questions_Title();
            notifyType = 0;
            target="回答";
        }
        else if((issueConsultEntity = consultDao.loadById(comment.getComment_Item_Id()))!=null){
            notifyFromId=issueConsultEntity.getIssue_Consult_Id();
            notifyContent=issueConsultEntity.getIssue_Consult_Title();
            notifyType=1;
            target="回复";
        }
        else if((issueDemandEntity = demandDao.queryByDid(comment.getComment_Item_Id()))!=null){
            notifyFromId=issueDemandEntity.getIssue_Demand_Id();
            notifyContent=issueDemandEntity.getIssue_Demand_Title();
            notifyType=2;
            target="评论";
        }
        else if((issueProgramEntity = programDao.queryByPid(comment.getComment_Item_Id()))!=null){
            notifyFromId=issueProgramEntity.getIssue_Program_Id();
            notifyContent=issueProgramEntity.getIssue_Program_Title();
            notifyType=3;
            target="评论";
        }
        else
            return false;
        String notifyid="noti"+ TimeUtil2.TimestampNow()+ RandomNumberUtil.createRandom(true,5);
        notify.setNotifyid(notifyid);
        notify.setNotifyfromid(notifyFromId);
        notify.setNotifytype(notifyType.toString());
        notify.setNotifycontent(notifyContent);
        notify.setUserid(uid);

        if(type==1){
            notify.setNotifytitle("有人点赞了你的"+target);
        }else if(type==2)
        {
            notify.setNotifytitle("有人踩了你的"+target);
        }
        else if(type==3){
            notify.setNotifytitle("有人回复你的"+target);
        }
        else
            return false;

        try{
            notifyDao.insertOneNotify(notify);
        }catch (Exception ee){
            System.out.println(ee.getMessage());
            return false;
        }


        return true;
    }

    private boolean generateCommentNotify(CommentEntity comment){

        Integer notifyType=0;
        IssueNotifyEntity notify=new IssueNotifyEntity();
        String target="";
        String notifyFromId="";
        String notifyContent="";
        String userid="";

        IssueQuestionsEntity issueQuestionsEntity;
        IssueConsultEntity issueConsultEntity;
        IssueProgramEntity issueProgramEntity;
        IssueDemandEntity issueDemandEntity;
        CommentEntity facomment;
        if((issueQuestionsEntity = questionsDao.loadByQid(comment.getComment_Father_Id()))!=null) {
            notifyFromId=issueQuestionsEntity.getIssue_Questions_Id();
            notifyContent=issueQuestionsEntity.getIssue_Questions_Title();
            notifyType = 0;
            target="问题";
            userid=issueQuestionsEntity.getIssue_Questions_Userid();
        }
        else if((issueConsultEntity = consultDao.loadById(comment.getComment_Father_Id()))!=null){
            notifyFromId=issueConsultEntity.getIssue_Consult_Id();
            notifyContent=issueConsultEntity.getIssue_Consult_Title();
            notifyType=1;
            target="咨询";
            userid=issueConsultEntity.getIssue_Consult_Userid();
        }
        else if((issueDemandEntity = demandDao.queryByDid(comment.getComment_Father_Id()))!=null){
            notifyFromId=issueDemandEntity.getIssue_Demand_Id();
            notifyContent=issueDemandEntity.getIssue_Demand_Title();
            notifyType=2;
            target="需求";
            userid=issueDemandEntity.getIssue_Demand_Userid();
        }
        else if((issueProgramEntity = programDao.queryByPid(comment.getComment_Father_Id()))!=null){
            notifyFromId=issueProgramEntity.getIssue_Program_Id();
            notifyContent=issueProgramEntity.getIssue_Program_Title();
            notifyType=3;
            target="方案";
            userid=issueProgramEntity.getIssue_Program_Userid();
        }
        else if((facomment = commentDao.selectCommentById(comment.getComment_Father_Id()))!=null){
            notifyFromId=facomment.getComment_Item_Id();
            notifyContent=facomment.getComment_Content();
            notifyType=4;
            target="评论";
            userid=facomment.getComment_User_Id();
        }
        else
            return false;
        String notifyid="noti"+ TimeUtil2.TimestampNow()+ RandomNumberUtil.createRandom(true,5);
        notify.setNotifyid(notifyid);
        notify.setNotifyfromid(notifyFromId);
        notify.setNotifytype(notifyType.toString());
        notify.setNotifycontent(notifyContent);
        notify.setUserid(userid);
        notify.setNotifytitle("有人评论了你的"+target);

        try{
            notifyDao.insertOneNotify(notify);
        }catch (Exception ee){
            System.out.println(ee.getMessage());
            return false;
        }

        return true;
    }
}
