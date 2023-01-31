<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Список перелётов:</h1>
<ul>
    <%--
    ЗАПОМИНАЕМ:
    При помощи requestScope, sessionScope мы можем в jsp получить доступ к сущностям которые передали в эту jsp из сервлета
    req.setAttribute(название в jsp,значение)
    req.getSession().setAttribute(название в jsp,значение)
    В jsp есть Expression Language который позволяет нам отображать на веб страничке информацию при помощи java кода
    JSTL предназначена для того чтобы использовать инструменты java в jsp (ифы,циклы и тд)
    Подключается JSTL вот так <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    Предварительно добавив jar-ку в либу или подтянув в maven
    --%>
    <c:forEach var="flight" items="${requestScope.flights}">
        <li>
            <a href="${pageContext.request.contextPath}/tickets?flightId=${flight.id}">${flight.description}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
