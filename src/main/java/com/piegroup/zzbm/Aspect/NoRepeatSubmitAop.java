package com.piegroup.zzbm.Aspect;

import com.piegroup.zzbm.Annotation.NoRepeatSubmit;
import com.piegroup.zzbm.Enums.ExceptionEnum;
import com.piegroup.zzbm.Utils.JsonUtil;
import com.piegroup.zzbm.Utils.ResultUtil;
import com.piegroup.zzbm.VO.SubC.DataPageSubc;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName NoRepeatSubmitAop
 * @Description TODO 重复请求切片处理
 * @Author DDLD
 * @Date 2019/3/20 16:06
 * @ModifyDate 2019/3/20 16:06
 * @Version 1.0
 */

@Aspect
@Component
@Slf4j
public class NoRepeatSubmitAop {

    private  RedisTemplate<String,Integer> SubRedis;

    @Autowired
    public NoRepeatSubmitAop(RedisTemplate redisTemplate) {
        this.SubRedis = redisTemplate;
        SubRedis.setKeySerializer(RedisSerializer.string());
        SubRedis.setValueSerializer(RedisSerializer.json());
    }

    @Pointcut(value = " execution(public * com.piegroup.zzbm.BS.App.Controller.*.*(..))")
    public void pc() {
    }

    @Around("pc() && @annotation(nrs)")
    public Object arround(ProceedingJoinPoint pjp, NoRepeatSubmit nrs) {
        System.out.println("进入路径重复检测。");
        ValueOperations<String, Integer> opsForValue = SubRedis.opsForValue();
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
            HttpServletRequest request = attributes.getRequest();
            String key = sessionId + "-" + request.getServletPath();
            if (opsForValue.get(key) == null) {// 如果缓存中有这个url视为重复提交
                Object o = pjp.proceed();
                opsForValue.set(key, 0, 2, TimeUnit.SECONDS);
                return o;
            } else {
                log.info("重复提交");
                return JsonUtil.toJson(ResultUtil.success(new DataPageSubc<>(), ExceptionEnum.Request_Frequently_Exception));
            }
        } catch (Throwable e) {
            e.printStackTrace();
            log.error("验证重复提交时出现未知异常!");
            return JsonUtil.toJson(ResultUtil.error(new DataPageSubc<>(),ExceptionEnum.Request_Frequently_Error_Exception).toString());
        }

    }



}
