<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title> სალამი! </title>
</head>
<body>
<h1> მოგესალმებით ფრიუნიჰაბზე! </h1>


<p> გთხოვთ შეიყვანოთ თქვენი ინფორმაცია </p>
<form action = "login" method = "post">
    იუზერნეიმი:
    <label> <input type = "text" name = "username"> </label> <br/> <br/>
    პაროლი:
    <label> <input type = "text" name = "password"> </label> <br/> <br/>
    <label> <input type = "submit" value = "შესვლა"> </label> <br/> <br/>
</form>

<a href="RegistrationPage.jsp"> ახალი მომხმარებლის რეგისტრაცია </a>

</body>
</html>
