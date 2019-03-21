package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueDemandEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IssueDemandDao {
    @Select("select count(*) from issue_demand")
    int count();


    @Select("select * from issue_demand where issue_demand_issueStatusid = 1 order by issue_demand_time desc limit #{index},#{pageSize}")
    List<IssueDemandEntity> list(@Param("index") int fromIndex, @Param("pageSize") int pageSize);

    @Insert("insert into issue_demand(" +
            "issue_demand_id,issue_demand_title,issue_demand_table,issue_demand_content,issue_demand_anonymous,issue_Demand_Userid,issue_demand_annexid)" +
            " values(#{issue_Demand_Id},#{issue_Demand_Title},#{issue_Demand_Table},#{issue_Demand_Content},#{issue_Demand_Anonymous},#{issue_Demand_Userid},#{issue_Demand_Annexid});")
    int addDemand(IssueDemandEntity I);

    @Update("update issue_demand set issue_demand_issueStatusid=#{1} where issue_demand_id=#{2};")
    int change(@Param(value = "1") int statusid, @Param(value = "2") String demandid);

    @Select("select count(*) from issue_demand where issue_demand_userid = #{0}")
    int countByUserid(@Param("0") String user_id);

    @Select("select * from issue_demand where issue_demand_userid = #{0} limit #{1} , #{2}")
    List<IssueDemandEntity> loadByUserId(@Param("0") String user_id, @Param("1") int fromIndex, @Param("2") int pageSize);

    @Select("select * from issue_demand where issue_demand_id=#{1};")
    IssueDemandEntity queryByDid(@Param(value = "1")String did);

    @Update("update issue_demand set issue_demand_like=issue_demand_like+1 where issue_demand_id=#{1};")
    int like(@Param(value = "1")String did);

    @Update("update issue_demand set issue_demand_read=issue_demand_read+1 where issue_demand_id=#{1};")
    int read(@Param(value = "1")String did);
}
