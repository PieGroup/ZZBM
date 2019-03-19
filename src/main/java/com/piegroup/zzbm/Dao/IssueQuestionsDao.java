package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueQuestionsEntity;
import org.apache.ibatis.annotations.*;

import javax.print.DocFlavor;
import java.util.List;

@Mapper
public interface IssueQuestionsDao {

    @Select("select count(*) from issue_questions")
    int count();


    @Select("select * from issue_questions where issue_questions_issueStatusid = 1 limit #{index},#{pageSize}")
    List<IssueQuestionsEntity> list(@Param("index") int fromIndex, @Param("pageSize") int pageSize);

    @Insert("insert into issue_questions(" +
            "issue_questions_id,issue_questions_userid,issue_questions_title,issue_questions_generalize,issue_questions_value,issue_questions_anonymous,issue_questions_annexid)" +
            " values(#{issue_Questions_Id},#{issue_Questions_Userid},#{issue_Questions_Title},#{issue_Questions_Generalize},#{issue_Questions_Value},#{issue_Questions_Anonymous},#{issue_Questions_Annexid});")
    int addDemand(IssueQuestionsEntity I);

    @Update("update issue_questions set issue_questions_issueStatusid=#{1} where issue_questions_id=#{2};")
    int change(@Param(value = "1") int statusid, @Param(value = "2") String demandid);

    @Update("update issue_questions set issue_questions_accept=1,issue_questions_replyid=#{1} where issue_questions_id=#{2};")
    int caina(@Param(value = "1")String issue_questions_replyid,@Param(value = "2")String issue_questions_id);

    @Select("select count(*) from issue_questions where issue_questions_userid = #{0}")
    int countById(@Param("0") String user_id);


    //取用户id 的多少条记录
    @Select("select * from issue_questions where issue_questions_userid = #{0} limit #{1} , #{2}")
    List<IssueQuestionsEntity> queryById(@Param("0") String user_id, @Param("1") int fromIndex,@Param("2") int pageSize);





}
