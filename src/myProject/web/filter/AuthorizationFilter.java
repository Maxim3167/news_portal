package myProject.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static myProject.web.util.UrlPath.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(CREATE_COMMENT,LOGIN,REGISTRATION,CREATE_NEWS,NEWS,NEWS_TEXT);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if(isPublicPath(uri) || isUserLoggedIn(servletRequest)){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {
    // не пропускаем
            String previousPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse).sendRedirect(previousPage != null ? previousPage : LOGIN);
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getSession().getAttribute("user") != null;
    }

    private boolean isPublicPath(String uri) {
        return (uri.equals(LOGIN) || uri.equals(REGISTRATION));
    }
}
