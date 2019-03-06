package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueDemandEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IssueDemandDao {

    @Select("select count(*) from issue_demand")
    int count();

    List<IssueDemandEntity> list(int fromIndex, int pageSize);
}
