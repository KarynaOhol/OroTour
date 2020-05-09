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
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tourFormStyle.css">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<body>
<c:set var="language" value="${cookie.language.value !=null ? cookie.language.value :'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>


<div class="container">
    <form action="${pageContext.request.contextPath}/updateTour" method="post" enctype="multipart/form-data">
        <fmt:bundle basename="staticinformation">
            <c:forEach var="tour" items="${tourInfo}">


                <c:choose>
                    <c:when test="${tour.key =='Tour'}">
                        <input type="hidden" value="${tour.value.id}" name="tour_id">
                        <input type="hidden" value="${tour.value.hotelId}" name="hotel_id">
                        <input type="hidden" value="${tour.value.descriptionId}" name="description_id">
                        <input type="hidden" value="${tour.value.durationID}" name="duration_id">
                        <div class="row">
                            <div class="col-25">
                                <label for="tname"><fmt:message key="admin.field.tour_name"/></label>
                            </div>
                            <div class="col-75">
                                <input type="text" id="tname" name="tourname" placeholder="Tour Name"
                                <c:if test="${language=='ru'}">
                                       value="${tour.value.tourNameRu}"
                                </c:if>
                                <c:if test="${language=='en'}">
                                       value="${tour.value.tourName}"
                                </c:if>
                                >
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-25">
                                <label for="tDepartCity"><fmt:message key="admin.field.departure_sity"/></label>
                            </div>
                            <div class="col-75">
                                <input type="text" id="tDepartCity" name="departCity"
                                       placeholder="Сity, where tour starts"
                                <c:if test="${language=='ru'}">
                                       value="${tour.value.departureCityRu}"
                                </c:if>
                                <c:if test="${language=='en'}">
                                       value="${tour.value.departureCity}"
                                </c:if>>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-25">
                                <label for="tprice"><fmt:message key="userInfo.field.price"/></label>
                            </div>
                            <div class="col-75">
                                <input type="number" min="1" id="tprice" name="price" placeholder="Tour price "
                                       value="${tour.value.price}">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-25">
                                <label for="tavalT"><fmt:message key="admin.field.avai_tickets"/></label>
                            </div>
                            <div class="col-75">
                                <input type="number" min="1" id="tavalT" name="avalT"
                                       placeholder="Tickets available to sale"
                                       value="${tour.value.availableTickets}">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-25">
                                <label for="ttickets"><fmt:message key="admin.field.all_tickets"/></label>
                            </div>
                            <div class="col-75">
                                <input type="number" min="1" id="ttickets" name="sartTickets"
                                       placeholder="Start amount of tickets"
                                       value="${tour.value.amountTickets}">
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${tour.key =='Duration'}">

                        <div class="row">
                            <div class="col-25">
                                <label for="dbegin"><fmt:message key="admin.field.sdate"/></label>
                            </div>
                            <div class="col-75">
                                <input type="text" id="dbegin" name="startDate"
                                       value="${tour.value.tourBeginDate}">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-25">
                                <label for="dend"><fmt:message key="admin.field.edate"/></label>
                            </div>
                            <div class="col-75">
                                <input type="text" id="dend" name="endDate"
                                       value="${tour.value.tourEndDate}">
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${tour.key =='Hotel'}">

                        <jsp:useBean id="HotelClass" scope="request"
                                     class="ua.nure.ohol.SummaryTask4.db.beans.HotelClassBean"/>

                        <div class="row">
                            <div class="col-25">
                                <label for="hname"><fmt:message key="admin.field.hotel"/></label>
                            </div>
                            <div class="col-75">
                                <input type="text" id="hname" name="hotelName" placeholder="Hotel name"
                                       value="${tour.value.hotelName}">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-25">
                                <label for="hotelClass"><fmt:message key="admin.field.hclass"/></label>

                            </div>
                            <div class="col-75">
                                <select id="hotelClass" name="hotelClass">
                                    <option value="${HotelClass.getHotelClass(tour.value.hotelClass)}">${HotelClass.getHotelClass(tour.value.hotelClass)}</option>
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
                                <input type="text" id="hsite" name="hotelSite" placeholder="Hotel web-site"
                                       value="${tour.value.hotelSite}">
                            </div>
                        </div>
                    </c:when>

                    <c:when test="${tour.key =='Description'}">
                        <jsp:useBean id="TypeBean" scope="request"
                                     class="ua.nure.ohol.SummaryTask4.db.beans.TypeBean"/>


                        <div class="row">
                            <div class="col-25">
                                <label for="descriptionType"><fmt:message key="admin.field.type"/></label>
                            </div>
                            <div class="col-75">
                                <select id="descriptionType" name="Type">
                                    <option value="${TypeBean.getType(tour.value.typeID)}">
                                            ${TypeBean.getType(tour.value.typeID)}</option>
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
                                <input type="text" id="country" name="country" placeholder="Country tour"
                                <c:if test="${language=='ru'}">
                                       value="${tour.value.countryRu}"
                                </c:if>
                                <c:if test="${language=='en'}">
                                       value="${tour.value.country}"
                                </c:if>>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-25">
                                <label for="program"><fmt:message key="admin.field.program"/></label>
                            </div>
                            <div class="col-75">
                    <textarea class="autoresizing" id="program" name="program"
                              placeholder=" Brief program tour information and destination places"
                              style="height:200px">
                        <c:if test="${language=='ru'}">
                            ${tour.value.programTourRu}
                        </c:if>
                            <c:if test="${language=='en'}">
                                ${tour.value.programTour}
                            </c:if>
                    </textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-25">
                                <label for="sport"><fmt:message key="admin.field.sport"/></label>
                            </div>
                            <div class="col-75">
                    <textarea class="autoresizing" id="sport" name="sport" placeholder=" Sport activities"
                              style="height:100px">
                        <c:if test="${language=='ru'}">
                            ${tour.value.sportActivityRu}
                        </c:if>
                            <c:if test="${language=='en'}">
                                ${tour.value.sportActivity}
                            </c:if>
                    </textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-25">
                                <label for="beach"><fmt:message key="admin.field.besch"/></label>
                            </div>
                            <div class="col-75">
                    <textarea class="autoresizing" id="beach" name="beach" placeholder="  Beach activities"
                              style="height:100px">
                        <c:if test="${language=='ru'}">
                            ${tour.value.beachActivityRu}
                        </c:if>
                            <c:if test="${language=='en'}">
                                ${tour.value.beachActivity}
                            </c:if>
                    </textarea>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-25">
                                <label for="imgTour"><fmt:message key="admin.field.img"/></label>
                            </div>
                            <div class="col-75">
                                <img id="imgTour" alt="img" src="data:image/jpeg;base64,${tour.value.base64image}"
                                     width="400" height="300"/>
                                <input type="file" name="filePhoto" accept=".jpg, .jpeg, .png">
                            </div>
                        </div>
                    </c:when>
                </c:choose>
            </c:forEach>

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