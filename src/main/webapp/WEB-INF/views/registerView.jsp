<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 20.04.2020
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginStyle.css">
<c:set var="language" value="${cookie.language.value !=null ? cookie.language.value :'en'}" scope="session"/>

<fmt:setLocale value="${language}"/>

<div id="register" class="modal">

    <form class="modal-content animate" id="sing_up_form" action="${pageContext.request.contextPath}/register"
          method="post">
        <div class="imgcontainer">
                <span onclick="closeRegisterForm()"
                      class="close" title="Close Modal">&times;</span>
        </div>

        <div class="container">
            <fmt:bundle basename="staticinformation">
                <p style="color: red">${errorMassage
                        }</p>

                <%--            <input type="hidden" name="redirectId" value="${param.redirectId}"/>--%>
                <label for="login"><b><fmt:message key="login.field.login"/></b></label>
                <input type="text"  name="login" id="login" value="${param.login}"
                       required
                       autocomplete="off">
                <label for="pswReg"><b><fmt:message key="login.field.password"/></b></label>
                <input type="password" name="password" id="pswReg"
                       value="${param.password}"
                       required autocomplete="off">

                <label for="firstName"><b><fmt:message key="login.field.first_name"/></b></label>
                <input type="text"  name="firstName" id="firstName"
                       value="${param.firstName}"
                       required autocomplete="off">
                <label for="lastName"><b><fmt:message key="login.field.last_name"/></b></label>
                <input type="text"  name="lastName" id="lastName" value="${param.lastName}"
                       required autocomplete="off">

                <label for="phone"><b><fmt:message key="login.field.phone"/></b></label>
                <input type="text"name="phone" id="phone" value="${param.phone}"
                       required autocomplete="off">
                <label for="email"><b><fmt:message key="login.field.email"/></b></label>
                <input type="text"  name="email" id="email" value="${param.email}"
                       required autocomplete="off">
                <c:if test="${errorId != null}">
                    <script>
                        document.getElementById('${errorId}').style.borderColor = 'red';
                    </script>
                </c:if>

                <button type="submit"><fmt:message key="login.btn.sign_up"/></button>
            </fmt:bundle>
        </div>

        <%--        <div class="container" style="background-color:#f1f1f1">--%>
        <%--            <button type="button" onclick="closeRegisterForm()" class="cancelbtn">--%>
        <%--                Cancel--%>
        <%--            </button>--%>
        <%--            <span class="psw">Forgot <a href="#">password?</a></span>--%>
        <%--        </div>--%>
    </form>
</div>

<script>
    // Get the modal
    function openRegisterForm() {
        closeLoginForm();
        document.getElementById('register').style.display = 'block';
    }

    function closeRegisterForm() {
        document.getElementById('register').style.display = 'none';
    }
</script>

