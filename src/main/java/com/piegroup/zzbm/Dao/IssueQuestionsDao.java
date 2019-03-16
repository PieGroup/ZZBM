package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueQuestionsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IssueQuestionsDao {
    @Select("select count(*) from issue_questions")
    int count();


    @Select("select * from issue_questions where issue_questions_issueStatusid = 1 limit #{index},#{pageSize}")
    List<IssueQuestionsEntity> list(@Param("index") int fromIndex, @Param("pageSize") int pageSize);

    @Insert("insert into issue_questions(" +
            "issue_questions_id,issue_questions_userid,issue_questions_title,issue_questions_generalize,issue_questions_accept,issue_questions_points,issue_questions_issueStatusid,issue_questions_replyid,issue_questions_paidLookReply,issue_questions_anonymous,issue_questions_annexid)" +
            " values(#{issue_questions_id},#{issue_questions_userid},#{issue_questions_title},#{issue_questions_generalize},#{issue_questions_accept},#{issue_questions_points},#{issue_questions_issueStatusid},#{issue_questions_replyid},#{issue_questions_paidLookReply},#{issue_questions_anonymous},#{issue_questions_annexid});")
    int addDemand(IssueQuestionsEntity I);

    @Update("update issue_questions set issue_questions_issueStatusid=#{1} where issue_questions_id=#{2};")
    int change(@Param(value = "1") int statusid, @Param(value = "2") String demandid);

}
