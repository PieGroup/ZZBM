package com.piegroup.zzbm.Dao;

import com.piegroup.zzbm.Entity.IssueNotifyEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface notifyDao {

    @Insert("insert into issue_notify" +
            "(notifyId,notifyFromId,notifyType,notifyTitle,notifyContent)" +
            " values" +
            "(#{notifyid},#{notifyfromid},#{notifytype},#{notifytitle},#{notifycontent});")
    int insertOneNotify(IssueNotifyEntity i);


}
