package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueQuestionsServiceIF;
import com.piegroup.zzbm.Dao.IssueQuestionsDao;
import com.piegroup.zzbm.Entity.IssueQuestionsEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class IssueQuestionsServiceImpl implements IssueQuestionsServiceIF {
    @Resource
    IssueQuestionsDao issueQuestionsDao;

    @Override
    public DataPageSubc list(int pageSize, int pageNum) {
        DataPageSubc dataPageSubc=new DataPageSubc();
        int count=issueQuestionsDao.count();
        boolean nextp=(pageSize*pageNum) < count;
        PaginationSubC paginationSubC=new PaginationSubC(pageNum,pageSize,pageNum,nextp);

        List<IssueQuestionsEntity> datas=issueQuestionsDao.list((paginationSubC.getFromIndex()-1)*paginationSubC.getPageSize(),
                paginationSubC.getPageSize());
        dataPageSubc.setPaginationSubC(paginationSubC);
        dataPageSubc.setData(datas);
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
}
