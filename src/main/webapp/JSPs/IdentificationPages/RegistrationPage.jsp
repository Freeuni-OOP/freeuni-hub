<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>
    <title> Registration </title>
    <link rel = "stylesheet" href = "../../PageStyles/BodyStyle.css">
</head>

<body>
<p> გთხოვთ გაიაროთ რეგისტრაცია </p>

<form action = "/register" method = "post">
    <label for = "firstName"> სახელი: </label>
    <input type = "text" name = "firstName" id = "firstName"> <br/> <br/>
    <label for = "lastName"> გვარი: </label>
    <input type = "text" name = "lastName" id = "lastName"> <br/> <br/>
    <label for = "username"> იუზერნეიმი: </label>
    <input type = "text" name = "username" id = "username"> <br/> <br/>
    <label for = "password"> პაროლი: </label>
    <input type = "text" name = "password" id = "password"> <br/> <br/>
    <label for = "mail"> ელ-ფოსტა: </label>
    <input type = "text" name = "mail" id = "mail"> <br/> <br/>
    <input type = "submit" value = "რეგისტრაცია"> <br/> <br/>
</form>


</body>


</html>
