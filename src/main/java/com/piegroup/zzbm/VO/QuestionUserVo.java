package com.piegroup.zzbm.VO;

import com.piegroup.zzbm.Entity.IssueQuestionsEntity;
import com.piegroup.zzbm.Entity.UserEntity;
import lombok.Data;

@Data
public class QuestionUserVo extends IssueQuestionsEntity {

    private UserEntity user;
}
