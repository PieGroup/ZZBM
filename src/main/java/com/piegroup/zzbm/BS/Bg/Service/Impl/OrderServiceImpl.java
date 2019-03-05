package com.piegroup.zzbm.BS.Bg.Service.Impl;

import com.piegroup.zzbm.BS.Bg.Service.OrderService;
import com.piegroup.zzbm.Dao.OrderDao;
import com.piegroup.zzbm.Entity.OrderMasterEntity;
import com.piegroup.zzbm.Utils.PaginationUtil;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl extends OrderService {

    @Resource
    private OrderDao orderDao;

    public OrderMasterEntity findOne(String orderId){
        return orderDao.findOne(orderId);
    }

    /**
     * 显示个人订单，无论是任何状态
     * @param UserId
     * @return
     */
    //使用父类方法，OrderAS OrderList()
    // 使用读序列化隔离级别，适用于非高并发且需要保证数据一致性的场景
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public DataPageSubc<List<OrderMasterEntity>> OrderListSvImpl(String UserId, int pageNum, int pageSize) {
        /*
         * 但是分页还是要体现出来，不是说，查完数据在分页，而是使用，sql语句拿数据
         */
        DataPageSubc dataPageSubc = new DataPageSubc();

        //count 是指获取Order表的数据条数，主要是获取count 值，才声明上面的 orderEntities
        int count = orderDao.OrderSize();
        /**
         * @pageNum 第几页，最小1，
         * @pageSize 去几条，默认最小值为 1
         * @count 数据表的总数
         * @return  一个分页对象R_pagination
         */
        PaginationSubC paginationSubC = PaginationUtil.pagination(pageNum,pageSize,count);
        //System.out.println("分页信息"+r_pagination);
        //模仿 去数据库拿值
        List<OrderMasterEntity> orderEntities =  orderDao.loadPage(UserId, paginationSubC.getFromIndex(), paginationSubC.getPageSize());


        System.out.println(UserId+"订单"+orderEntities);

        dataPageSubc.setPaginationSubC(paginationSubC);
        dataPageSubc.setData(orderEntities);
        return dataPageSubc;
    }

    public int test(){
        return orderDao.test();
    }
}
