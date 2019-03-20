package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.Entity.IssueQuestionsEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;

public interface IssueQuestionsServiceIF {
    DataPageSubc list(int pageSize, int pageNum) throws Exception;
    DataPageSubc Insert(IssueQuestionsEntity i);
    DataPageSubc change(int status, String id);
    DataPageSubc caina(String reply_id,String question_id);


    //通过用户id 查找用户发布的问题
    DataPageSubc queryById(String user_id,int pageSize,int pageNum);

    //通过问题id 查找用户发布的问题
    DataPageSubc queryByQuestionId(String qid) throws Exception;

}
