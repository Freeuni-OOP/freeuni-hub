<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>
    <title> Registration </title>
    <link rel="icon" type="image/png" href="../../Images/freeUni.jpg">
    <link rel="stylesheet" href="../../PageStyles/BodyStyle.css">
</head>

<body>
<p> გთხოვთ გაიაროთ რეგისტრაცია </p>

<form action="register" method="get">
    <label for="username"> იუზერნეიმი: </label>
    <input type="text" name="username" id="username"> <br/> <br/>
    <label for="password"> პაროლი: </label>
    <label> <input type="text" name="password" id="password"> </label> <br/> <br/> <%-- good hash needed --%>
    <label> <input type="submit" value="რეგისტრაცია"> </label> <br/> <br/>
</form>


</body>


</html>
