package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueQuestionsServiceIF;
import com.piegroup.zzbm.Dao.IssueQuestionsDao;
import com.piegroup.zzbm.Dao.IssueUserDao;
import com.piegroup.zzbm.Entity.IssueQuestionsEntity;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Utils.F2C;
import com.piegroup.zzbm.VO.QuestionUserVo;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class IssueQuestionsServiceImpl implements IssueQuestionsServiceIF {
    @Resource
    IssueQuestionsDao issueQuestionsDao;
    @Resource
    IssueUserDao issueUserDao;
    @Override
    public DataPageSubc list(int pageSize, int pageNum) throws Exception {
        DataPageSubc dataPageSubc=new DataPageSubc();
        int count=issueQuestionsDao.count();
        boolean nextp=(pageSize*pageNum) < count;
        PaginationSubC paginationSubC=new PaginationSubC(pageNum,pageSize,pageNum,nextp);

        List<IssueQuestionsEntity> datas=issueQuestionsDao.list((paginationSubC.getFromIndex()-1)*paginationSubC.getPageSize(),
                paginationSubC.getPageSize());
        List<QuestionUserVo> quv=new ArrayList<>();

        for (IssueQuestionsEntity i:datas) {
            QuestionUserVo temp=new QuestionUserVo();
            UserEntity userEntity = issueUserDao.selectUById(i.getIssue_Questions_Userid());
            F2C.father2child(i,temp);
            temp.setUser(userEntity);
            quv.add(temp);
        }

        dataPageSubc.setPaginationSubC(paginationSubC);
        dataPageSubc.setData(quv);
        return dataPageSubc;
    }

    @Override
    public DataPageSubc Insert(IssueQuestionsEntity i) {
        DataPageSubc dataPageSubc=new DataPageSubc();

        int s = issueQuestionsDao.addDemand(i);
        dataPageSubc.setData(s);
        return dataPageSubc;
    }

    @Override
    public DataPageSubc change(int status, String id) {

        DataPageSubc dataPageSubc=new DataPageSubc();
        int i = issueQuestionsDao.change(status, id);
        dataPageSubc.setData(i);

        return dataPageSubc;
    }

    @Override
    public DataPageSubc caina(String reply_id, String question_id) {
        DataPageSubc dataPageSubc=new DataPageSubc();
        int i = issueQuestionsDao.caina(reply_id,question_id);
        dataPageSubc.setData(i);

        return dataPageSubc;
    }
}
