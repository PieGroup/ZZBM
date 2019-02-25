package com.piegroup.zzbm.Utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookiesUtil {
    public static boolean saveCookies(String value, HttpServletResponse response, HttpServletRequest request) {
        try{
            Cookie[] cookies = request.getCookies();
            if (cookies == null){

                Cookie WXIDCookie = new Cookie("cookies_"+value, value);

                WXIDCookie.setMaxAge(315360000);//设置cookie生存时间：十年
                response.addCookie(WXIDCookie);// 添加cookie：
            }
            else {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("MID")) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
                Cookie WXIDCookie = new Cookie("cookies_"+value, value);
                WXIDCookie.setMaxAge(315360000);//设置cookie生存时间：十年
                response.addCookie(WXIDCookie);// 添加cookie：
            }

        }catch(Exception e){
            return false;

        }
        return true;
    }

    public static boolean clearCookies(String value, HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return true;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cookies_"+value)) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        return true;
    }
    public static String printCookies(String value, HttpServletRequest request){
        String ss = "";
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cookies_"+value)) {
                ss = cookie.getValue();
            }
        }
        return ss;
    }
}
