package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.OrderServiceIF;
import com.piegroup.zzbm.BS.App.Service.ProductServiceIF;
import com.piegroup.zzbm.BS.Bg.Exceptions.Exceptions;
import com.piegroup.zzbm.DTO.OrderDTO;
import com.piegroup.zzbm.DTO.ProductDTO;
import com.piegroup.zzbm.Dao.OrderDetailDao;
import com.piegroup.zzbm.Dao.OrderMasterDao;
import com.piegroup.zzbm.Dao.ProductDao;
import com.piegroup.zzbm.Entity.OrderDetailEntity;
import com.piegroup.zzbm.Entity.OrderMasterEntity;
import com.piegroup.zzbm.Entity.ProductEntity;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Enums.PayStyleEnum;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.TimeUtil2;
import com.piegroup.zzbm.VO.SubC.ProductSubC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author DDLD
 * @Date 2019/3/24 12:23
 * @ModifyDate 2019/3/24 12:23
 * @Version 1.0
 */

@Service
public class OrderServiceIFImpl implements OrderServiceIF {

    @Resource
    ProductDao productDao;

    @Resource
    OrderDetailDao orderDetailDao;

    @Resource
    OrderMasterDao orderMasterDao;

    @Autowired
    ProductServiceIF productServiceIF;


    //创建订单
    @Override
    @Transactional
    public OrderDTO create(UserEntity userEntity ,ProductDTO productDTO) {

        OrderDTO orderDTO = new OrderDTO();

        List<OrderDetailEntity> list = new ArrayList<OrderDetailEntity>();

        String orderId = TimeUtil2.TimestampNow()+ RandomNumberUtil.createRandom(true,8);
        int orderAmount = 0;
        // 1、查询商品（数量价格）

       for(ProductSubC productSubC :productDTO.getProductSubCs()){

         String product = productSubC.getProduct_id();
         int num = productSubC.getNum();



           ProductEntity productEntity = productDao.loadByProId(product);
           if (productEntity == null)
               throw new Exceptions(ExceptionEnum.Product_Null_Exception);

           //2、计算订单总价  单位为 分
            int productAmount = Integer.parseInt(productEntity.getProduct_Money())
                                * num;
            orderAmount += productAmount;

            //3、订单详情入库
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity(
                    TimeUtil2.TimestampNow()+RandomNumberUtil.createRandom(true,8),
                    orderId,
                    product,
                    TimeUtil2.SQLTimestampNow(),
                    TimeUtil2.SQLTimestampNow(),
                    num,
                    String.valueOf(productAmount));

            //先添加到数据库，再添加到OrderDTO
           orderDetailDao.save(orderDetailEntity);
           list.add(orderDetailEntity);

//            //扣除库存保存
//            int stock = productEntity.getProduct_Stock() - num;
//            productDao.updateStock(productEntity.getProduct_Id(),stock);

        }

        //4、写入订单数据库（orderMaster）
        OrderMasterEntity orderMasterEntity = new OrderMasterEntity(
                orderId,
                userEntity.getUser_Id(),
                String.valueOf(orderAmount),
                PayStyleEnum.WCPay_H5_PayStyle.getMessage(),
                TimeUtil2.SQLTimestampNow(),
                TimeUtil2.SQLTimestampNow()
        );

        if (userEntity.getUser_Wcid()!=null && !userEntity.getUser_Wcid().equals(""))
            orderMasterEntity.setOrder_Master_Buyer_Openid(userEntity.getUser_Wcid());
        if (userEntity.getUser_Phone() !=null && !userEntity.getUser_Phone().equals(""))
            orderMasterEntity.setOrder_Master_Phone(userEntity.getUser_Phone());
        //保存
        orderMasterDao.save(orderMasterEntity);

        //4、扣库存 // 可以不用这么写，因为本身传进来的就是list productid num ,如果不是的话，就要使用这下面这种lambda 表达式来完成


       /* List<ProductSubC> productSubClist = productDTO.getProductSubCs().stream().map(
                e -> new ProductSubC(e.getProduct_id(),e.getNum())
        ).collect(Collectors.toList());*/

       productServiceIF.decreaseStock(productDTO.getProductSubCs());

        orderDTO.setOrderDetailEntities(list);
        orderDTO.setOrderMasterEntity(orderMasterEntity);

        return orderDTO;

    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String user_id, String pageNum, String pageSize) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }
}
