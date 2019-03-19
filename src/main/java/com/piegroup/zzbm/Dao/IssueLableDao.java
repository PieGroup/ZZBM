package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueLableEntity;
import com.piegroup.zzbm.Entity.IssueProgramEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IssueLableDao {
    @Select("select * from issue_lable order by issue_lable_id;")
    List<IssueLableEntity> alll();

    @Select("select * from issue_lable where (issue_lable_id%10000=0) order by issue_lable_sort desc;")
    List<IssueLableEntity> allparent();

    @Select("select * from issue_lable where issue_lable_id between #{pId1} and #{pId2} order by issue_lable_sort desc")
    List<IssueLableEntity> subl(@Param("pId1") int pId1, @Param("pId2") int pId2);

    //通过issueId 查询 lable
    @Select("select * from issue_lable where  issue_lable_id = (select issueLableid from issue_mtm_issuelable where issueid = #{0})")
    public List<IssueLableEntity> loadByIssueId(@Param("0") String questionId);
}
