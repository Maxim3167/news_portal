<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 20.12.2022
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%--<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html> --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
    <c:if test="${not empty sessionScope.user}">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </c:if>
</div>

