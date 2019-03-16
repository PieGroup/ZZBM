package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueDemandServiceIF;
import com.piegroup.zzbm.Dao.IssueDemandDao;
import com.piegroup.zzbm.Entity.IssueDemandEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class IssueDemandServiceImpl implements IssueDemandServiceIF {
    @Resource
    IssueDemandDao  issueDemandDao;

    @Override
    public DataPageSubc list(int pageSize, int pageNum) {
        DataPageSubc dataPageSubc=new DataPageSubc();
        int count=issueDemandDao.count();
        boolean nextp=(pageSize*pageNum) < count;
        PaginationSubC paginationSubC=new PaginationSubC(pageNum,pageSize,pageNum,nextp);

        List<IssueDemandEntity> datas=issueDemandDao.list((paginationSubC.getFromIndex()-1)*paginationSubC.getPageSize(),
                paginationSubC.getPageSize());
        dataPageSubc.setPaginationSubC(paginationSubC);
        dataPageSubc.setData(datas);
        return dataPageSubc;
    }

    @Override
    public DataPageSubc Insert(IssueDemandEntity i) {
        DataPageSubc dataPageSubc=new DataPageSubc();

        int s = issueDemandDao.addDemand(i);
        dataPageSubc.setData(s);
        return dataPageSubc;
    }



    @Override
    public DataPageSubc change(int status, String id) {

        DataPageSubc dataPageSubc=new DataPageSubc();
        int i = issueDemandDao.change(status, id);
        dataPageSubc.setData(i);

        return dataPageSubc;
    }
}
