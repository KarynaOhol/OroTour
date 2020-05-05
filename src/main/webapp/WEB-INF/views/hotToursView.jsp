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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/hotTourStyle.css">
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
                <h1 style="font-size:50px"><fmt:message key="hot_tours"/></h1>
            </fmt:bundle>
        </div>


        <div class="search_body_row">

            <script>
                var tourIdArray = [];
            </script>

            <div class="search_result_column">
                <fmt:bundle basename="staticinformation">
                    <c:set var="index" value="0"/>
                    <c:forEach var="i" step="1" begin="1" end="${size}">
                        <div class="tour_card_row">
                            <c:forEach var="j" step="1" begin="1" end="3">
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

                                    <c:if test="${j != 3}">
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

</body>
</html>
