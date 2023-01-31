package com.dmdev.http.web.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {
    private static final String PATH = "/WEB-INF/jsp/jspPageFromCourse/%s.jsp";

    public static String getPath(String name){
        return String.format(PATH,name);
    }
}
