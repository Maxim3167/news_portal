
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title> <%-- так как эта jsp страничка лежит в директории web-inf
    она недоступна из браузера, поэтому обратиться к ней можем только из какого либо сервлета--%>
    <%-- директива include означает то же самое что и include в сервлетах
      в месте ее написания отработает указанная jsp и затем продолжит выполнятся основная jsp--%>
</head>
<body>
<%@include file="Footer.jsp"%>
<div>
    <p>Size: ${requestScope.flights.size()}</p>
    <p>Id:${requestScope.flights.get(0)}</p>
    <p> Id2: ${requestScope.flights[1]}</p>
    <p> Map : ${sessionScope.flightsMap[1]}</p>
    <p> JSESSIONID: ${cookie["JSESSIONID"].value}</p>
    <p> HEADER: ${header["Cookie"]}</p>
    <p> Param id: ${param.id}</p>
    <p> Param test : ${param.test}</p>
</div>
<%@include file="header.jsp"%>

</body>
</html>
