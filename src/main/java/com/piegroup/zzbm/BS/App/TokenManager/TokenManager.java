package com.piegroup.zzbm.BS.App.TokenManager;

import com.piegroup.zzbm.DTO.TokenDTO;

import javax.print.DocFlavor;

/**
 * 对Token进行操作的接口
 * @author ScienJus
 * @date 2015/7/31.
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     * @param userId 指定用户的id
     * @return 生成的token
     */
    public TokenDTO createToken(String userId);

    /**
     * 检查token是否有效
     * @param tokenDTO
     * @return 是否有效
     */
    public boolean checkToken(TokenDTO tokenDTO);

    /**
     * 从字符串中解析 密钥
     * @param token 加密后的字符串
     * @return
     */
    public TokenDTO getToken(String token);

    /**
     * 清除token
     * @param userId 登录用户的id
     */
    public void deleteToken(String userId);

    /***
     * token
     * 更新token
     */
    public TokenDTO updateToken(String userId);

    /**
     * 通过用户id 查找token
     */
    public boolean checkByid(String userId);

}
