<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 23.03.2020
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginStyle.css">
<fmt:setLocale value="${language}"/>

<div id="id01" class="modal">

    <form class="modal-content animate" id="sing_in_form" action="${pageContext.request.contextPath}/login"
          method="post">
        <div class="imgcontainer">
                <span onclick="document.getElementById('id01').style.display='none'"
                      class="close" title="Close Modal">&times;</span>
        </div>

        <div class="container">
            <fmt:bundle basename="staticinformation">
                <p style="color: red">${errorString}</p>
                <input type="hidden" name="redirectId" value="${param.redirectId}"/>
                <label for="userName"><b><fmt:message key="login.field.login"/></b></label>
                <input type="text" name="userName" id="userName" required>

                <label for="psw"><b><fmt:message key="login.field.password"/></b></label>
                <input type="password" name="password" id="psw" required>

                <button id="sign_in" type="submit"><fmt:message key="login.btn.login"/></button>
                <div class="sign_up" onclick="openRegisterForm()">
                    <span onclick="openRegisterForm()"><fmt:message key="login.btn.sign_up"/></span>
                </div>
                <label>
                    <input type="checkbox" value="Y" checked="checked"
                           name="remember"> <fmt:message key="login.btn.remember"/>
                </label>
            </fmt:bundle>
        </div>

    </form>
</div>

<jsp:include page="registerView.jsp"/>

<script>
    // Get the modal
    var modal = document.getElementById('id01');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
