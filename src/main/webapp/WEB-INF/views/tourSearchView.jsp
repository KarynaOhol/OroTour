<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 20.04.2020
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tourSearchStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<fmt:setLocale value="${language}"/>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<div class="search_page">
    <fmt:bundle basename="constants">
        <div class="img_head_row">
            <fmt:bundle basename="staticinformation">
                <h1 style="font-size:50px"><fmt:message key="menu.href.tour_search"/></h1>
            </fmt:bundle>
        </div>


        <div class="search_body_row">

            <div class="search_bar_column">
                <div class="search_bar">
                    <form action="${pageContext.request.contextPath}/tourSearch" method="post">
                        <div class="radio_type">
                            <h3><fmt:message key="tour.type.type"/></h3>
                            <label class="radio"><fmt:message key="tour.type.city"/>
                                <input type="radio" name="tourType"  value="1">
                                <span class="checkmark"></span>
                            </label>
                            <label class="radio"><fmt:message key="tour.type.culture"/>
                                <input type="radio" name="tourType" value="2">
                                <span class="checkmark"></span>
                            </label>
                            <label class="radio"><fmt:message key="tour.type.holiday"/>
                                <input type="radio" name="tourType" value="3">
                                <span class="checkmark"></span>
                            </label>
                            <label class="radio"><fmt:message key="tour.type.luxury"/>
                                <input type="radio" name="tourType" value="4">
                                <span class="checkmark"></span>
                            </label>
                            <label class="radio"><fmt:message key="tour.type.outdoor"/>
                                <input type="radio" name="tourType" value="5">
                                <span class="checkmark"></span>
                            </label>
                            <label class="radio"><fmt:message key="tour.type.relax"/>
                                <input type="radio" name="tourType" value="6">
                                <span class="checkmark"></span>
                            </label>
                            <label class="radio"><fmt:message key="tour.type.wild"/>
                                <input type="radio" name="tourType" value="7">
                                <span class="checkmark"></span>
                            </label>
                        </div>
                        <div class="field">
                            <fmt:bundle basename="staticinformation">
                                <h3><fmt:message key="userInfo.field.price"/></h3>
                            </fmt:bundle>
                            <div class="num_field min_field"><input type="number" placeholder="min"
                                                                    name="minPrice">
                            </div>
                            <div class="num_field max_field"><input type="number" placeholder="max"
                                                                    name="maxPrice">
                            </div>
                        </div>
                        <div class="field_num_guest">
                            <fmt:message key="num_people"/>
                            <input type="number" name="numberOfPeople">
                        </div>
                        <div class="radio_type">
                            <fmt:bundle basename="staticinformation">
                                <h3><fmt:message key="admin.field.hclass"/></h3>
                            </fmt:bundle>
                            <label class="radio"><fmt:message key="hotel.class.tour"/>
                                <input type="radio" name="hotelType" value="1">
                                <span class="checkmark"></span>
                            </label>
                            <label class="radio"><fmt:message key="hotel.class.stand"/>
                                <input type="radio" name="hotelType" value="2">
                                <span class="checkmark"></span>
                            </label>
                            <label class="radio"><fmt:message key="hotel.class.comf"/>
                                <input type="radio" name="hotelType" value="3">
                                <span class="checkmark"></span>
                            </label>
                            <label class="radio"><fmt:message key="hotel.class.first"/>
                                <input type="radio" name="hotelType" value="4">
                                <span class="checkmark"></span>
                            </label>
                            <label class="radio"><fmt:message key="hotel.class.lux"/>
                                <input type="radio" name="hotelType" value="5">
                                <span class="checkmark"></span>
                            </label>

                        </div>
                        <input class="button_search" type="submit" value="<fmt:message key="search"/>">

                    </form>
                </div>
            </div>
            <script>
                var tourIdArray = [];
            </script>
            <div class="search_result_column">
                <fmt:bundle basename="staticinformation">
                    <c:set var="index" value="0"/>
                    <c:forEach var="i" step="1" begin="1" end="${size}">
                        <div class="tour_card_row">
                            <c:forEach var="j" step="1" begin="1" end="2">
                                <c:if test="${index < tour.size()}">
                                    <div class="tour_card_column">
                                        <div class="tour_card">
                                            <div class="tour_img">
                                                <img alt="img"
                                                     src="data:image/jpeg;base64,${tour.get(index).value.value.base64image}"
                                                     width="100%" height="100%"/></div>
                                            <div class="tour_inf">
                                                <script>
                                                    tourIdArray.push(${tour.get(index).key.key.id});
                                                </script>
                                                <c:if test="${tour.get(index).key.key.hotTour==true}">
                                                    <a style="color: #f77f00; font-size: 25px">
                                                        <i class="fa fa-fw fa-fire"></i>
                                                    </a>
                                                </c:if>
                                                <c:if test="${tour.get(index).key.key.discountID != 0 && tour.get(index).key.key.hotTour==false}">
                                                    <a style="color: #439795; font-size: 25px">
                                                        <i class="fa fa-fw fa-percent"></i>
                                                    </a>
                                                </c:if>
                                                <c:if test="${language == 'ru'}">
                                                    ${tour.get(index).key.key.tourNameRu}
                                                </c:if>
                                                <c:if test="${language == 'en'}">
                                                    ${tour.get(index).key.key.tourName}
                                                </c:if>

                                                <p></p>

                                                <div>
                                                    <a style="color: #4D626A;float: left;"><i
                                                            class="fa fa-fw fa-calendar"></i>
                                                            ${tour.get(index).value.key.durationInDays}
                                                        <fmt:message key="days"/>
                                                    </a>
                                                    <c:if test="${tour.get(index).key.key.discountID != 0 }">
                                                        <a style="color: #637349; float:right">
                                                            <b>${tour.get(index).key.value.discountPrice}</b>
                                                            <s>${tour.get(index).key.key.price}</s>
                                                            <i class="fa fa-fw fa-money"></i>

                                                        </a>
                                                    </c:if>
                                                    <c:if test="${tour.get(index).key.key.discountID == 0}">

                                                        <a style="color: #637349; float:right">
                                                            <b>${tour.get(index).key.key.price}</b>
                                                            <i class="fa fa-fw fa-money"></i>
                                                        </a>
                                                    </c:if>

                                                </div>
                                            </div>

                                        </div>
                                    </div>

                                    <c:if test="${j == 1}">
                                        <div class="margin_between_cards"></div>
                                    </c:if>
                                </c:if>

                                <c:set var="index" value="${index+1}"/>

                            </c:forEach>
                        </div>
                    </c:forEach>

                </fmt:bundle>
            </div>


        </div>

    </fmt:bundle>
</div>

<jsp:include page="_footer.jsp"/>

</body>

<script>
    var $divTourCard = document.getElementsByClassName("tour_card_column");

    for (let k = 0; k < tourIdArray.length; k++) {
        $divTourCard[k].addEventListener('click', callServlet, false);
        $divTourCard[k].tourId = tourIdArray[k];
    }

    function callServlet(tourId) {
        document.location.href = '${pageContext.request.contextPath}/tourPage?tour_id=' + tourId.currentTarget.tourId;
    }

</script>

</html>
