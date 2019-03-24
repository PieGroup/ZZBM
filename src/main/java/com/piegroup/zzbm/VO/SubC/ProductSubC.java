package com.piegroup.zzbm.VO.SubC;

import lombok.Data;

/**
 * @ClassName ProductSubc
 * @Description TODO
 * @Author DDLD
 * @Date 2019/3/24 13:24
 * @ModifyDate 2019/3/24 13:24
 * @Version 1.0
 */
@Data
public class ProductSubC {

    //商品id
    private String product_id;
    //数量
    private int num;

    public ProductSubC(String product_id, int num) {
        this.product_id = product_id;
        this.num = num;
    }
    public ProductSubC(){

    }
}
