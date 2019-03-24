package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.ProductServiceIF;
import com.piegroup.zzbm.BS.Bg.Exceptions.Exceptions;
import com.piegroup.zzbm.Dao.ProductDao;
import com.piegroup.zzbm.Entity.ProductEntity;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.VO.SubC.ProductSubC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ProductServiceImlpl
 * @Description TODO
 * @Author DDLD
 * @Date 2019/3/24 14:39
 * @ModifyDate 2019/3/24 14:39
 * @Version 1.0
 */
@Service
public class ProductServiceImlpl implements ProductServiceIF {


    @Resource
    ProductDao productDao;

    //减库存
    @Override
    @Transactional
    public void decreaseStock(List<ProductSubC> productSubC) {
        for (ProductSubC p: productSubC){
           ProductEntity productEntity =  productDao.loadByProId(p.getProduct_id());
           if (productEntity == null)
               throw new Exceptions(ExceptionEnum.Product_Null_Exception);
           int result = productEntity.getProduct_Stock() - p.getNum();
           if (result < 0)
               throw new Exceptions(ExceptionEnum.Product_Stock_Error);

           productDao.updateStock(p.getProduct_id(),result);
        }
    }
}
