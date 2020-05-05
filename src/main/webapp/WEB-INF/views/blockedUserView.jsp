<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 18.04.2020
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/accessDeniedStyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
</head>
<body>
<fmt:setLocale value="${language}"/>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<br>
<br>
<br>
<br>
<br>
<br>
<img src="${pageContext.request.contextPath}/images/error.png" alt="Error" style="width:20%;">
<div style="text-align: center">
<fmt:bundle basename="staticinformation">
    <h1><fmt:message key="blocked"/></h1>
    <h3><fmt:message key="blocked_desc"/></h3>
</fmt:bundle>
</div>



<jsp:include page="_footer.jsp"/>

</body>
</html>
