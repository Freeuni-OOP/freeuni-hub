<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>
    <title> წარუმატებელი ავტორიზაცია </title>
</head>

<body>
    <h1> იუზერნეიმი ან პაროლი არასწორია, გთხოვთ სცადოთ ხელახლა </h1>
    <form action = "/login" method = "post">
        იუზერნეიმი: <label> <input type = "text" name = "username"> </label> <br/> <br/>
        პაროლი: <label> <input type = "text" name = "password"> </label> <br/> <br/>
    <label> <input type = "submit" value = "შესვლა"> </label> <br/> <br/>
</form>
</body>


</html>
