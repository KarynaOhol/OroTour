<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 02.05.2020
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Tour page </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tourPageStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>
<fmt:setLocale value="${language}"/>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<jsp:useBean id="TypeBean" scope="request"
             class="ua.nure.ohol.SummaryTask4.db.beans.TypeBean"/>
<jsp:useBean id="HotelClass" scope="request"
             class="ua.nure.ohol.SummaryTask4.db.beans.HotelClassBean"/>

<div class="tour_page">
    <fmt:bundle basename="staticinformation">
        <c:forEach items="${tour}" var="tourInf">

            <c:if test="${tourInf.key=='Tour'}">
                <c:set var="tourId" value="${tourInf.value.id}"/>
                <c:set var="tourNameRu" value="${tourInf.value.tourNameRu}"/>
                <c:set var="tourName" value="${tourInf.value.tourName}"/>
                <c:set var="hot" value="${tourInf.value.hotTour}"/>
                <c:set var="discountId" value="${tourInf.value.discountID}"/>
                <c:set var="deparRu" value="${tourInf.value.departureCityRu}"/>
                <c:set var="depar" value="${tourInf.value.departureCity}"/>
                <c:set var="avalTick" value="${tourInf.value.availableTickets}"/>
                <c:set var="price" value="${tourInf.value.price}"/>
            </c:if>

            <c:if test="${tourInf.key=='Duration'}">
                <c:set var="days" value="${tourInf.value.durationInDays}"/>
                <c:set var="end" value="${tourInf.value.tourEndDate}"/>
                <c:set var="start" value="${tourInf.value.tourBeginDate}"/>
            </c:if>
            <c:if test="${tourInf.key=='Description'}">
                <c:set var="type" value="${TypeBean.getType(tourInf.value.typeID)}"/>
                <c:set var="beachActivityRu" value="${tourInf.value.beachActivityRu}"/>
                <c:set var="sportActivityRu" value="${tourInf.value.sportActivityRu}"/>
                <c:set var="beachActivity" value="${tourInf.value.beachActivity}"/>
                <c:set var="sportActivity" value="${tourInf.value.sportActivity}"/>
                <c:set var="programTourRu" value="${tourInf.value.programTourRu}"/>
                <c:set var="programTour" value="${tourInf.value.programTour}"/>
                <c:set var="countryRu" value="${tourInf.value.countryRu}"/>
                <c:set var="country" value="${tourInf.value.country}"/>
                <c:set var="img" value="${tourInf.value.base64image}"/>
            </c:if>
            <c:if test="${tourInf.key=='Hotel'}">
                <c:set var="hotelSite" value="${tourInf.value.hotelSite}"/>
                <c:set var="hotelName" value="${tourInf.value.hotelName}"/>
                <c:set var="classHotel" value="${HotelClass.getHotelClass(tourInf.value.hotelClass)}"/>

            </c:if>
        </c:forEach>

        <div class="img_tour">
            <img alt="img" src="data:image/jpeg;base64,${img}" <%--width="100%" height="55%"--%>/>
            <c:if test="${language=='ru'}">
                <h1> ${tourNameRu}</h1>
            </c:if>
            <c:if test="${language=='en'}">
                <h1> ${tourName}</h1>
            </c:if>
        </div>


        <div class="tour_char">${type}
            <c:if test="${hot ==true}">
                <i class="fa fa-fw fa-fire" style="color: #f77f00"></i>
            </c:if>
            <c:if test="${discountId !=0 && hot ==false}">
                <i class="fa fa-fw fa-percent " style="color: darkblue"></i>
            </c:if>
        </div>
        <div class="tour_desc">
            <div class="tour_desc_column">
                <div><i class="fa fa-fw fa-clock-o"
                        style="color: #4D626A;font-size: 20px"></i>${days} <fmt:message
                        key="days"/></div>
                <div><i class="fa fa-fw fa-calendar"
                        style="color: #4D626A;font-size: 20px"></i>${start} -
                        ${end}
                </div>
            </div>

            <div class="tour_desc_column">
                <div><i class="fa fa-fw fa-flag " style="color: #4D626A;font-size: 20px"></i>
                    <c:if test="${language=='ru'}">
                        ${countryRu}
                    </c:if>
                    <c:if test="${language=='en'}">
                        ${country}
                    </c:if>
                </div>
                <div><i class="fa fa-fw fa-plane" style="color: #4D626A;font-size: 20px"></i>
                    <c:if test="${language=='en'}">
                        ${depar}
                    </c:if>
                    <c:if test="${language=='ru'}">
                        ${deparRu}
                    </c:if>
                </div>
            </div>

            <div class="tour_desc_column">
                <div><i class="fa fa-fw fa-user-plus" style="color: #4D626A; font-size: 20px"></i>
                        ${avalTick}
                    <fmt:message key="admin.field.avai_tickets"/></div>
                <div><i class="fa fa-fw fa-money" style="color: #637349;font-size: 16px"></i>
                    <c:if test="${discountId!=0}">
                        <s>${price}</s>
                        <b>${discount.discountPrice}</b>
                    </c:if>
                    <c:if test="${discountId ==0}">
                        <b>${price}</b>
                    </c:if>
                </div>
            </div>
            <div class="buy_bar">
                <form action="${pageContext.request.contextPath}/tourBooking" method="post">
                    <input type="hidden" name="tourId" value="${tourId}">
                    <fmt:bundle basename="constants">
                        <div class="field_num_guest">
                            <fmt:message key="num_people"/>
                            <input id="numOfPeople" type="number" value="" name="numberOfPeople" max="${avalTick}"
                                   min="1">
                            <h1></h1>
                            <div>
                                <p style="display: inline"><fmt:message key="total.price"/></p>
                                <p style="display: inline" id="total"></p>
                            </div>
                        </div>
                        <c:if test="${booked==null}">
                            <input class="button_search" type="submit" value="<fmt:message key="booking"/>">
                        </c:if>
                        <c:if test="${booked!=null}">
                            <input class="button_search" style="background-color: #637349" disabled type="submit"
                                   value="<fmt:message key="reser.succ"/>">
                        </c:if>
                    </fmt:bundle>
                </form>
            </div>
        </div>
        <div class="hotel_desc">

            <div class="hotel_desc_column">
                <i class="fa fa-fw fa-building-o" style="color: #4D626A; font-size: 20px"></i>
                    ${classHotel}
            </div>
            <div class="hotel_desc_column">
                    ${hotelName}
            </div>
            <div class="hotel_desc_column">
                <i class="fa fa-fw fa-globe" style="color: #4D626A;font-size: 20px"></i>
                    ${hotelSite}
            </div>
        </div>

        <div class="tour_desc_inf">
            <h1><fmt:message key="admin.field.program"/></h1>
            <div class="tour_desc_inf_block">
                <c:if test="${language=='en'}">
                    ${programTour}
                </c:if>
                <c:if test="${language=='ru'}">
                    ${programTourRu}
                </c:if>
            </div>
            <h1><fmt:message key="admin.field.sport"/></h1>
            <div class="tour_desc_inf_block">
                <c:if test="${language=='en'}">
                    ${sportActivity}
                </c:if>
                <c:if test="${language=='ru'}">
                    ${sportActivityRu}
                </c:if>
            </div>
            <h1><fmt:message key="admin.field.besch"/></h1>
            <div class="tour_desc_inf_block">
                <c:if test="${language=='en'}">
                    ${beachActivity}
                </c:if>
                <c:if test="${language=='ru'}">
                    ${beachActivityRu}
                </c:if>
            </div>
        </div>


    </fmt:bundle>
</div>

<script>
    document.getElementById("numOfPeople").addEventListener('change', (event) => {
        var price = parseFloat('${discountId == 0 ? price : discount.discountPrice}');
        document.getElementById("total").textContent = ': ' + price * event.target.value;
    }, false);
</script>

<jsp:include page="_footer.jsp"/>
</body>
</html>
