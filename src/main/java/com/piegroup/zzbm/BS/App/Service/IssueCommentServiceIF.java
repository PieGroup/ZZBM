package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.Entity.CommentEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;

/**
*@ClassName     IssueCommentServiceIF
*@Description   TODO
*@Author        DDLD
*@Date          2019/3/19 14:45
*@ModifyDate    2019/3/19 14:45
*@Version       1.0
*/
public interface IssueCommentServiceIF {

    public DataPageSubc list(int pageSize, int pageNum, String item_id)throws  Exception;

    public DataPageSubc QuestionRelist(int pageSize, int pageNum, String item_id)throws  Exception;

    public DataPageSubc addOneComment(CommentEntity c);

    public DataPageSubc changeStatus(String cid,int status);

    public DataPageSubc like(String cid);

    public DataPageSubc dislike(String cid);

}
