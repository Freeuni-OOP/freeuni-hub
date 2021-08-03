<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>
    <title> წარუმატებელი ავტორიზაცია </title>
    <style>
        h4 {
            color: red;
        }
    </style>
    <link rel = "stylesheet" href = "../../PageStyles/LogInStyle.css">
</head>

<body>

<form action = "/login" method = "post" id = "form_login">
    <h4> იუზერნეიმი ან პაროლი არასწორია, გთხოვთ სცადოთ ხელახლა </h4>
    <label for = "username"> იუზერნეიმი: </label>
    <input type = "text" name = "username" id = "username" size = "25"> <br/> <br/>
    <label for = "password"> პაროლი: </label>
    <input type = "password" name = "password" id = "password" size = "30"> <br/> <br/>
    <input type = "checkbox" onclick = change() id = "show">
    <label for = "show"> პაროლის ჩვენება </label> <br> <Br>
    <label> <input type = "submit" value = "შესვლა"> </label> <br/> <br/>
    <a href = "RegistrationPage.jsp"> ახალი მომხმარებლის რეგისტრაცია </a>
</form>


<!-- ------------------------------------------------------------------------java script -->
<script>
    function change() {
        let field = document.getElementById("password");
        if (field.type === "password") field.type = "text";
        else field.type = "password";
    }
</script>

</body>


</html>
