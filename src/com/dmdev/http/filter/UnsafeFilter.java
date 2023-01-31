package com.dmdev.http.filter;

import com.dmdev.http.web.dto.UserDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UnsafeFilter  {

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        UserDto userDto = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
//        if(userDto != null){
//            filterChain.doFilter(servletRequest,servletResponse); // если пользователь залогинен, то все ок, продолжаем
//        }
//        else { // иначе перенаправляем нашего юзера на страницу регистрации или логина
//            ((HttpServletResponse) servletResponse).sendRedirect("/registration");
//        }
//    }
}
