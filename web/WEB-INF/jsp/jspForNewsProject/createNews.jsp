<%--
  Created by IntelliJ IDEA.
  User: Maxim Kabanov
  Date: 05.01.2023
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница создания новости</title>
</head>
<body>
<%@include file="header.jsp"%>
<form method="post" action="">

    Title:<label for="idName">
        <input type="text" name="name" id="idName">
    </label><br>

    Content:<label for="idText">
        <input type="text" name="text" id="idText">
    </label><br>

    <button type="submit">Create</button>
</form>
</body>
</html>
