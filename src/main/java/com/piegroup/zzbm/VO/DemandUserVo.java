package com.piegroup.zzbm.VO;

import com.piegroup.zzbm.Entity.IssueDemandEntity;
import com.piegroup.zzbm.Entity.UserEntity;
import lombok.Data;

@Data
public class DemandUserVo extends IssueDemandEntity {
    private static final long serialVersionUID = 7510306854646723797L;
    private UserEntity user;
    int islike;
}
