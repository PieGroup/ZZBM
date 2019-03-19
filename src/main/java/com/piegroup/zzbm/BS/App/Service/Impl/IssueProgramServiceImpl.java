package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueProgramServiceIF;
import com.piegroup.zzbm.Dao.IssueLableDao;
import com.piegroup.zzbm.Dao.IssueProgramDao;
import com.piegroup.zzbm.Dao.IssueStatusDao;
import com.piegroup.zzbm.Entity.IssueConsultEntity;
import com.piegroup.zzbm.Entity.IssueLableEntity;
import com.piegroup.zzbm.Entity.IssueProgramEntity;
import com.piegroup.zzbm.Entity.IssueStatusEntity;
import com.piegroup.zzbm.Utils.PaginationUtil;
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
public class IssueProgramServiceImpl implements IssueProgramServiceIF {

    @Resource
    IssueProgramDao issueProgramDao;

    @Resource
    IssueLableDao issueLableDao;

    @Resource
    IssueStatusDao issueStatusDao;

    @Override
    public DataPageSubc list(int pageSize, int pageNum) {
        DataPageSubc dataPageSubc=new DataPageSubc();
        int count=issueProgramDao.count();
        boolean nextp=(pageSize*pageNum) < count;
        PaginationSubC paginationSubC=new PaginationSubC(pageNum,pageSize,pageNum,nextp);

        List<IssueProgramEntity> datas=issueProgramDao.list((paginationSubC.getFromIndex()-1)*paginationSubC.getPageSize(),
                paginationSubC.getPageSize());
        dataPageSubc.setPaginationSubC(paginationSubC);
        dataPageSubc.setData(datas);
        return dataPageSubc;
    }

    @Override
    public DataPageSubc Insert(IssueProgramEntity i) {
        DataPageSubc dataPageSubc=new DataPageSubc();

        int s = issueProgramDao.addProgram(i);
        dataPageSubc.setData(s);
        return dataPageSubc;
    }

    @Override
    public DataPageSubc change(int status, String id) {

        DataPageSubc dataPageSubc=new DataPageSubc();
        int i = issueProgramDao.changePro(status, id);
        dataPageSubc.setData(i);

        return dataPageSubc;
    }

    @Override
    public DataPageSubc loadByUserId(String user_id, int pageSize, int pageNum) {
        int count = issueProgramDao.countByUserid(user_id);

        PaginationSubC paginationSubC = PaginationUtil.pagination(pageNum,pageSize,count);
        List<IssueProgramEntity> issueProgramEntities = issueProgramDao.loadByUserId(user_id,paginationSubC.getFromIndex(),paginationSubC.getPageSize());

        List list = new ArrayList();
        List<IssueLableEntity> issueLableEntity = new ArrayList<>();
        for (IssueProgramEntity i: issueProgramEntities) {
            Map map = new HashMap();
            map.put("entity",i);
            List<IssueLableEntity> lableEntities = issueLableDao.loadByIssueId(i.getIssue_program_id());
            IssueStatusEntity issueStatusEntity = issueStatusDao.loadById(i.getIssue_program_issueStatusid());

            map.put("label",lableEntities);
            map.put("status",issueStatusEntity);
            list.add(map);
        }

        DataPageSubc dataPageSubc = new DataPageSubc();
        dataPageSubc.setPaginationSubC(paginationSubC);
        dataPageSubc.setData(list);

        return dataPageSubc;

    }
}
