package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.Entity.IssueConsultEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;

public interface IssueConsultServiceIF {
    DataPageSubc list(int pageSize, int pageNum) throws Exception;
    DataPageSubc Insert(IssueConsultEntity i);

    DataPageSubc change(int status, String id);

    DataPageSubc loadById(String Cid,String uid) throws Exception;

    DataPageSubc loadByUserId(String user_id,int pageSize,int pageNum);

    DataPageSubc like(String Cid,String userid) throws Exception;
}
