package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueStatusEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IssueStatusDao {

    //通过Issue id 获取状态
    @Select("select * from issue_status where issue_status_id = #{0} ")
    public IssueStatusEntity loadById(@Param("0") int id);

}
