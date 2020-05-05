<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 23.03.2020
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/homeStyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<fmt:setLocale value="${language}"/>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>


<c:if test="${errorString != null}">
    <script>
        openLoginForm();
    </script>
</c:if>

<c:if test="${errorMassage != null}">
    <script>
        openRegisterForm();
    </script>
</c:if>

<jsp:useBean id="TypeBean" scope="request"
             class="ua.nure.ohol.SummaryTask4.db.beans.TypeBean"/>
<div class="parallax">

    <div class="search">
        <fmt:bundle basename="constants">

        <p><fmt:message key="home.search.book"/></p>


        <div class="row_line">

            <form action="${pageContext.request.contextPath}/tourSearch" method="post">

                <div class="departure">
                    <label for="descriptionType"><fmt:message key="tour.type.type"/></label>
                    <select id="descriptionType" name="tourType">

                        <c:forEach items="${TypeBean.values}" var="state">
                            <option value="${TypeBean.getType(state.name())}">${state}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="date_start">
                    <input class="field" type="text" name="numberOfPeople"
                           placeholder="<fmt:message key="num_people"/>">
                </div>

                <div class="button_search">
                    <input class="field button_wrapper" type="submit" value="<fmt:message key="search"/>">
                </div>

            </form>


        </div>


    </div>

    </fmt:bundle>
    <%--    <div class="parallax-curtain">--%>
    <div class="search_page">
        <fmt:bundle basename="constants">
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
    <%--    </div>--%>
</div>


<%--<div class="parallax"></div>--%>

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
