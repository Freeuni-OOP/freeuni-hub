<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title> Retry </title>
</head>


<body>
<h1> Invalid inputs </h1>
<p> არასწორი რეგისტრაცია! გთხოვთ სცადოთ თავიდან. </p>

<form action="register" method="get">
    იუზერნეიმი:
    <label> <input type="text" name="username"> </label> <br/> <br/>
    პაროლი:
    <label> <input type="text" name="password"> </label> <br/> <br/> <%-- good hash needed --%>
    <label> <input type="submit" value="register"> </label> <br/> <br/>
</form>

</body>


</html>
