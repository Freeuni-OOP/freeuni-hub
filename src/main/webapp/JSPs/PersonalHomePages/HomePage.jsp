<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>
    <link rel="stylesheet" href="../../PageStyles/HomePageStyle.css"/>
    <title> სალამი ${username} </title>
</head>

<body>


<ul>
    <li><a href="${pageContext.request.contextPath}/JSPs/IdentificationPages/LogInPage.jsp"> გასვლა </a></li>
    <li><a href="${pageContext.request.contextPath}/JSPs/AboutUsPages/AboutUsPage.jsp"> ჩვენ შესახებ </a></li>
    <li><a href="${pageContext.request.contextPath}/JSPs/ContactPages/ContactPage.jsp"> კონტაქტი </a></li>
    <li><a href="${pageContext.request.contextPath}/JSPs/SavelePages/TradePage.jsp"> საველე გაცვლები </a></li>
    <li><a href="${pageContext.request.contextPath}/JSPs/PersonalHomePages/HomePage.jsp"> მთავარი გვერდი </a></li>
    <form action="${pageContext.request.contextPath}/FriendRequests" method="post" id="Friend_Request">
        <button> მეგობრების თხოვნები</button>
    </form>
    <form action="${pageContext.request.contextPath}/SaveleRequests" method="post" id="Savele_Request">
        <button> საველეს გაცვლის თხოვნები</button>
    </form>
    <form action="${pageContext.request.contextPath}/DeleteAccount" method="post" id="Delete_Request">
        <input type="hidden" name="username" value=${username}>
        <button> გააუქმე ექაუნთი </button>
    </form>
    <form action="${pageContext.request.contextPath}/Search" method="post" id="Search_Request">
        <button> მოძებნე იუზერი: </button>
        <input type="text" name="username" id="username" size="25"> <br/> <br/>
        <input type="hidden" name="curUser" value=${username}>
    </form>
    <a href="${pageContext.request.contextPath}/JSPs/PersonalHomePages/PersonalPage.jsp" id="personal_photo_home">
        <img src="../../Images/UserImages/default.png" alt="Avatar" height="50" width="50"/>
    </a>
</ul>


<h1 id="welcome_text"> გამარჯობა ${username} ! </h1>


</body>

</html>
