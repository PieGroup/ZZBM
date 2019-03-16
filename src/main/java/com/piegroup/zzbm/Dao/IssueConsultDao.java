package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueConsultEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IssueConsultDao {
    @Select("select count(*) from issue_consult")
    int count();


    @Select("select * from issue_consult where issue_consult_issueStatusid=1 limit #{index},#{pageSize}")
    @Results({
            @Result(column = "uid",property = "buserid.id"),
    })
    List<IssueConsultEntity> list(@Param("index") int fromIndex, @Param("pageSize") int pageSize);

    @Insert("insert into issue_consult(" +
            "issue_consult_id,issue_consult_userid,issue_consult_type,issue_consult_buserid,issue_consult_paidLookReply,issue_consult_issueStatusid,issue_consult_points,issue_consult_title,issue_consult_content,issue_consult_anonymous,issue_consult_annexid,issue_consult_paid)" +
            " values(#{issue_consult_id},#{issue_consult_userid},#{issue_consult_type},#{issue_consult_buserid},#{issue_consult_paidLookReply},#{issue_consult_issueStatusid},#{issue_consult_points},#{issue_consult_title},#{issue_consult_content},#{issue_consult_anonymous},#{issue_consult_annexid},#{issue_consult_paid});")
    int addProgram(IssueConsultEntity I);

    @Update("update issue_consult set issue_consult_issueStatusid=#{1} where issue_consult_id=#{2};")
    int changePro(@Param(value = "1") int statusid, @Param(value = "2") String conid);

}
