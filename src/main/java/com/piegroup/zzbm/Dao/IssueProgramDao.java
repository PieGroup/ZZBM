package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueConsultEntity;
import com.piegroup.zzbm.Entity.IssueProgramEntity;
import com.piegroup.zzbm.Entity.IssueQuestionsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IssueProgramDao {
    @Select("select count(*) from issue_program")
    int count();

    @Select("select * from issue_program where issue_program_issueStatusid=1 order by issue_program_time desc limit #{index},#{pageSize}")
    List<IssueProgramEntity> list(@Param("index")int fromIndex,@Param("pageSize")int pageSize);

    @Select("select * from issue_program where issue_program_id=#{1};")
    IssueProgramEntity queryByPid(@Param(value = "1")String pid);

    @Insert("insert into issue_program(" +
            "issue_program_id,issue_program_userid,issue_program_title,issue_program_table,issue_program_content,issue_program_anonymous,issue_program_reward,issue_program_pic)" +
            " values(#{issue_Program_Id},#{issue_Program_Userid},#{issue_Program_Title},#{issue_Program_Table},#{issue_Program_Content},#{issue_Program_Anonymous},#{issue_Program_Reward},#{issue_Program_Pic});")
    int addProgram(IssueProgramEntity I);

    @Update("update issue_program set issue_program_issueStatusid=#{1} where issue_program_id=#{2};")
    int changePro(@Param(value = "1") int statusid,@Param(value = "2") String proid);

    @Select("select count(*) from issue_program where issue_program_userid = #{0}")
    int countByUserid(@Param("0") String user_id);

    @Select("select * from issue_program where issue_program_userid = #{0} limit #{1} , #{2}")
    List<IssueProgramEntity> loadByUserId(@Param("0") String user_id, @Param("1") int fromIndex,@Param("2") int pageSize);

    @Update("update issue_program set issue_program_read=issue_program_read+1 where issue_program_id=#{1};")
    int read(@Param(value = "1")String pid);

    @Update("update issue_program set issue_program_like=issue_program_like+1 where issue_program_id=#{1};")
    int like(@Param(value = "1")String pid);

}
