<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}../PageStyles/HomePageStyle.css"/>
    <title> სალამი ${username} </title>
</head>

<body>


<h1> გამარჯობა ${username} ! </h1>

<ul>
    <li> <a class = "active" href = "HomePage.jsp"> მთავარი გვერდი </a> </li>
    <li> <a href = "../ContactPages/ContactPage.jsp"> კონტაქტი </a> </li>
    <li> <a href = "../AboutUsPages/AboutUsPage.jsp"> ჩვენ შესახებ </a> </li>
    <input type = "image" alt = "Submit" width = "30" height = "30">
</ul>

</body>

</html>
