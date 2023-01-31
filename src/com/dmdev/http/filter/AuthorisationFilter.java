package com.dmdev.http.filter;

import com.dmdev.http.web.util.UrlPath;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

public class AuthorisationFilter {
//    private static final Set<String> PUBLIC_PATH = Set.of(UrlPath.LOGIN, UrlPath.REGISTRATION, UrlPath.IMAGES);
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
//        if(isPublicPath(uri) || isUserLoggedIn(servletRequest)){
//            filterChain.doFilter(servletRequest,servletResponse);
//        }
//        else {
//            String prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");//получили урл странички с которой пришел запрос
//            ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : UrlPath.LOGIN);
//        }
//    }
//
//    private boolean isUserLoggedIn(ServletRequest servletRequest) {
//        return ((HttpServletRequest)servletRequest).getSession().getAttribute("user") != null;
//    }
//
//    private boolean isPublicPath(String uri) {
//        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
//    }
}
