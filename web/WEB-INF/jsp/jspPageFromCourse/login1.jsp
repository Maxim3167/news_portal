<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 31.12.2022
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

</head>
<body>
<form action="/login" method="post">
    <label for="email">Email:
        <input type="text" name="email" id="email" value="${param.email}" required>
    </label><br>

    <label for="password">Password:
        <input type="password" name="password" id="password" required>
    </label> <br>
    <button type="submit">Login</button>
    <a href="${pageContext.request.contextPath}/registration">
        <button type="button">Registration</button>
    </a>
    <c:if test="${param.error != null}">
        <div style="color: red">
            <span>Email or Password is not correct</span>
        </div>
    </c:if>

</form>
</body>
</html>
