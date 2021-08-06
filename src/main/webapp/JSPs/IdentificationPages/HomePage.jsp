<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>
    <link rel="stylesheet" href = "../../PageStyles/HomePageStyle.css"/>
    <title> სალამი ${username} </title>
</head>

<body>

<ul>
    <li> <a href = "${pageContext.request.contextPath}/JSPs/IdentificationPages/LogInPage.jsp"> გასვლა </a> </li>
    <li> <a href = "${pageContext.request.contextPath}/JSPs/AboutUsPages/AboutUsPage.jsp"> ჩვენ შესახებ </a> </li>
    <li> <a href = "${pageContext.request.contextPath}/JSPs/ContactPages/ContactPage.jsp"> კონტაქტი </a> </li>
    <li> <a href = "${pageContext.request.contextPath}/JSPs/IdentificationPages/HomePage.jsp"> მთავარი გვერდი </a> </li>
   <form action = "/FriendRequests" method = "post" id = "Friend_Request">
   <button> მეგორების თხოვნები </button>
   </form>
   <form action = "/Search" method = "post" id = "Search_Request">
   <button = "username"> მოძებნე იუზერი: </button>
       <input type = "text" name = "username" id = "username" size = "25"> <br/> <br/>
   </form>
   <a href = "${pageContext.request.contextPath}/JSPs/IdentificationPages/PersonalPage.jsp" id = "personal_photo_home">
        <img src="../../Images/UserImages/default.png" alt = "Avatar" height = "50" width = "50" />
   </a>
</ul>


<h1 id = "welcome_text"> გამარჯობა ${username} ! </h1>





</body>

</html>
