package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueConsultServiceIF;
import com.piegroup.zzbm.Dao.IssueConsultDao;
import com.piegroup.zzbm.Entity.IssueConsultEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class IssueConsultServiceImpl implements IssueConsultServiceIF {
    @Resource
    IssueConsultDao issueConsultDao;

    @Override
    public DataPageSubc list(int pageSize, int pageNum) {
        DataPageSubc dataPageSubc=new DataPageSubc();
        int count=issueConsultDao.count();
        boolean nextp=(pageSize*pageNum) < count;
        PaginationSubC paginationSubC=new PaginationSubC(pageNum,pageSize,pageNum,nextp);

        List<IssueConsultEntity> datas=issueConsultDao.list((paginationSubC.getFromIndex()-1)*paginationSubC.getPageSize(),
                paginationSubC.getPageSize());
        dataPageSubc.setPaginationSubC(paginationSubC);
        dataPageSubc.setData(datas);
        return dataPageSubc;
    }

    @Override
    public DataPageSubc Insert(IssueConsultEntity i) {
        DataPageSubc dataPageSubc=new DataPageSubc();

        int s = issueConsultDao.addProgram(i);
        dataPageSubc.setData(s);
        return dataPageSubc;
    }


    @Override
    public DataPageSubc change(int status, String id) {

        DataPageSubc dataPageSubc=new DataPageSubc();
        int i = issueConsultDao.changePro(status, id);
        dataPageSubc.setData(i);

        return dataPageSubc;
    }
}
