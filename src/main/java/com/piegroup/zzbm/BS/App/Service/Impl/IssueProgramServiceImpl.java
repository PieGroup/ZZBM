package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueProgramServiceIF;
import com.piegroup.zzbm.Dao.IssueProgramDao;
import com.piegroup.zzbm.Entity.IssueProgramEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class IssueProgramServiceImpl implements IssueProgramServiceIF {

    @Resource
    IssueProgramDao issueProgramDao;

    @Override
    public DataPageSubc list(int pageSize, int pageNum) {
        DataPageSubc dataPageSubc=new DataPageSubc();
        int count=issueProgramDao.count();
        boolean nextp=(pageSize*pageNum)>count?true:false;
        PaginationSubC paginationSubC=new PaginationSubC(pageNum,pageSize,pageNum,nextp);

        List<IssueProgramEntity> datas=issueProgramDao.list(paginationSubC.getFromIndex()*paginationSubC.getPageSize()-1,
                paginationSubC.getPageSize());
        dataPageSubc.setPaginationSubC(paginationSubC);
        dataPageSubc.setData(datas);
        return dataPageSubc;
    }
}
