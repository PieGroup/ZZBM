package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueProgramEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IssueConsultDao {

    @Select("select count(*) from issue_demand")
    int count();

    List<IssueProgramEntity> list(int fromIndex, int pageSize);
}
