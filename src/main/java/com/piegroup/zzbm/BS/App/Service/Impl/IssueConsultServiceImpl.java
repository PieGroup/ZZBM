package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueConsultServiceIF;
import com.piegroup.zzbm.Dao.IssueConsultDao;
import com.piegroup.zzbm.Dao.IssueLableDao;
import com.piegroup.zzbm.Dao.IssueStatusDao;
import com.piegroup.zzbm.Dao.IssueUserDao;
import com.piegroup.zzbm.Entity.IssueConsultEntity;
import com.piegroup.zzbm.Entity.IssueLableEntity;
import com.piegroup.zzbm.Entity.IssueStatusEntity;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Utils.F2C;
import com.piegroup.zzbm.Utils.PaginationUtil;
import com.piegroup.zzbm.VO.ConsultUserVo;
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
public class IssueConsultServiceImpl implements IssueConsultServiceIF {
    @Resource
    IssueConsultDao issueConsultDao;

    @Resource
    IssueUserDao issueUserDao;

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
            UserEntity buserEntity = issueUserDao.selectUById(I.getIssue_consult_buserid());
            UserEntity userEntity = issueUserDao.selectUById(I.getIssue_consult_userid());
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
    public DataPageSubc loadById(String Cid) throws Exception {
        DataPageSubc dataPageSubc=new DataPageSubc();
        IssueConsultEntity issueConsultEntity = issueConsultDao.loadById(Cid);
        if(issueConsultEntity==null)
            return null;
        UserEntity userEntity = issueUserDao.selectUById(issueConsultEntity.getIssue_consult_userid());
        UserEntity userEntity1 = issueUserDao.selectUById(issueConsultEntity.getIssue_consult_buserid());
        ConsultUserVo cuv=new ConsultUserVo();
        F2C.father2child(issueConsultEntity,cuv);
        cuv.setUser(userEntity);
        cuv.setBuser(userEntity1);

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
            issueLableEntity = issueLableDao.loadByIssueId(i.getIssue_consult_id());
            IssueStatusEntity issueStatusEntity = issueStatusDao.loadById(i.getIssue_consult_issueStatusid());
            map.put("label",issueLableEntity);
            map.put("status",issueStatusEntity);
            list.add(map);

        }
        DataPageSubc dataPageSubc = new DataPageSubc();
        dataPageSubc.setData(list);
        dataPageSubc.setPaginationSubC(paginationSubC);

        return dataPageSubc;
    }
}
