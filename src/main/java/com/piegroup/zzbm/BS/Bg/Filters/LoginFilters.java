package com.piegroup.zzbm.BS.Bg.Filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginFilters implements Filter {

    //不需要登录就可以访问的路径(比如:注册登录等)
    private static String[] includeUrls;
    private static List<String> NoFilterUrls = new ArrayList<String>();

    static {
        includeUrls = new String[]{"/login","register","/test"};
        NoFilterUrls.add("/extra-login-light.html");
        NoFilterUrls.add("register");
        NoFilterUrls.add("/test");
        NoFilterUrls.add("/api");
    }

    //标示符：表示当前用户未登录(可根据自己项目需要改为json样式)
    String NO_LOGIN = "您还未登录";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        String uri = request.getRequestURI();
        String proName = request.getContextPath();
        System.out.println("filter url:"+uri);
//        NoFilterUrls.add(proName);
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri);


        if (!needFilter) { //不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        } else { //需要过滤器
            // session中包含user对象,则是登录状态
            if(session!=null&&session.getAttribute("user") != null){
                // System.out.println("user:"+session.getAttribute("user"));
                filterChain.doFilter(request, response);
            }else{
                String requestType = request.getHeader("X-Requested-With");
                //判断是否是ajax请求
                if(requestType!=null && "XMLHttpRequest".equals(requestType)){
                    response.getWriter().write(this.NO_LOGIN);
                }else{
                    //重定向到登录页(需要在static文件夹下建立此html文件)
                    response.sendRedirect(request.getContextPath()+"/extra-login-light.html");
                }
                return;
            }
        }
    }

    public boolean isNeedFilter(String uri) {

        for (String nofileUrls : NoFilterUrls) {
            if(uri.contains(nofileUrls)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void destroy() {

    }
}
