package com.piegroup.zzbm.VO;

import com.piegroup.zzbm.Entity.IssueProgramEntity;
import com.piegroup.zzbm.Entity.UserEntity;
import lombok.Data;

@Data
public class ProgramUserVo extends IssueProgramEntity {
    private static final long serialVersionUID = -6513604601375254122L;
    private UserEntity user;
    int islike;
}
