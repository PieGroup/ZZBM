package com.piegroup.zzbm.VO;

import com.piegroup.zzbm.Entity.IssueConsultEntity;
import com.piegroup.zzbm.Entity.UserEntity;
import lombok.Data;

import java.io.Serializable;

/**
 *咨询表
 */

@Data
public class ConsultUserVo extends IssueConsultEntity {


    private static final long serialVersionUID = 813566416870720501L;
    private UserEntity user; //咨询人id
    private UserEntity buser; //咨询人id

}

