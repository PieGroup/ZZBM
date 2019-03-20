package com.piegroup.zzbm.VO;

import com.piegroup.zzbm.Entity.IssueQuestionsEntity;
import com.piegroup.zzbm.Entity.UserEntity;
import lombok.Data;

@Data
public class QuestionUserVo extends IssueQuestionsEntity {

    private static final long serialVersionUID = -4560630744071558967L;
    private UserEntity user;
}
