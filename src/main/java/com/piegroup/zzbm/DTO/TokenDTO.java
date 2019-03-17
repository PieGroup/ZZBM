package com.piegroup.zzbm.DTO;

import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.io.Serializable;

/**
 * @ClassName TokenDTO
 * @Description TODO 记录用户的 Token
 * @Author DDLD
 * @Date 2019/3/16 21:50
 * @ModifyDate 2019/3/16 21:50
 * @Version 1.0
 */
@Data
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = 8564906460600072710L;

    //用户id
    private String userId;
    //随机生成的uuid
    private String token;

    public TokenDTO(String userId,String token){
        this.userId = userId;
        this.token = token;
    }

    public TokenDTO(){}

}
