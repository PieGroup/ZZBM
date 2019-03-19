package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueCommentServiceIF;
import com.piegroup.zzbm.Dao.*;
import com.piegroup.zzbm.Entity.*;
import com.piegroup.zzbm.Utils.F2C;
import com.piegroup.zzbm.Utils.PaginationUtil;
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
    IssueConsultDao issueConsultDao;

    @Resource
    IssueProgramDao issueProgramDao;

    @Resource
    IssueDemandDao issueDemandDao;

    @Resource
    IssueQuestionsDao issueQuestionsDao;

    public DataPageSubc list(int pageSize, int pageNum, String item_id) throws Exception {
        DataPageSubc d = new DataPageSubc();
        int count = commentDao.count(item_id);
        boolean nextp = (pageSize * pageNum) < count;
        PaginationSubC paginationSubC = new PaginationSubC(pageNum, pageSize, pageNum, nextp);

        List<CommentEntity> comments = commentDao.allComment(item_id, (paginationSubC.getFromIndex() - 1) * paginationSubC.getPageSize(),
                paginationSubC.getPageSize());
        List<CommentUserVo> cuv = new ArrayList<>();
        for (CommentEntity c : comments) {
            CommentUserVo temtc = new CommentUserVo();
            UserEntity userEntity = issueUserDao.selectUById(c.getComment_Father_Id());
            UserEntity userEntity1 = issueUserDao.selectUById(c.getComment_User_Id());
            F2C.father2child(c, temtc);
            temtc.setFather_user(userEntity);
            temtc.setUser(userEntity1);
            cuv.add(temtc);
        }
        d.setData(cuv);
        d.setPaginationSubC(paginationSubC);
        return d;
    }

    public DataPageSubc QuestionRelist(int pageSize, int pageNum, String item_id) throws Exception {
        DataPageSubc d = new DataPageSubc();
        int count = commentDao.count(item_id);
        boolean nextp = (pageSize * pageNum) < count;
        PaginationSubC paginationSubC = new PaginationSubC(pageNum, pageSize, pageNum, nextp);

        List<CommentEntity> comments = commentDao.allComment(item_id, (paginationSubC.getFromIndex() - 1) * paginationSubC.getPageSize(),
                paginationSubC.getPageSize());
        CommentEntity replyCom = commentDao.selectReply(item_id);
        List<CommentUserVo> cuv = new ArrayList<>();
        if (replyCom == null) {
            for (CommentEntity c : comments) {
                CommentUserVo temtc = new CommentUserVo();
                UserEntity userEntity1 = issueUserDao.selectUById(c.getComment_User_Id());
                F2C.father2child(c, temtc);
                temtc.setUser(userEntity1);
                cuv.add(temtc);
            }
        } else {
            {
                CommentUserVo temtc = new CommentUserVo();
                UserEntity userEntity1 = issueUserDao.selectUById(replyCom.getComment_User_Id());
                F2C.father2child(replyCom, temtc);
                temtc.setUser(userEntity1);
                cuv.add(temtc);
            }
            for (CommentEntity c : comments) {
                if (c.getComment_Id().equals(replyCom.getComment_Id()))
                    continue;
                CommentUserVo temtc = new CommentUserVo();
                UserEntity userEntity1 = issueUserDao.selectUById(c.getComment_User_Id());
                F2C.father2child(c, temtc);
                temtc.setUser(userEntity1);
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

        dataPageSubc.setData(i);
        return dataPageSubc;
    }

    public DataPageSubc changeStatus(String cid, int status) {
        DataPageSubc d = new DataPageSubc();
        int i = commentDao.changeCommentStatus(cid, status);
        d.setData(i);
        return d;

    }

    public DataPageSubc like(String cid) {
        DataPageSubc d = new DataPageSubc();
        int like = commentDao.like(cid);
        d.setData(like);


        return d;
    }

    public DataPageSubc dislike(String cid) {
        DataPageSubc d = new DataPageSubc();
        int dislike = commentDao.dislike(cid);
        d.setData(dislike);


        return d;
    }

}
