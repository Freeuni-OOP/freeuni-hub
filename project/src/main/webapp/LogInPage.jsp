
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title> Welcome </title>
</head>
<body>
  <h1> Welcome to FreeuniHub! </h1>

   <p> Please enter your information </p>
    <form action = "login" method = "get">
        Username:
        <label> <input type = "text" name = "username"> </label> <br/> <br/>
        Password:
        <label> <input type = "text" name = "password"> </label> <br/> <br/>
        <label> <input type = "submit" value = "log in"> </label> <br/> <br/>
    </form>

    <a href = "RegisterPage.jsp"> Registration for new user </a>

</body>
</html>
