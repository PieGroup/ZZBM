package com.piegroup.zzbm.resolvers;

import com.piegroup.zzbm.Annotation.CurrentUser;
import com.piegroup.zzbm.BS.App.Service.Impl.UserServiceImpl;
import com.piegroup.zzbm.Configs.TokenConfig;
import com.piegroup.zzbm.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 * @see
 * @author ScienJus
 * @date 2015/7/31.
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        System.out.println("开始通过注释拿用户信息");
        //如果参数类型是User并且有CurrentUser注解则支持
        if (parameter.getParameterType().isAssignableFrom(UserEntity.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //取出鉴权时存入的登录用户Id
        String currentUserId = (String) webRequest.getAttribute(TokenConfig.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
        if (currentUserId != null) {
            //从数据库中查询并返回
            return userService.queryByUserId(currentUserId);
        }
        throw new MissingServletRequestPartException(TokenConfig.CURRENT_USER_ID);
    }
}
