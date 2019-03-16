package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.Entity.IssueDemandEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;

public interface IssueDemandServiceIF {
    DataPageSubc list(int pageSize, int pageNum);
    DataPageSubc Insert(IssueDemandEntity i);
    DataPageSubc change(int status, String id);
}
