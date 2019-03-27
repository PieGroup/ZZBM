package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueConsultEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IssueConsultDao {
    @Select("select count(*) from issue_consult")
    int count();

    @Select("select * from issue_consult where issue_consult_id=#{1};")
    IssueConsultEntity loadById(@Param(value = "1")String cid);

    @Select("select * from issue_consult where issue_consult_issueStatusid=1 order by issue_consult_time desc limit #{index},#{pageSize}")
    List<IssueConsultEntity> list(@Param("index") int fromIndex, @Param("pageSize") int pageSize);

    @Insert("insert into issue_consult(" +
            "issue_consult_id,issue_consult_userid,issue_consult_type,issue_consult_buserid,issue_consult_paidLookReply,issue_consult_points,issue_consult_title,issue_consult_content,issue_consult_anonymous,issue_consult_annexid,issue_consult_paid)" +
            " values(#{issue_Consult_Id},#{issue_Consult_Userid},#{issue_Consult_Type},#{issue_Consult_Buserid},#{issue_Consult_Paidlookreply},#{issue_Consult_Points},#{issue_Consult_Title},#{issue_Consult_Content},#{issue_Consult_Anonymous},#{issue_Consult_Annexid},#{issue_Consult_Paid});")
    int addProgram(IssueConsultEntity I);

    @Update("update issue_consult set issue_consult_issueStatusid=#{1} where issue_consult_id=#{2};")
    int changePro(@Param(value = "1") int statusid, @Param(value = "2") String conid);

    @Update("update issue_consult set issue_consult_like=issue_consult_like+1 where issue_consult_id=#{1};")
    int like(@Param(value = "1")String cid);

    @Select("select count(*) from issue_consult where issue_consult_userid = #{0}")
    int countByUserid(@Param("0") String user_id);

    @Select("select * from issue_consult where issue_consult_userid = #{0} limit #{1} , #{2}")
    List<IssueConsultEntity> loadByUserId(@Param("0") String user_id,@Param("1") int fromIndex,@Param("2") int pageSize);
}
