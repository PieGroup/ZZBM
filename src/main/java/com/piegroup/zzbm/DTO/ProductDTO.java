package com.piegroup.zzbm.DTO;

import com.piegroup.zzbm.VO.SubC.ProductSubC;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ProductDTO
 * @Description TODO 下单参数
 * @Author DDLD
 * @Date 2019/3/24 12:04
 * @ModifyDate 2019/3/24 12:04
 * @Version 1.0
 */
@Data
public class ProductDTO {

    private List<ProductSubC> productSubCs;  //商品id

}
