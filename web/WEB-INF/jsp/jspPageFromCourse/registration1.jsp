<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<img src="${pageContext.request.contextPath}/images/users/CodeWars.png" alt="User image">
<form action="/registration" method="post" enctype="multipart/form-data">
    <label for="name">Name:
        <input type="text" name="name" id="name">
    </label> <br>

    <label for="birthday">Birthday:
        <input type="date" name="birthday" id="birthday" required>
    </label> <br>

    <label for="imageId">Image:
        <input type="file" name="image" id="imageId" required>
    </label> <br>

    <label for="email">Email:
        <input type="text" name="email" id="email">
    </label> <br>

    <label for="passwordId">Password:
        <input type="password" name="password" id="passwordId">
    </label> <br>

    <select name="role" id="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role}"> ${role}</option>
        </c:forEach>
    </select><br>

    <c:forEach var="gender" items="${requestScope.genders}">
        <label>
            <input type="radio" name="gender" value="${gender}"> ${gender}
        </label>
        <br>
    </c:forEach>

    <c:if test="${not empty requestScope.errors}">
         <ul style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <li>
                   ${error.text}
                </li>
            </c:forEach>
         </ul>
    </c:if>

    <button type="submit">Send</button>
</form>
</body>
</html>
