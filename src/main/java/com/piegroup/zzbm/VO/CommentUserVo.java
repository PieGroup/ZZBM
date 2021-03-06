package com.piegroup.zzbm.VO;

import com.piegroup.zzbm.Entity.CommentEntity;
import com.piegroup.zzbm.Entity.UserEntity;
import lombok.Data;

@Data
public class CommentUserVo extends CommentEntity {

    private static final long serialVersionUID = 8654804473461566788L;
    private UserEntity father_user;
    private UserEntity user;
    private int islike;
    private int dislike;
}
