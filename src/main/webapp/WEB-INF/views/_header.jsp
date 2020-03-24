<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 23.03.2020
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    body {
        font-family: Arial, Helvetica, sans-serif;
    }

    .navbar {
        width: 100%;
        background-color: #f4f4f2;
        overflow: auto;
    }

    .navbar a {
        float: left;
        padding: 12px;
        color: #bbbbbe;
        text-decoration: none;
        font-size: 17px;
    }

    #us {
        float: right;
        color: #819EA6;
    }

    #so{
        float: right;
        color: rgba(129, 158, 166, 0.67);

    }

    .active {
        background-color: #f4f4f2;
    }

    @media screen and (max-width: 500px) {
        .navbar a {
            float: none;
            display: block;
        }
    }
</style>
<body>
<div class="navbar">
    <c:choose>
        <c:when test="${not empty loginedUser}">
            <a class="active" href="${pageContext.request.contextPath}/logout" id="so">
                <i class="fa fa-sign-out" aria-hidden="true"></i>
            </a>
            <a class="active" href="${pageContext.request.contextPath}/userInfo" id="us">
                <i class="fa fa-fw fa-user"></i> Hi, <b>${loginedUser.firstName}</b> <b>${loginedUser.lastName}</b></a>
        </c:when>
        <c:otherwise>
            <a class="active" href="${pageContext.request.contextPath}/login" id="so">
                <i class="fa fa-fw fa-user"></i>
            </a>
        </c:otherwise>
    </c:choose>
    <a href="#"><i class="fa fa-fw fa-phone"></i>7428421137</a>
    <a href="#"><i class="fa fa-fw fa-envelope"></i> orotour@mail.com</a>

</div>

</body>
</html>

