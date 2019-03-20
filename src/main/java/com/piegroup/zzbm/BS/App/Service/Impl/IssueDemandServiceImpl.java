package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueDemandServiceIF;
import com.piegroup.zzbm.Dao.IssueDemandDao;
import com.piegroup.zzbm.Dao.IssueLableDao;
import com.piegroup.zzbm.Dao.IssueStatusDao;
import com.piegroup.zzbm.Dao.UserDao;
import com.piegroup.zzbm.Entity.IssueDemandEntity;
import com.piegroup.zzbm.Entity.IssueLableEntity;
import com.piegroup.zzbm.Entity.IssueStatusEntity;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Utils.F2C;
import com.piegroup.zzbm.Utils.PaginationUtil;
import com.piegroup.zzbm.VO.DemandUserVo;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    UserDao userDao;
    @Resource
    IssueLableDao issueLableDao;

    @Resource
    IssueStatusDao issueStatusDao;

    @Override
    public DataPageSubc list(int pageSize, int pageNum) throws Exception {
        DataPageSubc dataPageSubc=new DataPageSubc();
        int count=issueDemandDao.count();
        boolean nextp=(pageSize*pageNum) < count;
        PaginationSubC paginationSubC=new PaginationSubC(pageNum,pageSize,pageNum,nextp);

        List<IssueDemandEntity> datas=issueDemandDao.list((paginationSubC.getFromIndex()-1)*paginationSubC.getPageSize(),
                paginationSubC.getPageSize());
        List<DemandUserVo> duvs=new ArrayList<>();
        for (IssueDemandEntity i:datas) {
            DemandUserVo d=new DemandUserVo();
            UserEntity userEntity = userDao.queryByUserId(i.getIssue_Demand_Userid());
            F2C.father2child(i,d);
            d.setUser(userEntity);
            duvs.add(d);

        }
        dataPageSubc.setPaginationSubC(paginationSubC);
        dataPageSubc.setData(duvs);
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
            List<IssueLableEntity> lableEntities = issueLableDao.loadByIssueId(i.getIssue_Demand_Id());
            IssueStatusEntity issueStatusEntity = issueStatusDao.loadById(i.getIssue_Demand_Issuestatusid());

            map.put("label",lableEntities);
            map.put("status",issueStatusEntity);
            list.add(map);
        }
        DataPageSubc dataPageSubc = new DataPageSubc();
        dataPageSubc.setData(list);
        dataPageSubc.setPaginationSubC(paginationSubC);

        return dataPageSubc;
    }

    @Override
    public DataPageSubc loadByDemandId(String did) throws Exception {
        DataPageSubc d=new DataPageSubc();
        IssueDemandEntity issueDemandEntity = issueDemandDao.queryByDid(did);
        if(issueDemandEntity==null) return null;
        DemandUserVo duv=new DemandUserVo();
        UserEntity userEntity = userDao.queryByUserId(issueDemandEntity.getIssue_Demand_Userid());
        F2C.father2child(issueDemandEntity,d);
        duv.setUser(userEntity);
        d.setData(duv);
        return d;
    }

    @Override
    public DataPageSubc like(String did) {
        DataPageSubc d=new DataPageSubc();
        int like = issueDemandDao.like(did);
        d.setData(like);
        return d;
    }
}
