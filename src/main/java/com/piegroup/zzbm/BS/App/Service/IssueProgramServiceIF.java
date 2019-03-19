package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.Entity.IssueProgramEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;

public interface IssueProgramServiceIF {
    DataPageSubc list(int pageSize, int pageNum);
    DataPageSubc Insert(IssueProgramEntity i);

    DataPageSubc change(int status,String id);

    DataPageSubc loadByUserId(String user_id,int pageSize,int pageNum);
}
