<%--
  Created by IntelliJ IDEA.
  User: Maxim Kabanov
  Date: 06.01.2023
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Страница редактирования новости</title>
</head>
<body>
<form name="updateNews" method="post" action="${pageContext.request.contextPath}/updateNews?newsId=${requestScope.id}">
    New title:<label for="idNewName">
        <input type="text" name="newName" id="idNewName">
    </label> <br>

    New content:<label for="idNewContent">
        <input type="text" name="newContent" id="idNewContent">
    </label>

    <button type="submit">Update</button>

</form>
</body>
</html>
