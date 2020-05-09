<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 23.03.2020
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #E5E3DD;
        }

        .topnav a {
            float: left;
            display: block;
            color: #795642;
            text-align: center;
            padding: 25px 60px;
            text-decoration: none;
            font-size: 17px;
        }

        #img {
            float: left;
        }


        .topnav a:hover {
            background-color: #637349;
            color: #C2CAC9;
        }

        .topnav a.active {

            background-color: #637349;
            color: #C2CAC9;
        }

        .topnav .icon {
            display: none;
        }

        @media screen and (max-width: 600px) {
            .topnav a:not(:first-child) {
                display: none;
            }

            .topnav a.icon {
                float: right;
                display: block;
            }

            .topnav.responsive {
                position: relative;
            }

            .topnav.responsive .icon {
                position: absolute;
                right: 0;
                top: 0;
            }

            .topnav.responsive a {
                float: none;
                display: block;
                text-align: left;
            }
        }

        .sticky {
            position: fixed;
            top: 0;
            width: 100%;
        }

    </style>
</head>
<body>
<c:set var="language" value="${cookie.language.value !=null ? cookie.language.value :'en'}" scope="session"/>

<fmt:setLocale value="${language}"/>

<div class="topnav" id="myTopnav">
    <fmt:bundle basename="staticinformation" prefix="menu.href.">
        <img src="images/menuLogo.png" alt="logo.png" width="789" height="70" id="img">
        <a class="active" href="${pageContext.request.contextPath}/home" id="fr"><fmt:message key="home"/></a>
        <a href="${pageContext.request.contextPath}/tourSearch"><fmt:message key="tour_search"/></a>
        <a href="${pageContext.request.contextPath}/hotTours"><fmt:message key="hot_tour"/></a>
        <a href="javascript:void(0);" class="icon" onclick="myFunction()">
            <i class="fa fa-bars"></i>
        </a>
    </fmt:bundle>
</div>

<script>
    function myFunction() {
        var x = document.getElementById("myTopnav");
        if (x.className === "topnav") {
            x.className += " responsive";
        } else {
            x.className = "topnav";
        }
    }

    window.onscroll = function () {
        myFunction()
    };

    // var header = document.getElementById("myTopnav");
    // var sticky = header.offsetTop;
    //
    // function myFunction() {
    //     if (window.pageYOffset > sticky) {
    //         header.classList.add("sticky");
    //     } else {
    //         header.classList.remove("sticky");
    //     }
    // }
</script>

</body>
</html>


