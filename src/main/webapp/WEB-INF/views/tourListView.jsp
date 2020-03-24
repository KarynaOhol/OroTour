<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 23.03.2020
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>QWe</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>


<c:forEach items="${tourMap}" var="map" >
    <c:choose>
        <c:when test="${map.key == 'Tour'}">
            <p>Tour name: ${map.value.tourName}<br></p>
            <p>Price: ${map.value.price}<br></p>
            <p>Available tickets: ${map.value.availableTickets}<br></p>
        </c:when>
        <c:when test="${map.key == 'Duration'}">
            <p>tourBeginDate: ${map.value.tourBeginDate}<br></p>
            <p>TourEndDate: ${map.value.tourEndDate}<br></p>
        </c:when>
    </c:choose>
</c:forEach>

<jsp:include page="_footer.jsp"/>

</body>
</html>
