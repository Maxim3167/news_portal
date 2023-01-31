<%--
  Created by IntelliJ IDEA.
  User: Maxim Kabanov
  Date: 03.01.2023
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Login page</title>
</head>
<body>
    <form method="post">
        Email: <label for="emailId">
            <input type="text" name="Email" id="emailId" required>
        </label><br>
        <br>
        Password: <label for="passwordId">
            <input name="Password" type="password" id="passwordId" required>
        </label><br>

        <button type="submit" style="color: cornflowerblue">Войти</button>

        <a href="${pageContext.request.contextPath}/myRegistration">
            <button type="button">Регистрация</button>
        </a>
    </form>

<a href="${pageContext.request.contextPath}/news"> Войти без авторизации</a>

<c:if test="${param.error != null}" >
    <span style="color: red">Your email or Password is not correct</span>
</c:if>
</body>
</html>
