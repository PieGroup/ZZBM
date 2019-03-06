package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;

public interface IssueDemandServiceIF {
    DataPageSubc list(int pageSize, int pageNum);
}
