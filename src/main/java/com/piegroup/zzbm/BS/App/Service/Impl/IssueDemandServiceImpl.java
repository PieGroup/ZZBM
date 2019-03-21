package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueDemandServiceIF;
import com.piegroup.zzbm.Dao.*;
import com.piegroup.zzbm.Entity.*;
import com.piegroup.zzbm.Utils.F2C;
import com.piegroup.zzbm.Utils.PaginationUtil;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.TimeUtil2;
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
    IssueUserDao issueUserDao;
    @Resource
    IssueLableDao issueLableDao;
    @Resource
    likeDao likeDao;
    @Resource
    IssueStatusDao issueStatusDao;
    @Resource
    notifyDao notifyDao;
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
            UserEntity userEntity;
            if (i.getIssue_Demand_Anonymous()==1){
                userEntity= issueUserDao.selectUById("defaultid");
                userEntity.setUser_Id(i.getIssue_Demand_Userid());
            }
            else{
                userEntity= issueUserDao.selectUById(i.getIssue_Demand_Userid());
            }
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
    public DataPageSubc loadByDemandId(String did,String uid) throws Exception {
        DataPageSubc d=new DataPageSubc();
        IssueDemandEntity issueDemandEntity = issueDemandDao.queryByDid(did);
        if(issueDemandEntity==null) return null;
        issueDemandDao.read(did);
        DemandUserVo duv=new DemandUserVo();
        UserEntity userEntity ;
        if (issueDemandEntity.getIssue_Demand_Anonymous()==1){
            userEntity= issueUserDao.selectUById("defaultid");
            userEntity.setUser_Id(issueDemandEntity.getIssue_Demand_Userid());
        }
        else{
            userEntity= issueUserDao.selectUById(issueDemandEntity.getIssue_Demand_Userid());}
        if(likeDao.checkLike(uid,issueDemandEntity.getIssue_Demand_Id())!= null)
            duv.setIslike(1);
        F2C.father2child(issueDemandEntity,duv);
        duv.setUser(userEntity);
        d.setData(duv);
        return d;
    }

    @Override
    public DataPageSubc like(String did,String uid) {
        DataPageSubc d=new DataPageSubc();
        int like1 = likeDao.like(new IssueLikeEntity(did, uid));
        int like = issueDemandDao.like(did);
        IssueDemandEntity issueDemandEntity = issueDemandDao.queryByDid(did);
        String notifyid="noti"+ TimeUtil2.TimestampNow()+ RandomNumberUtil.createRandom(true,5);
        IssueNotifyEntity notifyEntity=new IssueNotifyEntity();
        notifyEntity.setNotifyid(notifyid);
        notifyEntity.setNotifyfromid(did);
        notifyEntity.setNotifytype(""+2);
        notifyEntity.setNotifytitle("有人点赞了你的需求");
        notifyEntity.setUserid(uid);
        notifyEntity.setNotifycontent(issueDemandEntity.getIssue_Demand_Title());
        int i = notifyDao.insertOneNotify(notifyEntity);
        d.setData((like+like1+i)/3);
        return d;
    }
}
