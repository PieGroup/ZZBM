package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.CommentEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Mapper
public interface commentDao {

    @Select("select count(*) from comment where comment_status = 1 and comment_item_id=#{1};")
    int count(@Param(value = "1")String id);

    @Select("select * from comment where comment_item_id=#{1} and comment_status = 1 order by comment_time desc limit #{2},#{3};")
    List<CommentEntity> allComment(@Param(value = "1")String itemid,@Param(value = "2")int fromIndex,@Param(value = "3")int size);

    @Select("select * from comment where comment_id=#{1};")
    CommentEntity selectCommentById(@Param(value = "1")String comment_id);

    @Select("select * from comment where comment_id=(" +
            "select issue_questions_replyid from issue_questions where issue_questions_id=#{1});")
    CommentEntity selectReply(@Param(value = "1")String itemid);


    @Insert("insert into " +
            "comment(comment_id,comment_item_id,comment_user_id,comment_father_id,comment_content,comment_type) " +
            "values(#{comment_Id},#{comment_Item_Id},#{comment_User_Id},#{comment_Father_Id},#{comment_Content},#{comment_Type});")
    int addComment(CommentEntity commentEntity);

    @Update("update comment set comment_status=#{2} where comment_id=#{1};")
    int changeCommentStatus(@Param(value = "1")String commentid,@Param(value = "2")int status);

    @Update("update comment set comment_dislike=comment_dislike+1 where comment_id=#{1};")
    int dislike(@Param(value = "1")String commentid);

    @Update("update comment set comment_like=comment_like + 1 where comment_id=#{1};")
    int like(@Param(value = "1")String commentid);


}
