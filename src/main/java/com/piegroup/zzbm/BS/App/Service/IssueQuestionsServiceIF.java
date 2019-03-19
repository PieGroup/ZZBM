package com.piegroup.zzbm.BS.App.Service;

import com.piegroup.zzbm.Entity.IssueQuestionsEntity;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;

public interface IssueQuestionsServiceIF {
    DataPageSubc list(int pageSize, int pageNum) throws Exception;
    DataPageSubc Insert(IssueQuestionsEntity i);
    DataPageSubc change(int status, String id);
    DataPageSubc caina(String reply_id,String question_id);
}
