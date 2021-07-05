
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>
    <title> Registration </title>
</head>

<body>

    <p> Please register below </p>

    <form action = "register" method = "get">
        Username:
        <label> <input type = "text" name = "username"> </label> <br/> <br/>
        Password:
        <label> <input type = "text" name = "password"> </label> <br/> <br/> <%-- good hash needed --%>
        <label> <input type = "submit" value = "register"> </label> <br/> <br/>
    </form>

    </body>

</html>
