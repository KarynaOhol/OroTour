<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 23.03.2020
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
<%--    <style>--%>
<%--        body {--%>
<%--            margin: 0;--%>
<%--            font-family: Arial, Helvetica, sans-serif;--%>
<%--        }--%>

<%--        .hero-image {--%>
<%--            /*background-image: url("/images/bali1.jpg");*/--%>
<%--            background-color: #cccccc;--%>
<%--            height: 500px;--%>
<%--            background-position: center;--%>
<%--            background-repeat: no-repeat;--%>
<%--            background-size: cover;--%>
<%--            position: relative;--%>
<%--        }--%>

<%--        .hero-text {--%>
<%--            text-align: center;--%>
<%--            position: absolute;--%>
<%--            top: 50%;--%>
<%--            left: 50%;--%>
<%--            transform: translate(-50%, -50%);--%>
<%--            color: white;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>

<%--<div class="hero-image">--%>
<%--    <img src="${pageContext.request.contextPath}/images/bali2.jpg" alt="logo.png" >--%>
<%--    <div class="hero-text">--%>
<%--        <h1 style="font-size:50px">I am Jane Doe</h1>--%>
<%--        <h3>And I'm a Photographer</h3>--%>
<%--        <button>Hire me</button>--%>
<%--    </div>--%>

<%--<p>Page content..</p>--%>
<%--<p>Note that this technique will also make the image responsive: Resize the browser window to see the effect.</p>--%>
<%--</div>--%>

<%--</body>--%>
<%--</html>--%>


<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/homeStyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<%--<p>In this example we have turned off parallax scrolling for mobile devices. It works as expected on all desktop screens sizes.</p>--%>
<%--<p>Scroll Up and Down this page to see the parallax scrolling effect.</p>--%>

<div class="parallax">
    hfyfjgvjhbjbjk
    <div  class="parallax-curtain" >
        dfsdgdrhtgfhgfhf
    </div>
</div>



<%--<div class="parallax"></div>--%>

<jsp:include page="_footer.jsp"/>

</body>
</html>
