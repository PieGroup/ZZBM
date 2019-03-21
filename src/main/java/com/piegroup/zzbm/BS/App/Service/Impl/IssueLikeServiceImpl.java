package com.piegroup.zzbm.BS.App.Service.Impl;

import com.piegroup.zzbm.Dao.UserDao;
import com.piegroup.zzbm.Dao.likeDao;
import com.piegroup.zzbm.Entity.IssueLikeEntity;
import com.piegroup.zzbm.Entity.UserEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import com.piegroup.zzbm.VO.SubC.PaginationSubC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class IssueLikeServiceImpl {
    @Resource
    likeDao likedao;
    @Resource
    UserDao userDao;

    public DataPageSubc allLikeUser(String itemid, int pageNum, int pageSize){
        DataPageSubc d=new DataPageSubc();
        PaginationSubC pagedata=new PaginationSubC();
        pagedata.setCurrentPage(pageNum);
        pagedata.setPageSize(pageSize);
        int count = likedao.count(itemid);
        pagedata.setNextPage(count>pageNum*pageSize);
        pagedata.setFromIndex((pageNum-1)*pageSize);
        d.setPaginationSubC(pagedata);
        List<UserEntity> users=new ArrayList<>();

        for (IssueLikeEntity i :likedao.allLikeByItemId(itemid,pagedata.getFromIndex(),pagedata.getPageSize())) {
            users.add(userDao.queryByUserId(i.getLike_User()));
        }
        d.setData(users);
        return d;
    }
}
