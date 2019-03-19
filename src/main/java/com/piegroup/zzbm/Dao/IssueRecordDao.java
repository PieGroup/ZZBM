package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName IssueRecord
 * @Description TODO
 * @Author DDLD
 * @Date 2019/3/19 9:03
 * @ModifyDate 2019/3/19 9:03
 * @Version 1.0
 */

@Mapper
public interface IssueRecordDao {
    //获取记录id
    @Select("select * from issue_record where question_record_id = #{0}")
    public IssueRecordEntity loadById(@Param("0") String recordId);
}
