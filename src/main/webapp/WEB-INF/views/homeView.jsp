<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 23.03.2020
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body, html {
            height: 100%;
        }

        .parallax {
            /* The image used */
            background-image: url('/images/bali1.jpg');

            /* Full height */
            height: 100%;

            /* Create the parallax scrolling effect */
            background-attachment: fixed;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }
    </style>
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>


<p>Scroll Up and Down this page to see the parallax scrolling effect.</p>

<div class="parallax"></div>

<div style="height:300px;background-color:red;font-size:36px">
    Scroll Up and Down this page to see the parallax scrolling effect.
    This div is just here to enable scrolling.
    Tip: Try to remove the background-attachment property to remove the scrolling effect.
</div>
<jsp:include page="_footer.jsp"/>

</body>
</html>
