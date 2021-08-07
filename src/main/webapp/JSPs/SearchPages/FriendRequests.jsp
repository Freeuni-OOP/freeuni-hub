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
     System.out.println(all.size());
     for(int i=0;i<all.size();i++){
     int id=all.get(i).getId();
     String username =all.get(i).getUserName();
       System.out.println(all.size());
     }
     %>


</body>

</html>
