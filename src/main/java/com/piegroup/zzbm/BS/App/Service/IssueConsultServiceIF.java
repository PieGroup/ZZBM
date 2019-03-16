package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.Entity.IssueConsultEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;

public interface IssueConsultServiceIF {
    DataPageSubc list(int pageSize, int pageNum);
    DataPageSubc Insert(IssueConsultEntity i);

    DataPageSubc change(int status, String id);
}
