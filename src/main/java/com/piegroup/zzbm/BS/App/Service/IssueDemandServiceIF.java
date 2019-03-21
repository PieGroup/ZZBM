package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.Entity.IssueDemandEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;

public interface IssueDemandServiceIF {
    DataPageSubc list(int pageSize, int pageNum) throws Exception;
    DataPageSubc Insert(IssueDemandEntity i);
    DataPageSubc change(int status, String id);

    DataPageSubc loadByUserId(String user_id,int pageSize,int pageNum);

    DataPageSubc loadByDemandId(String did,String uid) throws Exception;

    DataPageSubc like(String did,String uid);
}
