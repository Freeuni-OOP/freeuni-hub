<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.math.*, java.util.*,Manage.HelperClasses.User" %>
<html>

<head>

    <title> სალამი ${username} </title>
    <h1> მსგავსი იუზერნეიმები: </h1>
</head>
<body>



     <%
         ArrayList<User> all = (ArrayList<User>)request.getSession().getAttribute("searchList");
         for(int i=0;i<all.size();i++){
         int id=all.get(i).getId();
         String user =all.get(i).getUserName();
         String userFirstName =all.get(i).getUserFirstName();
         String userLastName = all.get(i).getUserLastName();
          %>
         <form action="/visitProfile" , method="post">
         <input type="hidden" name="username"  value=${username}>
         <input type="hidden" name="profileName"  value=<%=user%>>
         <%=user%>
         <p class="info">სახელი: <%=userFirstName%> </p>
         <p class="info">გვარი:<%=userLastName%></p>
         <input type="submit" value ="Visit Profile">
         <p>  </p>
         <%}
         %>
        </form>



</body>

</html>
