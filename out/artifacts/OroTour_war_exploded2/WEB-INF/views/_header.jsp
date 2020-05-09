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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
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

    .navbar a, .loginBtn {
        float: left;
        padding: 12px;
        color: #bbbbbe;
        text-decoration: none;
        font-size: 17px;
    }

    .loginBtn {
        float: right;
    }

    #us {
        float: right;
        color: #819EA6;
    }

    #so {
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

    #translate {
        float: right;
        color: gray;
        text-shadow: 2px 2px 3px rgba(255, 255, 255, 0.1);

    }
</style>
<body>
<c:set var="language" value="${cookie.language.value !=null ? cookie.language.value :'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>

<div class="navbar">
    <c:choose>
        <c:when test="${loginedUser != null}">
            <a class="active" href="${pageContext.request.contextPath}/logout" id="so">
                <i class="fa fa-sign-out" aria-hidden="true"></i>
            </a>
            <a class="active" href="${pageContext.request.contextPath}/userInfo" id="us">
            <fmt:bundle basename="staticinformation" prefix="header.field.">
                <i class="fa fa-fw fa-user"></i><fmt:message key="hi"/> <b>${loginedUser.firstName}</b>
                <b>${loginedUser.lastName}</b></a>
            </fmt:bundle>
        </c:when>
        <c:otherwise>
            <span class="loginBtn active" onclick="openLoginForm()">
                 <i class="fa fa-fw fa-user"></i>
            </span>
            <%--            <a class="active" href="${pageContext.request.contextPath}/login" id="so">--%>
            <%--                <i class="fa fa-fw fa-user"></i>--%>
            <%--            </a>--%>
        </c:otherwise>
    </c:choose>
    <c:if test="${loginedUser.roleId == 0 or loginedUser.roleId == 1 }">
        <a class="active" href="${pageContext.request.contextPath}/managerInfo" id="so">
            <i class="fa fa-fw fa-cog"></i>
        </a>
    </c:if>
    <c:if test="${language=='ru'}">
        <a id="translate" href="${pageContext.request.contextPath}/translator">RU</a>
    </c:if>
    <c:if test="${language=='en'}">
        <a id="translate" href="${pageContext.request.contextPath}/translator">EN</a>
    </c:if>
    <a href="#"><i class="fa fa-fw fa-phone"></i>7428421137</a>
    <a href="#"><i class="fa fa-fw fa-envelope"></i> orotour@mail.com</a>

</div>
<jsp:include page="loginView.jsp"/>

<script>
    function openLoginForm() {
        document.getElementById('id01').style.display = 'block';
    }

    function closeLoginForm() {
        document.getElementById('id01').style.display = 'none';
    }
</script>
</body>
</html>

