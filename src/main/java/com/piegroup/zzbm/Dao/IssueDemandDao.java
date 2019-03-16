package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueDemandEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IssueDemandDao {
    @Select("select count(*) from issue_demand")
    int count();


    @Select("select * from issue_demand where issue_demand_issueStatusid = 1 limit #{index},#{pageSize}")
    List<IssueDemandEntity> list(@Param("index") int fromIndex, @Param("pageSize") int pageSize);

    @Insert("insert into issue_demand(" +
            "issue_demand_id,issue_demand_title,issue_demand_content,issue_demand_issueStatusid,issue_demand_anonymous,issue_demand_userid,issue_demand_annexid)" +
            " values(#{issue_demand_id},#{issue_demand_title},#{issue_demand_content},#{issue_demand_issueStatusid},#{issue_demand_anonymous},#{issue_demand_userid},#{issue_demand_annexid});")
    int addDemand(IssueDemandEntity I);

    @Update("update issue_demand set issue_demand_issueStatusid=#{1} where issue_demand_id=#{2};")
    int change(@Param(value = "1") int statusid, @Param(value = "2") String demandid);

}
