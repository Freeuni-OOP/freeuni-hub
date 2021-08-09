<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.math.*, java.util.*,Manage.HelperClasses.User" %>
<html>

<head>
    <link rel="stylesheet" href = "../../PageStyles/HomePageStyle.css"/>
    <title> სალამი ${username} </title>
    <h1>  ქვემოთ ჩამოთვლილია თქვენთან მეგობრების მსურველები:</h1>
</head>

<body>

     <%
     ArrayList<User> all = (ArrayList<User>)request.getSession().getAttribute("requesters");
     for(int i=0;i<all.size();i++){
     int id=all.get(i).getId();
     String userName =all.get(i).getUserName();
     String userFirstName = all.get(i).getUserFirstName();
     String userLastName = all.get(i).getUserLastName();
     %>
        <form action="/answerRequest" , method="post">
        <%=userName%>
        <input type="hidden" name="username"  value=${username}>
        <input type="hidden" name=<%=id%>  value=<%=userName%>>
        <p class="info">სახელი: <%=userFirstName%> </p>
        <p class="info">გვარი:<%=userLastName%></p>
        <input type="submit" name="action" value ="accept">
        <input type="submit" name="action" value = "delete">
        <p>  </p>
        </form>
     <%}
     %>

</body>

</html>
