<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title> Welcome </title>
    <link rel="icon" type="image/png" href="../../Images/freeUni.jpg">
    <link rel="stylesheet" href="../../PageStyles/BodyStyle.css">
</head>
<body>
<h1> მოგესალმებით ფრიუნიჰაბზე! </h1>


<p> გთხოვთ შეიყვანოთ თქვენი ინფორმაცია </p>
<form action="login" method="get">
    იუზერნეიმი:
    <label> <input type="text" name="username"> </label> <br/> <br/>
    პაროლი:
    <label> <input type="text" name="password"> </label> <br/> <br/>
    <label> <input type="submit" value="შესვლა"> </label> <br/> <br/>
</form>

<a href="RegistrationPage.jsp"> ახალი მომხმარებლის რეგისტრაცია </a>

</body>
</html>
