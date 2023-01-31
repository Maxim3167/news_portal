<%--
  Created by IntelliJ IDEA.
  User: Maxim Kabanov
  Date: 03.01.2023
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

</head>
<body>
<ul>
   <c:if test="${not empty requestScope.news}">
       <div>
           ${requestScope.news.text}
       </div>
   </c:if>
</ul>

<button type="button"><a href="${pageContext.request.contextPath}/updateNews?newsId=${requestScope.news.id}">Edit news</a></button>
<form method="post" action="${pageContext.request.contextPath}/createComments?newsId=${requestScope.news.id}">
    <label for="idText">
        <input name="comment" type="text" id="idText">
    </label><br>
</form>
<button type="submit">Add comment</button>

</body>
</html>
