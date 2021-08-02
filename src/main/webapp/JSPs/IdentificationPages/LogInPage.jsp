<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title> სალამი! </title>
    <link rel = "stylesheet" href = "../../PageStyles/LogInStyle.css">
    <style>
        h1 {
            color: GREEN;
        }
    </style>
</head>
<body>

    <form action = "/login" method = "post" id = "form_login">
        <h1> მოგესალმებით ფრიუნიჰაბზე! </h1>
        <p> გთხოვთ შეიყვანოთ თქვენი ინფორმაცია </p>
        <label for = "username"> იუზერნეიმი: </label>
        <input type = "text" name = "username" id = "username" size = "25"> <br/> <br/>
        <label for = "password"> პაროლი: </label>
        <input type = "password" name = "password" id = "password" size = "30"> <br/> <br/>
        <input type = "checkbox" onclick = change() id = "show">
        <label for = "show"> პაროლის ჩვენება </label> <br> <Br>
        <label> <input type = "submit" value = "შესვლა"> </label> <br/> <br/>
        <a href = "RegistrationPage.jsp"> ახალი მომხმარებლის რეგისტრაცია </a>
    </form>

    <!------------------------------------------------------------ java script -->
    <script>
        function change() {
            let field = document.getElementById("password");
            if (field.type === "password") field.type = "text";
            else field.type = "password";
        }
    </script>
</body>
</html>
