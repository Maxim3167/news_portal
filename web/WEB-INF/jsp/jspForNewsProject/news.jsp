<%--
  Created by IntelliJ IDEA.
  User: Maxim Kabanov
  Date: 03.01.2023
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

</head>
<body>
<ul>
        <c:forEach var="news" items="${requestScope.newsDtoList}">
            <li>
               <a href="${pageContext.request.contextPath}/newsText?newsId=${news.id}">Новость:${news.name}</a>
            </li>
        </c:forEach>
</ul>
<button type="button">
    <a href="${pageContext.request.contextPath}/createNews">Создать новость</a></button><br>
</body>
</html>
