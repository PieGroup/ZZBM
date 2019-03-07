package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.BS.App.Service.IssueDemandServiceIF;
import com.piegroup.zzbm.Dao.IssueDemandDao;
import com.piegroup.zzbm.Entity.IssueDemandEntity;
import com.piegroup.zzbm.Utils.PaginationUtil;
import com.piegroup.zzbm.VO.DataVO;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class IssueDemandServiceImpl implements IssueDemandServiceIF {


    @Resource
    IssueDemandDao issueDemandDao;

    @Override
    public DataPageSubc<List<IssueDemandEntity>> list(int pageSize, int pageNum)  {

        DataPageSubc dataPageSubc = new DataPageSubc();

        /**
         * @pageNum 第几页，最小1，
         * @pageSize 去几条，默认最小值为 1
         * @count 数据表的总数
         * @return  一个分页对象R_pagination
         */
        int count = issueDemandDao.count();
        //分页对象
        PaginationSubC paginationSubC = PaginationUtil.pagination(pageNum,pageSize,count);

        //返回List<issue_demand> 对象   getFromIndex查询开始，getPageSize查询一共条数
        List<IssueDemandEntity> issueDemandEntities = issueDemandDao.list(paginationSubC.getFromIndex(), paginationSubC.getPageSize());
        //将数据和分页信息保存起来
        dataPageSubc.setData(issueDemandEntities);
        dataPageSubc.setPaginationSubC(paginationSubC);


        return dataPageSubc;
    }
}
