package com.piegroup.zzbm.BS.App.Service.Impl;

import ch.qos.logback.core.util.TimeUtil;
import com.piegroup.zzbm.BS.App.Service.IssueConsultServiceIF;
import com.piegroup.zzbm.Dao.*;
import com.piegroup.zzbm.Entity.*;
import com.piegroup.zzbm.Utils.F2C;
import com.piegroup.zzbm.Utils.PaginationUtil;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.TimeUtil2;
import com.piegroup.zzbm.VO.ConsultUserVo;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class IssueConsultServiceImpl implements IssueConsultServiceIF {
    @Resource
    IssueConsultDao issueConsultDao;

    @Resource
    IssueUserDao issueUserDao;

    @Resource
    likeDao likeDao;
    @Resource
    notifyDao notifyDao;
    @Resource
    IssueStatusDao issueStatusDao;

    @Resource
    IssueLableDao issueLableDao;

    @Override
    public DataPageSubc list(int pageSize, int pageNum) throws Exception {
        DataPageSubc dataPageSubc=new DataPageSubc();
        int count=issueConsultDao.count();
        boolean nextp=(pageSize*pageNum) < count;
        PaginationSubC paginationSubC=new PaginationSubC(pageNum,pageSize,pageNum,nextp);

        List<IssueConsultEntity> cls=issueConsultDao.list((paginationSubC.getFromIndex()-1)*paginationSubC.getPageSize(),
                paginationSubC.getPageSize());
        List<ConsultUserVo> realD=new ArrayList<>();
        for (IssueConsultEntity I : cls) {
            ConsultUserVo cuv= new ConsultUserVo();
            UserEntity buserEntity = issueUserDao.selectUById(I.getIssue_Consult_Buserid());
            UserEntity userEntity ;
            if (I.getIssue_Consult_Anonymous()==1){
                userEntity= issueUserDao.selectUById("defaultid");
                userEntity.setUser_Id(I.getIssue_Consult_Userid());
            }
            else{
                userEntity= issueUserDao.selectUById(I.getIssue_Consult_Userid());}
            F2C.father2child(I,cuv);
            cuv.setUser(userEntity);
            cuv.setBuser(buserEntity);
            realD.add(cuv);
        }

        dataPageSubc.setPaginationSubC(paginationSubC);
        dataPageSubc.setData(realD);
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

    @Override
    public DataPageSubc loadById(String Cid,String uid) throws Exception {
        DataPageSubc dataPageSubc=new DataPageSubc();
        IssueConsultEntity issueConsultEntity = issueConsultDao.loadById(Cid);
        if(issueConsultEntity==null)
            return null;
        UserEntity userEntity ;
        if (issueConsultEntity.getIssue_Consult_Anonymous()==1){
            userEntity= issueUserDao.selectUById("defaultid");
            userEntity.setUser_Id(issueConsultEntity.getIssue_Consult_Userid());
        }
        else{
            userEntity= issueUserDao.selectUById(issueConsultEntity.getIssue_Consult_Userid());}
        UserEntity userEntity1 = issueUserDao.selectUById(issueConsultEntity.getIssue_Consult_Buserid());
        ConsultUserVo cuv=new ConsultUserVo();
        F2C.father2child(issueConsultEntity,cuv);
        cuv.setUser(userEntity);
        cuv.setBuser(userEntity1);
        if(likeDao.checkLike(uid,issueConsultEntity.getIssue_Consult_Id()) != null )
            cuv.setIslike(1);
        dataPageSubc.setData(cuv);
        return dataPageSubc;
    }

    //用户的咨询
    @Override
    public DataPageSubc loadByUserId(String user_id, int pageSize, int pageNum) {

        int count = issueConsultDao.countByUserid(user_id);

        PaginationSubC paginationSubC = PaginationUtil.pagination(pageNum,pageSize,count);
        List<IssueConsultEntity> issueConsults = issueConsultDao.loadByUserId(user_id,paginationSubC.getFromIndex(),paginationSubC.getPageSize());

        List list = new ArrayList();
        List<IssueLableEntity> issueLableEntity = new ArrayList<>();


        for (IssueConsultEntity i: issueConsults) {
            Map map = new HashMap();
            map.put("entity",i);
            issueLableEntity = issueLableDao.loadByIssueId(i.getIssue_Consult_Id());
            IssueStatusEntity issueStatusEntity = issueStatusDao.loadById(i.getIssue_Consult_Issuestatusid());
            map.put("label",issueLableEntity);
            map.put("status",issueStatusEntity);
            list.add(map);

        }
        DataPageSubc dataPageSubc = new DataPageSubc();
        dataPageSubc.setData(list);
        dataPageSubc.setPaginationSubC(paginationSubC);

        return dataPageSubc;
    }

    @Override
    public DataPageSubc like(String Cid,String userid) throws Exception {
        DataPageSubc d=new DataPageSubc();
        int like1 = likeDao.like(new IssueLikeEntity(Cid, userid));
        int like = issueConsultDao.like(Cid);
        IssueConsultEntity issueConsultEntity = issueConsultDao.loadById(Cid);
        String notifyid="noti"+ TimeUtil2.TimestampNow()+ RandomNumberUtil.createRandom(true,5);
        IssueNotifyEntity notifyEntity=new IssueNotifyEntity();
        notifyEntity.setNotifyid(notifyid);
        notifyEntity.setNotifyfromid(Cid);
        notifyEntity.setNotifytype("1");
        notifyEntity.setNotifytitle("有人点赞了你的咨询");
        notifyEntity.setUserid(userid);
        notifyEntity.setNotifycontent(issueConsultEntity.getIssue_Consult_Title());
        int i = notifyDao.insertOneNotify(notifyEntity);
        d.setData((like+like1+i)/3);
        return d;
    }
}
