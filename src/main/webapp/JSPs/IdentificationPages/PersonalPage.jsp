
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>

<head>
    <title> სალამი ${username} </title>
    <link rel = "stylesheet" href="../../PageStyles/PersonalPageStyle.css"/>
    <link rel = "script" href = "../../PageScripts/PersonalPageScript.js"/>
</head>

<body>

<div class = "container">

    <!---------------------------------------------------->
    <div class = "searching_div">
        <label for = "profile_search" style="float: right;"> </label>
            <input type = "text" onkeyup = searchHelper() placeholder = "მოძებნე.." id = "profile_search"/>

        <a href="../../JSPs/IdentificationPages/LogInPage.jsp" style="float: right">
            <img src="../../Images/UsefulIcons/logout.png" alt = "logout" width="50" height="50"> </a>

        <a href = "../../JSPs/IdentificationPages/HomePage.jsp" style="float: right;">
            <img src="../../Images/UsefulIcons/home.png" alt = "goHome" width="50" height="50"> </a>

    </div>


    <!-- ------------------------------------------------------------------------ -->
        <div class = "profile_div">
            <img class = "back_picture"
                    src = "../../Images/UsefulImages/freeUni.jpg" alt = "back_image" width="2047" height="500"/>
            <div id = "profile_section">
                <img class = "profile_picture"
                        src = "../../Images/UserImages/default.png" alt = "profile_image"/>
                <h1> ${username} </h1>
            </div>
        </div>






        <!-- ------------------------------------------------------------------------ -->

        <div class = "menu_div">
            <ul id = "menu">
                <li> <a href = "#"> HTML </a> </li>
                <li> <a href = "#"> CSS </a> </li>
                <li> <a href = "#"> JavaScript </a> </li>
                <li> <a href = "#"> PHP </a> </li>
                <li> <a href = "#"> Python </a> </li>
                <li> <a href = "#"> jQuery </a> </li>
                <li> <a href = "#"> SQL </a> </li>
                <li> <a href = "#"> Bootstrap </a> </li>
                <li> <a href = "#"> Node.js </a> </li>
            </ul>
        </div>

        <!-- ----------------------------------------------------------------------------- -->

        <section id = "achievements_section"> <!-- achievements table-->
            <ul>
                <li>

                </li>
            </ul>
        </section>

        <!-- ----------------------------------------------------------------------------- -->

        <div id = "friends_div">

        </div>

        <!-- ----------------------------------------------------------------------------- -->
    </div>

</body>

</html>


