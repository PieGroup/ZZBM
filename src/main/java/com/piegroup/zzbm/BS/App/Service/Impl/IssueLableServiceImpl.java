package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueDemandServiceIF;
import com.piegroup.zzbm.BS.App.Service.IssueLableServiceIF;
import com.piegroup.zzbm.Dao.IssueDemandDao;
import com.piegroup.zzbm.Dao.IssueLableDao;
import com.piegroup.zzbm.Entity.IssueDemandEntity;
import com.piegroup.zzbm.Entity.IssueLableEntity;
import com.piegroup.zzbm.Entity.IssueProgramEntity;
import com.piegroup.zzbm.Utils.PaginationUtil;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class IssueLableServiceImpl implements IssueLableServiceIF {

    @Resource
    IssueLableDao issueLableDao;

    @Override
    public DataPageSubc allLable() {
        DataPageSubc dataPageSubc = new DataPageSubc();

        List<IssueLableEntity> lists=issueLableDao.alll();
        dataPageSubc.setData(lists);
        return dataPageSubc;
    }

    @Override
    public DataPageSubc subLable(int pId) {
        DataPageSubc dataPageSubc = new DataPageSubc();

        List<IssueLableEntity> lists=issueLableDao.subl(pId+1,pId+9999);
        dataPageSubc.setData(lists);
        return dataPageSubc;
    }

    @Override
    public DataPageSubc allParentLable() {
        DataPageSubc dataPageSubc = new DataPageSubc();

        List<IssueLableEntity> lists=issueLableDao.allparent();
        dataPageSubc.setData(lists);
        return dataPageSubc;
    }
}
