package com.piegroup.zzbm.BS.App.TokenManager;
import com.piegroup.zzbm.Configs.TokenConfig;
import com.piegroup.zzbm.DTO.TokenDTO;
import com.piegroup.zzbm.Entity.TokenEntity;
import com.piegroup.zzbm.Utils.TimeUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 通过Redis存储和验证token的实现类
 * @see
 * @author ScienJus
 * @date 2015/7/31.
 */
@Component
public class RedisTokenManager implements TokenManager {


    private RedisTemplate<String,Object> objectRedisTemplate;

    @Autowired
    public void setRedis(RedisTemplate redisTemplate) {
        this.objectRedisTemplate = redisTemplate;
        //泛型设置成Long后必须更改对应的序列化方案
        objectRedisTemplate.setKeySerializer(RedisSerializer.string());
        objectRedisTemplate.setValueSerializer(RedisSerializer.json());
    }

    public TokenDTO createToken(String userId) {
        //使用uuid作为源 密钥secret
        //token = userId_secret (加密过程) 可以自己选择
        String secret = UUID.randomUUID().toString().replace("-", "");
        //生成token
        String token = userId+"_"+secret;
        TokenDTO tokenDTO = new TokenDTO(userId, token);
        //添加数据库
        System.out.println(secret);
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setCreate_Time(TimeUtil2.TimestampNow());
        tokenEntity.setToken(secret);

        deleteToken(userId);
        //存储到redis并设置过期时间
        objectRedisTemplate.boundValueOps(userId).set(tokenEntity, TokenConfig.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);


        return tokenDTO;
    }

    //传token 解析密钥
    public TokenDTO getToken(String token) {
        if (token == null || token.length() == 0) {
            return null;
        }
        String[] param = token.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        String userId = param[0];
        String secret  = param[1];
        System.out.println(userId+":"+secret);
        return new TokenDTO(userId, secret);
    }

    public boolean checkToken(TokenDTO tokenDTO) {
        if (tokenDTO == null) {
            return false;
        }
//        String token = redis.boundValueOps(model.getUserId()).get();
        TokenEntity tokenEntity = (TokenEntity) objectRedisTemplate.boundValueOps(tokenDTO.getUserId()).get();
        System.out.println("check:"+tokenEntity);
        if (tokenEntity == null || !tokenEntity.getToken().equals(tokenDTO.getToken())) {
            return false;
        }
        tokenEntity.setCreate_Time(TimeUtil2.TimestampNow());
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        deleteToken(tokenDTO.getUserId());
        objectRedisTemplate.boundValueOps(tokenDTO.getUserId()).set(tokenEntity,TokenConfig.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    public void deleteToken(String userId) {
        objectRedisTemplate.delete(userId);
    }



    @Override
    public TokenDTO updateToken(String userId) {


        TokenDTO tokenDTO = new TokenDTO();
        TokenEntity tokenEntity = (TokenEntity) objectRedisTemplate.boundValueOps(userId).get();
        //是否需要更新token
        if (TimeUtil2.check(tokenEntity.getCreate_Time(),tokenEntity.getUpdate_Time())){
            System.out.println("更新token");
            tokenDTO = createToken(userId);
            System.out.println(tokenDTO);
        }

        return tokenDTO;
    }

    @Override
    public boolean checkByid(String userId) {
       Set set =  objectRedisTemplate.keys(userId);
        System.out.println("数据库里面有多少："+set.size());
       if (set.size()==0) {
           return false;
       }else
           return true;
    }
}
