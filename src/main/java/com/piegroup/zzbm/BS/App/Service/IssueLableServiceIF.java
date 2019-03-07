package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.VO.SubC.DataPageSubc;

public interface IssueLableServiceIF {


    DataPageSubc allLable();

    DataPageSubc subLable(int pId);

    DataPageSubc allParentLable();
}
