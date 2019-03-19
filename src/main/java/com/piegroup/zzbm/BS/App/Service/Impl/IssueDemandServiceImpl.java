package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueDemandServiceIF;
import com.piegroup.zzbm.Dao.IssueDemandDao;
import com.piegroup.zzbm.Dao.IssueLableDao;
import com.piegroup.zzbm.Dao.IssueStatusDao;
import com.piegroup.zzbm.Entity.IssueDemandEntity;
import com.piegroup.zzbm.Entity.IssueLableEntity;
import com.piegroup.zzbm.Entity.IssueStatusEntity;
import com.piegroup.zzbm.Utils.PaginationUtil;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.plugin.javascript.navig.LinkArray;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class IssueDemandServiceImpl implements IssueDemandServiceIF {
    @Resource
    IssueDemandDao  issueDemandDao;

    @Resource
    IssueLableDao issueLableDao;

    @Resource
    IssueStatusDao issueStatusDao;

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

    //需求
    @Override
    public DataPageSubc loadByUserId(String user_id, int pageSize, int pageNum) {

        int count = issueDemandDao.countByUserid(user_id);

        PaginationSubC paginationSubC = PaginationUtil.pagination(pageNum,pageSize,count);

        List<IssueDemandEntity> issueDemandEntities = issueDemandDao.loadByUserId(user_id,paginationSubC.getFromIndex(),paginationSubC.getPageSize());

        List list = new ArrayList();
        for (IssueDemandEntity i: issueDemandEntities) {
            Map map = new HashMap();
            map.put("entity",i);
            List<IssueLableEntity> lableEntities = issueLableDao.loadByIssueId(i.getIssue_demand_id());
            IssueStatusEntity issueStatusEntity = issueStatusDao.loadById(i.getIssue_demand_issueStatusid());

            map.put("label",lableEntities);
            map.put("status",issueStatusEntity);
            list.add(map);
        }
        DataPageSubc dataPageSubc = new DataPageSubc();
        dataPageSubc.setData(list);
        dataPageSubc.setPaginationSubC(paginationSubC);

        return dataPageSubc;
    }
}
