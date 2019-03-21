package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueNotifyEntity;
import org.apache.ibatis.annotations.*;

import javax.ws.rs.DELETE;
import java.util.List;

@Mapper
public interface notifyDao {

    @Insert("insert into issue_notify" +
            "(notifyId,userid,notifyFromId,notifyType,notifyTitle,notifyContent)" +
            " values" +
            "(#{notifyid},#{userid},#{notifyfromid},#{notifytype},#{notifytitle},#{notifycontent});")
    int insertOneNotify(IssueNotifyEntity i);

    @Select("select count(*) from issue_notify where userid=#{1} and notifyType = #{2} ;")
    int count(@Param(value = "1")String userid,@Param(value = "2")String type);

    @Select("select * from issue_notify where userid=#{1} and notifyType = #{2} order by notifyTime desc limit #{3},#{4};")
    List<IssueNotifyEntity> queryNotifyByUserId(@Param(value = "1")String uid,@Param(value = "2")String type,
                                                @Param(value = "3")int fromIndex,@Param(value = "4")int pageSize);

    @Delete("delete from issue_notify where  notifyId = #{1};")
    int read(@Param(value = "1")String nid);
}
