package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.Dao.notifyDao;
import com.piegroup.zzbm.Entity.IssueNotifyEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IssueNotifyServiceImpl {
    @Resource
    notifyDao notifydao;

    public DataPageSubc myNotify(String uid, String type, int pageSize, int pageNum){
        DataPageSubc d=new DataPageSubc();
        PaginationSubC pagedata=new PaginationSubC();
        pagedata.setCurrentPage(pageNum);
        pagedata.setPageSize(pageSize);
        int count = notifydao.count(uid,type);
        pagedata.setNextPage(count>pageNum*pageSize);
        pagedata.setFromIndex((pageNum-1)*pageSize);
        d.setPaginationSubC(pagedata);

        List<IssueNotifyEntity> issueNotifyEntities = notifydao.queryNotifyByUserId(uid, type, pagedata.getFromIndex(), pagedata.getPageSize());
        d.setData(issueNotifyEntities);
        return d;
    }

    public DataPageSubc read(String oid){
        DataPageSubc d=new DataPageSubc();
        int read = notifydao.read(oid);
        d.setData(read);
        return (d);
    }
}
