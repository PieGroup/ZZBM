package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueLikeEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface likeDao {

    @Select("select * from issue_like where like_user=#{1} and like_item=#{2};")
    IssueLikeEntity checkLike(@Param(value = "1")String userid,@Param(value = "2")String itemid);

    @Select("select * from issue_dislike where dislike_user=#{1} and dislike_item=#{2};")
    IssueLikeEntity checkDislike(@Param(value = "1")String userid,@Param(value = "2")String itemid);


    @Insert("insert into issue_dislike" +
            "(dislike_item,dislike_user) " +
            "values" +
            "(#{dislike_Item},#{dislike_User});")
    int dislike(IssueLikeEntity i);

    @Insert("insert into issue_like" +
            "(like_item,like_user) " +
            "values" +
            "(#{like_Item},#{like_User});")
    int like(IssueLikeEntity i);

    @Delete("delete from issue_like where like_user=#{2} and like_item=#{1};")
    int deleteLike(@Param(value = "1")String itemid,@Param(value = "2")String userid);

    @Delete("delete from issue_dislike where dislike_user=#{2} and dislike_item=#{1};")
    int deleteDislike(@Param(value = "1")String itemid,@Param(value = "2")String userid);
}
