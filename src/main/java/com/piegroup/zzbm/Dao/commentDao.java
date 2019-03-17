package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface commentDao {

    @Select("select * from comment where comment_father_id=#{1} and status=1 order by comment_time desc;")
    List<CommentEntity> allComment(@RequestParam(value = "1")String fatherid);


}
