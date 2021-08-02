<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title> სალამი! </title>

    <link rel = "stylesheet" href = "../../PageStyles/BodyStyle.css">
</head>
<body>

    <form action = "/login" method = "post" id = "form_login">
        <h1> მოგესალმებით ფრიუნიჰაბზე! </h1>
        <p> გთხოვთ შეიყვანოთ თქვენი ინფორმაცია </p>
        <label for = "username"> იუზერნეიმი: </label>
        <input type = "text" name = "username" id = "username"> <br/> <br/>
        <label for = "password"> პაროლი: </label>
        <input type = "password" name = "password" id = "password"> <br/> <br/>
        <input type = "checkbox" onclick = myFunction() id = "show">
        <label for = "show"> პაროლის ჩვენება </label> <br> <Br>
        <label> <input type = "submit" value = "შესვლა"> </label> <br/> <br/>
        <a href = "RegistrationPage.jsp"> ახალი მომხმარებლის რეგისტრაცია </a>
    </form>

    <!-- ---------------------------------------------------------- java script -->
    <script>
        function myFunction() {
            let field = document.getElementById("password");
            if (field.type === "password") field.type = "text";
            else field.type = "password";
        }
    </script>
</body>
</html>
