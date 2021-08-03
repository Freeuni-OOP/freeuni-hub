<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}../PageStyles/HomePageStyle.css"/>
    <title> სალამი ${username} </title>
</head>

<body>

<ul>
    <li> <a href = "${pageContext.request.contextPath}/JSPs/IdentificationPages/LogInPage.jsp"> გასვლა </a> </li>
    <li> <a href = "${pageContext.request.contextPath}/JSPs/AboutUsPages/AboutUsPage.jsp"> ჩვენ შესახებ </a> </li>
    <li> <a href = "${pageContext.request.contextPath}/JSPs/ContactPages/ContactPage.jsp"> კონტაქტი </a> </li>
    <li> <a href = "${pageContext.request.contextPath}/JSPs/IdentificationPages/HomePage.jsp"> მთავარი გვერდი </a> </li>
    <a href = "${pageContext.request.contextPath}/JSPs/IdentificationPages/PersonalPage.jsp" id = "personal_photo_home">
        <img src="../../Images/UserImages/default.png" alt = "Avatar" height = "50" width = "50" />
    </a>
</ul>


<h1 id = "welcome_text"> გამარჯობა ${username} ! </h1>





</body>

</html>
