<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 23.03.2020
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
User name: <b>${user.firstName}</b> <b>${user.lastName}</b><br>
User Phone: <b>${user.phone}</b><br>
User Email: <b>${user.email}</b>


<table class="table">
    <thead class="thead-light">
    <tr>
        <th scope="col">Tour</th>
        <th scope="col">Price</th>
        <th scope="col">Duration</th>
        <th scope="col">Status</th>
    </tr>
    </thead>
    <tbody>

    <tr>
        <c:forEach items="${reservationInfo}" var="i">
            <c:choose>
                <c:when test="${i.key == 'Tour'}">
                    <th>${i.value.tourName}</th>
                    <th>${i.value.price}</th>
                </c:when>
                <c:when test="${i.key == 'Duration'}">
                    <th>${i.value.durationInDays}</th>
                </c:when>
                <c:when test="${i.key == 'Reservation'}">
                    <th>${statusName}</th>
                </c:when>
            </c:choose>
        </c:forEach>
    </tr>

    </tbody>
</table>

<jsp:include page="_footer.jsp"/>

</body>
</html>
