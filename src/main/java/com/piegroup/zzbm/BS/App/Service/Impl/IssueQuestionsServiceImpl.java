package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueQuestionsServiceIF;
import com.piegroup.zzbm.Dao.*;
import com.piegroup.zzbm.Entity.*;
import com.piegroup.zzbm.Utils.F2C;
import com.piegroup.zzbm.VO.ConsultUserVo;
import com.piegroup.zzbm.VO.QuestionUserVo;
import com.piegroup.zzbm.Utils.PaginationUtil;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import lombok.extern.slf4j.Slf4j;
import org.junit.experimental.theories.FromDataPoints;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class IssueQuestionsServiceImpl implements IssueQuestionsServiceIF {

    @Resource
    private IssueQuestionsDao issueQuestionsDao;
    @Resource
    private IssueUserDao issueUserDao;

    @Resource
    private IssueLableDao issueLableDao;

    @Resource
    private IssueStatusDao issueStatusDao;

    @Resource
    private IssueRecordDao issueRecordDao;



    @Override
    public DataPageSubc list(int pageSize, int pageNum) throws Exception {
        DataPageSubc dataPageSubc = new DataPageSubc();
        int count = issueQuestionsDao.count();
        boolean nextp = (pageSize * pageNum) < count;
        PaginationSubC paginationSubC = new PaginationSubC(pageNum, pageSize, pageNum, nextp);

        List<IssueQuestionsEntity> datas = issueQuestionsDao.list((paginationSubC.getFromIndex() - 1) * paginationSubC.getPageSize(),
                paginationSubC.getPageSize());
        List<QuestionUserVo> quv = new ArrayList<>();

        for (IssueQuestionsEntity i : datas) {
            QuestionUserVo temp = new QuestionUserVo();
            UserEntity userEntity = issueUserDao.selectUById(i.getIssue_Questions_Userid());
            F2C.father2child(i, temp);
            temp.setUser(userEntity);
            quv.add(temp);
        }

        dataPageSubc.setPaginationSubC(paginationSubC);
        dataPageSubc.setData(quv);
        return dataPageSubc;
    }

    @Override
    public DataPageSubc Insert(IssueQuestionsEntity i) {
        DataPageSubc dataPageSubc = new DataPageSubc();

        int s = issueQuestionsDao.addDemand(i);
        dataPageSubc.setData(s);
        return dataPageSubc;
    }

    @Override
    public DataPageSubc change(int status, String id) {

        DataPageSubc dataPageSubc = new DataPageSubc();
        int i = issueQuestionsDao.change(status, id);
        dataPageSubc.setData(i);

        return dataPageSubc;
    }

    @Override
    public DataPageSubc caina(String reply_id, String question_id) {
        DataPageSubc dataPageSubc = new DataPageSubc();
        int i = issueQuestionsDao.caina(reply_id, question_id);
        dataPageSubc.setData(i);

        return dataPageSubc;
    }


    //通过用户id 查找用户的问题
    @Override
    public DataPageSubc queryById(String user_id, int pageSize, int pageNum) {

        log.info("查询用户的问题");

        int count = issueQuestionsDao.countById(user_id);

        DataPageSubc pageSubc = new DataPageSubc();

        PaginationSubC paginationSubC = PaginationUtil.pagination(pageNum, pageSize, count);

        List<IssueLableEntity> issueLableEntity = new ArrayList<IssueLableEntity>();
        IssueStatusEntity issueStatusEntity = new IssueStatusEntity();
        IssueRecordEntity issueRecordEntity = new IssueRecordEntity();

        List lists = new ArrayList();


        List<IssueQuestionsEntity> issueQuestionsEntities = issueQuestionsDao.queryById(user_id, paginationSubC.getFromIndex(), paginationSubC.getPageSize());


        //获取问题标签
        for (IssueQuestionsEntity i : issueQuestionsEntities) {
            Map map = new HashMap();
            issueLableEntity = issueLableDao.loadByIssueId(i.getIssue_Questions_Id());
            issueStatusEntity = issueStatusDao.loadById(i.getIssue_Questions_Issuestatusid());
            issueRecordEntity = issueRecordDao.loadById(i.getIssue_Questions_Id());

            map.put("entity", i);
            map.put("label", issueLableEntity);
            map.put("record", issueRecordEntity);
            map.put("status", issueStatusEntity);

            lists.add(map);
        }
        //获得问题的状态
        pageSubc.setData(lists);
        pageSubc.setPaginationSubC(paginationSubC);
        log.info("结束查询问题");

        return pageSubc;
    }

    @Override
    public DataPageSubc queryByQuestionId(String qid) throws Exception {
        DataPageSubc d=new DataPageSubc();
        QuestionUserVo questionUserVo=new QuestionUserVo();
        IssueQuestionsEntity issueQuestionsEntity = issueQuestionsDao.loadByQid(qid);
        UserEntity userEntity = issueUserDao.selectUById(issueQuestionsEntity.getIssue_Questions_Userid());
        F2C.father2child(issueQuestionsEntity,questionUserVo);
        questionUserVo.setUser(userEntity);
        d.setData(questionUserVo);
        return  d;
    }


}
