<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.math.*, java.util.*,Manage.HelperClasses.User" %>
<html>

<head>
    <link rel="stylesheet" href = "../../PageStyles/HomePageStyle.css"/>
    <title> სალამი ${username} </title>
    <h1>  ქვემოთ ჩამოთვლილია თქვენთან მეგობრების მსურველები:</h1>
</head>

<body>
    <form action="/answerRequest" , method="post">
     <%
     ArrayList<User> all = (ArrayList<User>)request.getSession().getAttribute("requesters");
     System.out.println(all.size());
     for(int i=0;i<all.size();i++){
     int id=all.get(i).getId();
     String userName =all.get(i).getUserName();
     String userFirstName = all.get(i).getUserFirstName();
     String userLastName = all.get(i).getUserLastName();
     System.out.println(userFirstName);
     %>
        <%=userName%>
        <p class="info">სახელი: <%=userFirstName%> </p>
        <p class="info">გვარი:<%=userLastName%></p>
        <input type="submit" value ="Accept">
        <input type="submit" value = "Reject"
        <p>  </p>
     <%}
     %>


</body>

</html>
