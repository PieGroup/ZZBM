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
import com.piegroup.zzbm.Enums.OrderStatusEnum;
import com.piegroup.zzbm.Enums.PayStatusEnum;
import com.piegroup.zzbm.Enums.PayStyleEnum;
import com.piegroup.zzbm.Utils.PaginationUtil;
import com.piegroup.zzbm.Utils.RandomNumberUtil;
import com.piegroup.zzbm.Utils.TimeUtil2;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import com.piegroup.zzbm.VO.SubC.ProductSubC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
@Slf4j
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

    //查询单个订单
    @Override
    public OrderDTO findOne(String orderId) {


        OrderMasterEntity orderMasterEntity = orderMasterDao.findOne(orderId);
        if (orderMasterEntity == null)
            throw new Exceptions(ExceptionEnum.Order_Null_Exception);
        List<OrderDetailEntity> orderDetailEntities = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailEntities))
            throw new Exceptions(ExceptionEnum.Product_Null_Exception);

        OrderDTO orderDTO = new OrderDTO();
       orderDTO.setOrderMasterEntity(orderMasterEntity);
       orderDTO.setOrderDetailEntities(orderDetailEntities);

        return orderDTO;
    }

    @Override
    public DataPageSubc findList(String user_id, int pageNum, int pageSize) {

        int count = orderMasterDao.SizeByUserId(user_id);

        PaginationSubC paginationSubC = PaginationUtil.pagination(pageNum,pageSize,count);

        List<OrderMasterEntity> orderMasterEntities = orderMasterDao.findList(user_id,paginationSubC.getFromIndex(),paginationSubC.getPageSize());
        DataPageSubc dataPageSubc = new DataPageSubc();
        dataPageSubc.setData(orderMasterEntities);
        dataPageSubc.setPaginationSubC(paginationSubC);

        return dataPageSubc;
    }

    //取消订单
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        //判断订单状态
        if (!(orderDTO.getOrderMasterEntity().getOrder_Master_Status() == Integer.parseInt(OrderStatusEnum.Default_OrderState.getCode()))){
            log.error("【取消订单】订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderMasterEntity().getOrder_Master_Id(),orderDTO.getOrderMasterEntity().getOrder_Master_Status());
            throw new Exceptions(ExceptionEnum.Order_Status_Error);
        }

        //修改订单状态

        orderDTO.getOrderMasterEntity().setOrder_Master_Status(Integer.parseInt(OrderStatusEnum.Cancel_OrderState.getCode()));
        boolean b  = orderMasterDao.UpOrderStatus(TimeUtil2.SQLTimestampNow(),orderDTO.getOrderMasterEntity().getOrder_Master_Id(),Integer.parseInt(OrderStatusEnum.Cancel_OrderState.getCode()));
        if (b == false){
            log.error("【取消订单】更新失败，orderMaster={}",orderDTO.getOrderMasterEntity());
            throw new Exceptions(ExceptionEnum.Order_UP_Status_Error);
        }

        //返还库存

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailEntities())){
            log.error("【取消订单】订单中无商品详情，orderMaster={}",orderDTO.getOrderMasterEntity());
            throw new Exceptions(ExceptionEnum.Product_Stock_Error);
        }
        List<ProductSubC> productSubCS = orderDTO.getOrderDetailEntities().stream()
                .map(e -> new ProductSubC(e.getOrder_Detail_Productid(),e.getOrder_Detail_Quantity()))
                .collect(Collectors.toList());
        productServiceIF.increaseStock(productSubCS);

        //如果已支付，需要退款

        if (orderDTO.getOrderMasterEntity().getOrder_Master_Pay_Status() == Integer.parseInt( PayStatusEnum.Pay_Success_PayStatus.getCode())){
            //TODO
        }

        return orderDTO;
    }

    //完结订单
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(!String.valueOf(orderDTO.getOrderMasterEntity().getOrder_Master_Status()).equals(OrderStatusEnum.Default_OrderState.getCode())){
            log.error("【完结订单】订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderMasterEntity().getOrder_Master_Id(),orderDTO.getOrderMasterEntity().getOrder_Master_Status());
            throw new Exceptions(ExceptionEnum.Order_Status_Error);
        }
        //修改订单状态
        orderDTO.getOrderMasterEntity().setOrder_Master_Status(Integer.parseInt(OrderStatusEnum.Success_OrderState.getCode()));
        boolean b  = orderMasterDao.UpOrderStatus(TimeUtil2.SQLTimestampNow(),orderDTO.getOrderMasterEntity().getOrder_Master_Id(),Integer.parseInt(OrderStatusEnum.Success_OrderState.getCode()));
        if (b == false){
            log.error("【完结订单】更新失败，orderMaster={}",orderDTO.getOrderMasterEntity());
            throw new Exceptions(ExceptionEnum.Order_UP_Status_Error);
        }

        return orderDTO;
    }

    //修改支付
    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {

        //判断订单状态
        if(!String.valueOf(orderDTO.getOrderMasterEntity().getOrder_Master_Status()).equals(OrderStatusEnum.Default_OrderState.getCode())){
            log.error("【订单支付完成】订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderMasterEntity().getOrder_Master_Id(),orderDTO.getOrderMasterEntity().getOrder_Master_Status());
            throw new Exceptions(ExceptionEnum.Order_Status_Error);
        }
        //判断支付状态
        if (!String.valueOf(orderDTO.getOrderMasterEntity().getOrder_Master_Pay_Status()).equals(PayStatusEnum.Pay_Wait_PayStatus.getCode())){
            log.error("【订单支付完成】订单状态不正确，orderDTO={}",orderDTO);
            throw new Exceptions(ExceptionEnum.Order_Status_Error);
        }

        //修改支付状态
        orderDTO.getOrderMasterEntity().setOrder_Master_Pay_Status(Integer.parseInt(PayStatusEnum.Pay_Success_PayStatus.getCode()));
        boolean b  = orderMasterDao.UpPaytatus(TimeUtil2.SQLTimestampNow(),orderDTO.getOrderMasterEntity().getOrder_Master_Id(),Integer.parseInt(PayStatusEnum.Pay_Success_PayStatus.getCode()));
        if (b == false){
            log.error("【订单支付完成】更新失败，orderMaster={}",orderDTO.getOrderMasterEntity());
            throw new Exceptions(ExceptionEnum.Order_UP_Status_Error);
        }


        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }
}
