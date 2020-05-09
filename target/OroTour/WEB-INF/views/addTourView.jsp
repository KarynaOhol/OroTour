<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 01.04.2020
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %><!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tourFormStyle.css">
    <title>Add new tour</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<body>
<c:set var="language" value="${cookie.language.value !=null ? cookie.language.value :'en'}" scope="session"/>

<fmt:setLocale value="${language}"/>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>


<div class="container">
    <form action="${pageContext.request.contextPath}/addTour" method="post" enctype="multipart/form-data">
        <fmt:bundle basename="staticinformation">
        <div class="row">
            <div class="col-25">

                <label for="tname"><fmt:message key="admin.field.tour_name"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="tname" name="tourname" placeholder="<fmt:message key="admin.field.tour_name"/>"
                       required>
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="tDepartCity"><fmt:message key="admin.field.departure_sity"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="tDepartCity" name="departCity"
                       placeholder="<fmt:message key="admin.field.departure_desc"/>"
                       required>
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="tprice"><fmt:message key="userInfo.field.price"/></label>
            </div>
            <div class="col-75">
                <input type="number" min="1" id="tprice" name="price" placeholder="<fmt:message key="price_desc"/>"
                       required>
            </div>
        </div>


        <div class="row">
            <div class="col-25">
                <label for="ttickets"><fmt:message key="admin.field.all_tickets"/></label>
            </div>
            <div class="col-75">
                <input type="number" min="1" id="ttickets" name="sartTickets"
                       placeholder="<fmt:message key="start_tick_desc"/>"
                       required>
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="dbegin"><fmt:message key="admin.field.sdate"/></label>
            </div>
            <div class="col-75">
                <input type="date" id="dbegin" name="startDate"
                       required>
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="dend"><fmt:message key="admin.field.edate"/></label>
            </div>
            <div class="col-75">
                <input type="date" id="dend" name="endDate"
                       required>
            </div>
        </div>

        <jsp:useBean id="HotelClass" scope="request"
                     class="ua.nure.ohol.SummaryTask4.db.beans.HotelClassBean"/>

        <div class="row">
            <div class="col-25">
                <label for="hname"><fmt:message key="admin.field.hotel"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="hname" name="hotelName" placeholder="<fmt:message key="hotel_name"/>"
                       required>
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="hotelClass"><fmt:message key="admin.field.hclass"/></label>
            </div>
            <div class="col-75">
                <select id="hotelClass" name="hotelClass">
                    <c:forEach items="${HotelClass.values}" var="state">
                        <option value="${state}">${state}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="hsite"><fmt:message key="admin.field.site"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="hsite" name="hotelSite" placeholder="<fmt:message key="hotel_site"/>"
                       required>
            </div>
        </div>

        <jsp:useBean id="TypeBean" scope="request"
                     class="ua.nure.ohol.SummaryTask4.db.beans.TypeBean"/>


        <div class="row">
            <div class="col-25">
                <label for="descriptionType"><fmt:message key="admin.field.type"/></label>
            </div>
            <div class="col-75">
                <select id="descriptionType" name="Type">

                    <c:forEach items="${TypeBean.values}" var="state">
                        <option value="${state}">${state}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="country"><fmt:message key="admin.field.country"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="country" name="country" placeholder="<fmt:message key="admin.field.country"/>"
                       required>
            </div>
        </div>


        <div class="row">
            <div class="col-25">
                <label for="program"><fmt:message key="admin.field.program"/></label>
            </div>
            <div class="col-75">
                    <textarea class="autoresizing" id="program" name="program"
                              placeholder="<fmt:message key="program"/>"
                              style="height:200px" required></textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="sport"><fmt:message key="admin.field.sport"/></label>
            </div>
            <div class="col-75">
                    <textarea class="autoresizing" id="sport" name="sport"
                              placeholder="<fmt:message key="admin.field.sport"/>"
                              style="height:100px" required></textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="beach"><fmt:message key="admin.field.besch"/></label>
            </div>
            <div class="col-75">
                    <textarea class="autoresizing" id="beach" name="beach"
                              placeholder="<fmt:message key="admin.field.besch"/>"
                              style="height:100px" required></textarea>
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="imgTour"><fmt:message key="admin.field.img"/></label>
            </div>
            <div class="col-75">
                <input id="imgTour" type="file" name="filePhoto" accept=".jpg, .jpeg, .png" required>
            </div>
        </div>


        <div class="row">
            <input type="submit" value="<fmt:message key="admin.btn.save"/>">
        </div>

        </fmt:bundle>
    </form>
</div>


<jsp:include page="_footer.jsp"/>


<script type="text/javascript">
    $('.autoresizing').on('input', function () {
        this.style.height = 'auto';

        this.style.height =
            (this.scrollHeight) + 'px';
    });
</script>
</body>
</html>