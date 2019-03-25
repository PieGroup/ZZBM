package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.VO.SubC.ProductSubC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
*@ClassName     ProductService
*@Description   TODO
*@Author        DDLD
*@Date          2019/3/24 14:18
*@ModifyDate    2019/3/24 14:18
*@Version       1.0
*/

public interface ProductServiceIF {

    //减库存
    public void decreaseStock(List<ProductSubC> productSubCs);

    //增加库存
    public void increaseStock(List<ProductSubC> productSubCS);
}
