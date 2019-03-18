package com.piegroup.zzbm.VO;

import com.piegroup.zzbm.Entity.CommentEntity;
import com.piegroup.zzbm.Entity.UserEntity;
import lombok.Data;

@Data
public class CommentUserVo extends CommentEntity {

    private UserEntity father_user;
    private UserEntity user;
}
