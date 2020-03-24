<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 23.03.2020
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<h3>Hello: ${user.login}</h3>
User name: <b>${user.firstName}</b>  <b>${user.lastName}</b><br>
User Phone: <b>${user.phone}</b><br>
User Email: <b>${user.email}</b>

<jsp:include page="_footer.jsp"/>

</body>
</html>
