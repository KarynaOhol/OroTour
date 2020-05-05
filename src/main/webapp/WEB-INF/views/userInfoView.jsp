<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userInfoStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/accountInformationView.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<jsp:useBean id="StatusBean" scope="request"
             class="ua.nure.ohol.SummaryTask4.db.beans.StatusBean"/>
<body>
<fmt:setLocale value="${language}"/>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<div class="wrapper">
    <fmt:bundle basename="staticinformation">
        <div class="left">

            <h4>${user.firstName} ${user.lastName}</h4>
            <p>${user.login}</p>
        </div>
        <div class="right">
            <div class="info">
                <h3><fmt:message key="information"/></h3>
                <div class="info_data">
                    <div class="data">
                        <h4><fmt:message key="login.field.email"/></h4>
                        <p>${user.email}</p>
                    </div>
                    <div class="data">
                        <h4><fmt:message key="login.field.phone"/></h4>
                        <p>${user.phone}</p>
                    </div>
                </div>
            </div>

            <c:if test="${user.roleId == 2 and not empty reservationInfo}">
                <div class="projects">
                    <h3><fmt:message key="userInfo.field.reservation"/></h3>
                </div>
                <table>
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="userInfo.field.tour"/></th>
                        <th scope="col"><fmt:message key="userInfo.field.duration"/></th>
                        <th scope="col"><fmt:message key="userInfo.field.price"/></th>
                        <fmt:bundle basename="constants">
                            <th scope="col"><fmt:message key="num_people"/></th>
                        </fmt:bundle>
                        <th scope="col"><fmt:message key="userInfo.field.status"/></th>
                        <th></th>
                        <th></th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach step="1" end="${reservationInfo.size()/3}" begin="1" var="i">
                        <tr>
                            <c:forEach items="${reservationInfo}" var="entity">
                                <c:choose>
                                    <c:when test="${entity.key == 'Tour'.concat(i)}">
                                        <c:set value="${entity.value.id}" var="tourId"/>
                                        <c:if test="${language=='en'}">
                                            <td>${entity.value.tourName}</td>
                                        </c:if>
                                        <c:if test="${language=='ru'}">
                                            <td>${entity.value.tourNameRu}</td>
                                        </c:if>
                                        <c:set var="price" value=" ${entity.value.price}"/>
                                    </c:when>
                                    <c:when test="${entity.key == 'Duration'.concat(i)}">
                                        <td>${entity.value.durationInDays}</td>
                                    </c:when>
                                    <c:when test="${entity.key == 'Reservation'.concat(i)}">
                                        <c:set value="${entity.value.statusId}" var="statusId"/>
                                        <td> ${price * entity.value.numberOfreserv} </td>
                                        <td>${entity.value.numberOfreserv}</td>
                                        <td>${StatusBean.getStatus(entity.value.statusId)}</td>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                            <td>
                                <c:if test="${statusId==0}">
                                <span class="btn success" onclick="openPayForm(${tourId}, ${user.id})"><fmt:message
                                        key="pay"/></span>
                                </c:if>
                                <c:if test="${statusId!=0}">
                                </c:if>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/deleteReservation" method="post"
                                      id="chTourDelete">
                                    <input class="btn success" type="submit"
                                           value="<fmt:message key="admin.btn.delete"/>" id="deleteTour">
                                    <input type="hidden" name="tourId" value="${tourId}">
                                    <input type="hidden" name="userId" value="${user.id}">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>


                    </tbody>
                </table>
            </c:if>
        </div>
    </fmt:bundle>
</div>

<jsp:include page="_tourPaymentView.jsp"/>
<jsp:include page="_footer.jsp"/>

<script>
    function openPayForm(tourId, userId) {
        document.getElementById('id02').style.display = 'block';
        document.getElementById('tourId').value = tourId;
        document.getElementById('userId').value = userId;
    }

    function closePayForm() {
        document.getElementById('id02').style.display = 'none';
    }
</script>
</body>
</html>
