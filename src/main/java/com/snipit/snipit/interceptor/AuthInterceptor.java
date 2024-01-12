package com.snipit.snipit.interceptor;

import java.util.Base64;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(final HttpServletRequest req, final HttpServletResponse res, final Object handler) throws Exception{
        String authHeader = req.getHeader("Authorization");
        if((authHeader != null)&& (authHeader.startsWith("Basic"))){
            authHeader = authHeader.substring(6).trim();
            byte[] decodedHeader = Base64.getDecoder().decode(authHeader);
            String[] creds = new String(decodedHeader).split(":");
            req.setAttribute("username", creds[0]);
            req.setAttribute("password", creds[1]);
            System.out.println(req);
        }
        System.out.println("HELLO");
        return true;
    }
}
