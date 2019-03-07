package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueProgramEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IssueProgramDao {
    @Select("select count(*) from issue_program")
    int count();
    @Select("select * from issue_program limit #{index},#{pageSize}")
    List<IssueProgramEntity> list(@Param("index")int fromIndex,@Param("pageSize")int pageSize);
}
