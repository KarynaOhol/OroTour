<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 23.03.2020
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/accessDeniedStyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<br>
<br>
<br>
<br>
<br>
<br>
<img src="${pageContext.request.contextPath}/images/error.png" alt="Error" style="width:20%;">



<jsp:include page="_footer.jsp"/>

</body>
</html>
