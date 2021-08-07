<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.math.*, java.util.*,Manage.HelperClasses.User" %>
<html>

<head>

    <title> სალამი ${username} </title>
    <h1> მსგავსი იუზერნეიმები: </h1>
</head>

     <%
         ArrayList<User> all = (ArrayList<User>)request.getSession().getAttribute("searchList");
         System.out.println(all.size());
         for(int i=0;i<all.size();i++){
         int id=all.get(i).getId();
         String username =all.get(i).getUserName();
           System.out.println(username);
           out.println(
           "<li>"+
                                   "<p>"+
                                       username+
                                   "</p>"+
                            "</li>");
         }
         %>
<body>




</body>

</html>
