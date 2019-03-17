package com.piegroup.zzbm.interceptor;

import com.piegroup.zzbm.Annotation.Authorization;
import com.piegroup.zzbm.Annotation.CurrentUser;
import com.piegroup.zzbm.BS.App.TokenManager.TokenManager;
import com.piegroup.zzbm.Configs.TokenConfig;
import com.piegroup.zzbm.DTO.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
*@ClassName     AuthorizationInterceptor
*@Description   TODO  自定义拦截器，判断此次请求是否有权限
*@Author        DDLD
*@Date          2019/3/16 21:59
*@ModifyDate    2019/3/16 21:59
*@Version       1.0
*/
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager manager;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.println("开始拦截器");
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }


        //保存更新Token
        TokenDTO tokenDTO = new TokenDTO();
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(Authorization.class) ==null){
            System.out.println("没有@Authorization注解结束拦截");
            return true;
        }

        //从header中得到token
        String token = request.getHeader(TokenConfig.TOKEN);
        //验证token,获得密钥
        TokenDTO tokenDTO1 = manager.getToken(token);
        if (tokenDTO1 != null ) {
            if (manager.checkByid(tokenDTO1.getUserId())) {
                    //更新token--判断依据在于 TokenEntity->update_Time
                    System.out.println("获取的tokenDTO"+tokenDTO1);
                    tokenDTO = manager.updateToken(tokenDTO1.getUserId());
                    //设置密钥，如果没有改变就不设置
                if (tokenDTO.getToken() !=null) {

                    if (!tokenDTO.getToken().equals(""))
                        response.setHeader(TokenConfig.TOKEN, tokenDTO.getToken());
                }
                if (manager.checkToken(tokenDTO1) || manager.checkToken(manager.getToken(tokenDTO.getToken()))) {
                    //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                    request.setAttribute(TokenConfig.CURRENT_USER_ID, tokenDTO1.getUserId());
                    return true;
                }

            }

        }
        System.out.println("结束拦截器");

        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authorization.class) != null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect("/tokens/noLogin");
            return false;
        }
        return true;
    }
}
