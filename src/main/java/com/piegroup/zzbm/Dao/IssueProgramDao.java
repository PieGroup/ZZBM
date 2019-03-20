package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueConsultEntity;
import com.piegroup.zzbm.Entity.IssueProgramEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IssueProgramDao {
    @Select("select count(*) from issue_program")
    int count();

    @Select("select * from issue_program where issue_program_issueStatusid=1 order by issue_program_time desc limit #{index},#{pageSize}")
    List<IssueProgramEntity> list(@Param("index")int fromIndex,@Param("pageSize")int pageSize);

    @Insert("insert into issue_program(" +
            "issue_program_id,issue_program_userid,issue_program_title,issue_program_content,issue_program_anonymous,issue_program_reward,issue_program_issueStatusid,issue_program_annexid)" +
            " values(#{issue_program_id},#{issue_program_userid},#{issue_program_title},#{issue_program_content},#{issue_program_anonymous},#{issue_program_reward},#{issue_program_issueStatusid},#{issue_program_annexid});")
    int addProgram(IssueProgramEntity I);

    @Update("update issue_program set issue_program_issueStatusid=#{1} where issue_program_id=#{2};")
    int changePro(@Param(value = "1") int statusid,@Param(value = "2") String proid);

    @Select("select count(*) from issue_program where issue_program_userid = #{0}")
    int countByUserid(@Param("0") String user_id);

    @Select("select * from issue_program where issue_program_userid = #{0} limit #{1} , #{2}")
    List<IssueProgramEntity> loadByUserId(@Param("0") String user_id, @Param("1") int fromIndex,@Param("2") int pageSize);
}
