<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 26.03.2020
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userInfoStyle.css">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">

</head>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<style>

    * {
        box-sizing: border-box;
    }

    .autoresizing {
        display: block;
        overflow: hidden;
        resize: none;
        border: none;
    }
</style>
<body>
<fmt:setLocale value="${language}"/>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<fmt:bundle basename="staticinformation">
    <div class="tab">

        <c:choose>
            <c:when test="${tabId=='reservation'}">
                <button class="tablinks" onclick="openTab(event, 'resList')" id="defaultOpen"><fmt:message
                        key="userInfo.field.reservation"/></button>
                <button class="tablinks" onclick="openTab(event, 'hot')"><fmt:message key="hot_tour_admin"/></button>
                <button class="tablinks" onclick="openTab(event, 'discount')"><fmt:message
                        key="admin.field.discount"/></button>
                <c:if test="${user.roleId==0}">
                    <button class="tablinks" onclick="openTab(event, 'tourAdm')"><fmt:message
                            key="admin.field.touradmi"/></button>
                    <button class="tablinks" onclick="openTab(event, 'userInf')"><fmt:message
                            key="admin.field.userInf"/></button>
                </c:if>
            </c:when>
            <c:when test="${tabId=='hot'}">
                <button class="tablinks" onclick="openTab(event, 'resList')"><fmt:message
                        key="userInfo.field.reservation"/></button>
                <button class="tablinks" onclick="openTab(event, 'hot')" id="defaultOpen"><fmt:message
                        key="hot_tour_admin"/></button>
                <button class="tablinks" onclick="openTab(event, 'discount')"><fmt:message
                        key="admin.field.discount"/></button>
                <c:if test="${user.roleId==0}">
                    <button class="tablinks" onclick="openTab(event, 'tourAdm')"><fmt:message
                            key="admin.field.touradmi"/></button>
                    <button class="tablinks" onclick="openTab(event, 'userInf')"><fmt:message
                            key="admin.field.userInf"/></button>
                </c:if>
            </c:when>
            <c:when test="${tabId=='discount'}">
                <button class="tablinks" onclick="openTab(event, 'resList')"><fmt:message
                        key="userInfo.field.reservation"/></button>
                <button class="tablinks" onclick="openTab(event, 'hot')"><fmt:message key="hot_tour_admin"/></button>
                <button class="tablinks" onclick="openTab(event, 'discount')" id="defaultOpen"><fmt:message
                        key="admin.field.discount"/></button>
                <c:if test="${user.roleId==0}">
                    <button class="tablinks" onclick="openTab(event, 'tourAdm')"><fmt:message
                            key="admin.field.touradmi"/></button>
                    <button class="tablinks" onclick="openTab(event, 'userInf')"><fmt:message
                            key="admin.field.userInf"/></button>
                </c:if>
            </c:when>
            <c:when test="${tabId=='tourAdm'}">
                <button class="tablinks" onclick="openTab(event, 'resList')"><fmt:message
                        key="userInfo.field.reservation"/></button>
                <button class="tablinks" onclick="openTab(event, 'hot')"><fmt:message key="hot_tour_admin"/></button>
                <button class="tablinks" onclick="openTab(event, 'discount')"><fmt:message
                        key="admin.field.discount"/></button>
                <c:if test="${user.roleId==0}">
                    <button class="tablinks" onclick="openTab(event, 'tourAdm')" id="defaultOpen">
                        <fmt:message key="admin.field.touradmi"/>
                    </button>
                    <button class="tablinks" onclick="openTab(event, 'userInf')"><fmt:message
                            key="admin.field.userInf"/></button>
                </c:if>

            </c:when>
            <c:when test="${tabId=='userInf'}">
                <button class="tablinks" onclick="openTab(event, 'resList')"><fmt:message
                        key="userInfo.field.reservation"/></button>
                <button class="tablinks" onclick="openTab(event, 'hot')"><fmt:message key="hot_tour_admin"/></button>
                <button class="tablinks" onclick="openTab(event, 'discount')"><fmt:message
                        key="admin.field.discount"/></button>
                <c:if test="${user.roleId==0}">
                    <button class="tablinks" onclick="openTab(event, 'tourAdm')"><fmt:message
                            key="admin.field.touradmi"/></button>
                    <button class="tablinks" onclick="openTab(event, 'userInf')" id="defaultOpen"><fmt:message
                            key="admin.field.userInf"/>
                    </button>
                </c:if>
            </c:when>
        </c:choose>
    </div>

    <div id="resList" class="tabcontent">
        <h3><fmt:message key="userInfo.field.reservation"/></h3>
        <p><fmt:message key="admin.field.descreservationL_list"/></p>
        <c:if test="${not empty sessionScope.upStat}">
            <p style="color: #98261f"><fmt:message key="information"/> ${sessionScope.upStat}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/editStatus" method="post" id="chStat">
            <input class="btn success" type="submit" value="<fmt:message key="admin.btn.save"/>" form="chStat"
                   id="editStatusBtn">
            <input type="hidden" name="tab_id" value="reservation">
        </form>
        <table>
            <thead>
            <tr>
                <th scope="col"><fmt:message key="admin.field.customer"/></th>
                <th scope="col"><fmt:message key="login.field.email"/></th>
                <th scope="col"><fmt:message key="userInfo.field.tour"/></th>
                <th scope="col"><fmt:message key="userInfo.field.price"/></th>
                <th scope="col"><fmt:message key="admin.field.sdate"/></th>
                <th scope="col"><fmt:message key="admin.field.edate"/></th>
                <th scope="col"><fmt:message key="userInfo.field.status"/></th>
            </tr>
            </thead>
            <tbody>

            <c:set var="i" value="1"/>

            <c:forEach items="${statuses}" var="stat">
                <tr>
                    <c:forEach items="${reservationInfo}" var="entity">
                        <c:choose>
                            <c:when test="${entity.key == 'Users'.concat(i)}">
                                <td>${entity.value.lastName} ${entity.value.firstName}</td>
                                <td>${entity.value.email}</td>
                            </c:when>
                            <c:when test="${entity.key == 'Tour'.concat(i)}">
                                <c:if test="${language=='ru'}">
                                    <td>${entity.value.tourNameRu}</td>
                                </c:if>
                                <c:if test="${language=='en'}">
                                    <td>${entity.value.tourName}</td>
                                </c:if>
                                <td>${entity.value.price}</td>
                            </c:when>
                            <c:when test="${entity.key == 'Duration'.concat(i)}">
                                <td>${entity.value.tourBeginDate }</td>
                                <td>${entity.value.tourEndDate }</td>
                            </c:when>
                            <c:when test="${entity.key == 'Reservation'.concat(i)}">
                                <td>
                                    <input type="hidden" id="reserv_id" name="reserv_id" value="${entity.value.id}"
                                           form="chStat"/>
                                    <div class="select"
                                         style="width:200px;">
                                        <select id="stat" name="stat" form="chStat">
                                            <option value="${stat}">${stat}</option>
                                            <c:choose>
                                                <c:when test="${stat == 'REGISTER'}">
                                                    <option value="CANCELED">CANCELED</option>
                                                    <option value="PAID">PAID</option>
                                                </c:when>
                                                <c:when test="${stat == 'CANCELED'}">
                                                    <option value="REGISTER">REGISTER</option>
                                                    <option value="PAID">PAID</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="REGISTER">REGISTER</option>
                                                    <option value="CANCELED">CANCELED</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </select>
                                    </div>
                                </td>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </tr>
                <c:set var="i" value="${(i+1)}"/>
            </c:forEach>

            </tbody>
        </table>
    </div>

    <div id="hot" class="tabcontent"    >
        <h3><fmt:message key="hot_tour_admin"/></h3>
        <p><fmt:message key="admin.field.descHot"/></p>

        <c:if test="${not empty sessionScope.upStat2}">
            <p style="color: #98261f"><fmt:message key="information"/> ${sessionScope.upStat2}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/editHot" method="post" id="chHot">
            <input class="btn success" type="submit" value="<fmt:message key="admin.btn.save"/>" id="editHotBtn">
            <input type="hidden" name="tab_id" value="hot">
        </form>

        <table>

            <thead>
            <tr>
                <th scope="col"><fmt:message key="userInfo.field.tour"/></th>
                <th scope="col"><fmt:message key="userInfo.field.price"/></th>
                <th scope="col"><fmt:message key="hot_tour_admin"/></th>
                <th scope="col"><fmt:message key="admin.field.sdate"/></th>
                <th scope="col"><fmt:message key="admin.field.edate"/></th>
            </tr>
            </thead>

            <tbody>
            <c:set var="i" value="1"/>

            <c:forEach begin="1" end="${countTour}" step="1">
                <tr>
                    <c:forEach items="${hotTourInfo}" var="entity">

                        <c:choose>

                            <c:when test="${entity.key == 'Tour'.concat(i)}">
                                <input type="hidden" id="tour_id" name="tour_id" value="${entity.value.id}"
                                       form="chHot"/>
                                <c:if test="${language=='ru'}">
                                    <td>${entity.value.tourNameRu}</td>
                                </c:if>
                                <c:if test="${language=='en'}">
                                    <td>${entity.value.tourName}</td>
                                </c:if>
                                <td>${entity.value.price}</td>

                                <td>
                                    <div class="select"
                                         style="width:200px;"><select id="hotS" name="check" form="chHot">
                                        <c:choose>
                                            <c:when test="${entity.value.hotTour}">
                                                <option value="true">VALID</option>
                                                <option value="false">INACTIVE</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="false">INACTIVE</option>
                                                <option value="true">VALID</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select></div>
                                </td>

                            </c:when>

                            <c:when test="${entity.key == 'Duration'.concat(i)}">
                                <td>${entity.value.tourBeginDate }</td>
                                <td>${entity.value.tourEndDate }</td>
                                <input type="hidden" name="end_date" value="${entity.value.tourEndDate}" form="chHot"/>
                            </c:when>

                        </c:choose>

                    </c:forEach>
                    <c:set var="i" value="${(i+1)}"/>

                </tr>

            </c:forEach>
            </tbody>

        </table>
    </div>

    <div id="discount" class="tabcontent">
        <h3><fmt:message key="admin.field.discount"/></h3>
        <p><fmt:message key="admin.field.descdiscount"/></p>
        <c:if test="${not empty sessionScope.upStat3}">
            <p style="color: #98261f"><fmt:message key="information"/> ${sessionScope.upStat3}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/editDiscount" method="post" id="chDiscount">
            <input class="btn success" type="submit" value="<fmt:message key="admin.btn.save"/>" id="editDiscountBtn">
            <input type="hidden" name="tab_id" value="discount">
        </form>

        <table>

            <thead>
            <tr>
                <th scope="col"><fmt:message key="userInfo.field.tour"/></th>
                <th scope="col"><fmt:message key="admin.field.begin_price"/></th>
                <th scope="col"><fmt:message key="admin.field.discount"/></th>
                <th scope="col"><fmt:message key="admin.field.name"/></th>
                <th scope="col"><fmt:message key="admin.field.percent"/></th>
                <th scope="col"><fmt:message key="admin.field.step"/></th>
                <th scope="col"><fmt:message key="admin.field.sdate"/></th>
                <th scope="col"><fmt:message key="admin.field.edate"/></th>
                <th scope="col"><fmt:message key="admin.field.price_d"/></th>
                <th scope="col"><fmt:message key="admin.field.date_last"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tourDiscount}" var="step">
                <tr>
                    <input type="hidden" name="tour_id" value="${step.key.id}"
                           form="chDiscount"/>
                    <c:if test="${language=='ru'}">
                        <td>${step.key.tourNameRu}</td>
                    </c:if>
                    <c:if test="${language=='en'}">
                        <td>${step.key.tourName}</td>
                    </c:if>
                    <td>${step.key.price}</td>
                    <c:choose>
                        <c:when test="${step.value != null}">
                            <td>
                                <div class="select"
                                     style="width:120px;"><select name="check" form="chDiscount">
                                    <option value="true">VALID</option>
                                    <option value="false">INACTIVE</option>
                                </select></div>
                            </td>
                            <td>
                            <c:if test="${language=='ru'}">
                                  <textarea class="autoresizing" name="discName" form="chDiscount" required
                                            style="width: 100px;">${step.value.discountNameRu}</textarea>
                            </c:if>
                            <c:if test="${language=='en'}">
                                  <textarea class="autoresizing" name="discName" form="chDiscount" required
                                            style="width: 100px;">${step.value.discountName}</textarea>
                            </c:if>

                            </td>
                            <td><input type="number" value="${step.value.discountPercent}" name="percent"
                                       form="chDiscount" required style="width: 35px; border: none;" min="0"></td>
                            <td><input type="number" value="${step.value.discountStep}" name="step" form="chDiscount"
                                       required style="width: 30px; border: none;" min="0"
                                       max="${step.value.discountPercent-1}"></td>
                            <td><input type="date" value="${step.value.discountDateFrom}" name="dateFrom"
                                       form="chDiscount" required style="border: none;"></td>
                            <td><input type="date" value="${step.value.discountDateTo}" name="dateTo"
                                       form="chDiscount" required style="border: none;"></td>
                            <td>${step.value.discountPrice}</td>
                            <td>${step.value.discountLastChangeData}</td>
                            <input type="hidden" name="query" form="chDiscount" value="update">
                        </c:when>

                        <c:otherwise>
                            <input type="hidden" name="query" form="chDiscount" value="insert">

                            <td>
                                <div class="select"
                                     style="width:120px;"><select name="check" form="chDiscount" id="sel"
                                                                  onchange="x()">
                                    <option value="false">INACTIVE</option>
                                    <option value="true">VALID</option>
                                </select></div>
                            </td>
                            <td><textarea class="autoresizing" name="discName" form="chDiscount"
                                          id="discName" style="width: 100px;"></textarea></td>
                            <td><input type="number" name="percent" id="percent"
                                       form="chDiscount" style="width: 35px; border: none;" min="0"></td>
                            <td><input type="number" name="step" form="chDiscount"
                                       style="width: 30px; border: none;" min="0" id="step"></td>
                            <td><input type="date" name="dateFrom" id="from"
                                       form="chDiscount" style="border: none;"></td>
                            <td><input type="date" name="dateTo" id="to"
                                       form="chDiscount" style="border: none;"></td>
                            <td></td>
                            <td></td>


                        </c:otherwise>
                    </c:choose>

                </tr>

            </c:forEach>


            </tbody>

        </table>

    </div>

    <div id="tourAdm" class="tabcontent">
        <h3><fmt:message key="admin.field.touradmi"/></h3>
        <p><fmt:message key="admin.field.desctouradmin"/></p>

        <p style="color: #98261f"><fmt:message key="information"/> ${sessionScope.upStat4}</p>


        <form action="${pageContext.request.contextPath}/addTour" method="get">
            <input class="btn success" type="submit" value="<fmt:message key="admin.btn.add"/>" id="addTour">
        </form>

        <table>

            <thead>
            <tr>
                <th scope="col"><fmt:message key="admin.field.name"/></th>
                <th scope="col"><fmt:message key="userInfo.field.price"/></th>
                <th scope="col"><fmt:message key="admin.field.avai_tickets"/></th>
                <th scope="col"><fmt:message key="admin.field.all_tickets"/></th>
                <th scope="col" style="width: 30px"></th>
                <th scope="col" style="width: 30px"></th>
            </tr>
            </thead>
            <tbody>
            <c:set var="i" value="1"/>

            <c:forEach begin="1" end="${tourInfo.size()}" step="1">
                <tr>
                    <c:forEach items="${tourInfo}" var="tour">

                        <c:choose>

                            <c:when test="${tour.key == 'Tour'.concat(i)}">
                                <c:if test="${language == 'ru'}">
                                    <td>${tour.value.tourNameRu}</td>
                                </c:if>
                                <c:if test="${language == 'en'}">
                                    <td>${tour.value.tourName}</td>
                                </c:if>
                                <td>${tour.value.price}</td>
                                <td>${tour.value.availableTickets}</td>
                                <td>${tour.value.amountTickets}</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/editTour" method="post"
                                          id="chTourUpdate">
                                        <input class="btn success" type="submit"
                                               value="<fmt:message key="admin.btn.edit"/>">
                                        <input type="hidden" name="tour_id" value="${tour.value.id}">
                                        <input type="hidden" name="tab_id" value="tourAdm">
                                    </form>
                                </td>

                                <td>
                                    <form action="${pageContext.request.contextPath}/deleteTour" method="post"
                                          id="chTourDelete">
                                        <input class="btn success" type="submit"
                                               value="<fmt:message key="admin.btn.delete"/>" id="deleteTour">
                                        <input type="hidden" name="tour_id" value="${tour.value.id}">
                                        <input type="hidden" name="tab_id" value="tourAdm">
                                    </form>
                                </td>
                            </c:when>

                            <c:when test="${tour.key == 'Duration'.concat(i)}">

                            </c:when>

                            <c:when test="${tour.key == 'Hotel'.concat(i)}">

                            </c:when>

                            <c:when test="${tour.key == 'Description'.concat(i)}">

                            </c:when>

                        </c:choose>

                    </c:forEach>

                    <c:set var="i" value="${(i+1)}"/>

                </tr>

            </c:forEach>
            </tbody>

        </table>


    </div>

    <div id="userInf" class="tabcontent">
        <h3><fmt:message key="admin.field.userInf"/></h3>
        <p><fmt:message key="admin.field.descuserInf"/></p>
        <c:if test="${not empty sessionScope.upStat5}">
            <p style="color: #98261f"><fmt:message key="information"/> ${sessionScope.upStat5}</p>
        </c:if>
        <form action="${pageContext.request.contextPath}/editUserValidation" method="post" id="chUsers">
            <input class="btn success" type="submit" value="<fmt:message key="admin.btn.save"/>" id="editUsersInfo">
            <input type="hidden" name="tab_id" value="userInf">
        </form>

        <table>

            <thead>
            <tr>
                <th scope="col"><fmt:message key="admin.field.name"/></th>
                <th scope="col"><fmt:message key="admin.field.position"/></th>
                <th scope="col"><fmt:message key="login.field.login"/></th>
                <th scope="col"><fmt:message key="login.field.email"/></th>
                <th scope="col"><fmt:message key="login.field.phone"/></th>
                <th scope="col"><fmt:message key="userInfo.field.status"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userInfo}" var="user">
                <tr>
                    <input type="hidden" name="user_id" value="${user.id}"
                           form="chUsers"/>
                    <td>${user.lastName} ${user.firstName}</td>
                    <c:choose>
                        <c:when test="${user.roleId==0}">
                            <td><fmt:message key="admin"/></td>
                        </c:when>
                        <c:when test="${user.roleId==1}">
                            <td><fmt:message key="manager"/></td>
                        </c:when>
                        <c:when test="${user.roleId==2}">
                            <td><fmt:message key="customer"/></td>
                        </c:when>
                    </c:choose>
                    <td>${user.login}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>
                        <div class="select"
                             style="width:200px;"><select id="valid" name="valid" form="chUsers">
                            <c:choose>
                                <c:when test="${user.validUser}">
                                    <option value="true">VALID</option>
                                    <option style="color: #98261f" value="false">BLOCKED</option>
                                </c:when>
                                <c:otherwise>
                                    <option style="color: #98261f" value="false">BLOCKED</option>
                                    <option value="true">VALID</option>
                                </c:otherwise>
                            </c:choose>
                        </select></div>
                    </td>


                </tr>

            </c:forEach>


            </tbody>

        </table>
    </div>

</fmt:bundle>

<script type="text/javascript">
    $('.autoresizing').on('input', function () {
        this.style.height = 'auto';

        this.style.height =
            (this.scrollHeight) + 'px';
    });
</script>

<jsp:include page="_footer.jsp"/>

<script>
    function x() {
        var selId = document.getElementById("sel");

        if (selId.value === "true") {
            document.getElementById("discName").setAttribute("required", "");
            document.getElementById("percent").setAttribute("required", "");
            document.getElementById("step").setAttribute("required", "");
            document.getElementById("from").setAttribute("required", "");
            document.getElementById("to").setAttribute("required", "");
        } else if (selId.value === "false") {
            document.getElementById("discName").removeAttribute("required");
            document.getElementById("percent").removeAttribute("required");
            document.getElementById("step").removeAttribute("required");
            document.getElementById("from").removeAttribute("required");
            document.getElementById("to").removeAttribute("required");
        }
    }

    function openTab(evt, tabName) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(tabName).style.display = "block";
        evt.currentTarget.className += " active";
    }

    // Get the element with id="defaultOpen" and click on it
    document.getElementById("defaultOpen").click();


</script>


</body>
</html>
