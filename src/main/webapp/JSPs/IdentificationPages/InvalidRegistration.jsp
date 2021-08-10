<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>


<head>
    <link rel = "stylesheet" href = "../../PageStyles/RegistrationStyle.css">
    <style>
        h5 {
            color: red;
        }
    </style>
    <title> ხელახალი მცდელობა </title>
</head>



<body>

<form action="register" method="post" id = "form_register">
    ${problem} <br>
    <h5> სცადეთ ხელახლა </h5> <br>
        <label for = "firstName"> სახელი: </label>
        <input type = "text" name = "firstName" id = "firstName"
               size = "38" placeholder = "ლათინური პატარა ასოები"> <br/> <br/>
        <label for = "lastName"> გვარი: </label>
        <input type = "text" name = "lastName" id = "lastName"
               size = "40" placeholder = "ლათინური პატარა ასოები"> <br/> <br/>
        <label for = "username"> იუზერნეიმი: </label>
        <input type = "text" name = "username" id = "username"
               size = "33" placeholder = "ლათინური ასოები, ციფრები, _"> <br/> <br/>
        <label for = "password"> პაროლი: </label>
        <input type = "password" name = "password" id = "password"
               size = "37" placeholder = "ლათინური ასოები, ციფრები, _"> <br/> <br/>
        <input type = "checkbox" onclick = change() id = "show">
        <label for = "show"> პაროლის ჩვენება </label> <br> <Br>
        <label for = "repeatPassword"> პაროლის გამეორება: </label>
        <input type = "password" name = "repeatPassword" id = "repeatPassword"
               size = "37" placeholder = "გაიმეორეთ პაროლი"> <br/> <br/>
        <label for = "mail"> ელ-ფოსტა: </label>
        <input type = "text" name = "mail" id = "mail"
               size = "35" placeholder = "მაგ: gadik19@freeuni.edu.ge"> <br/> <br/>
        <input type = "submit" value = "რეგისტრაცია"> <br/> <br/>
</form>



<!-- ---------------------------------------------------------- java script -->
<script>
    function change() {
        let field = document.getElementById("password");
        if (field.type === "password") field.type = "text";
        else field.type = "password";
    }
</script>

</body>


</html>
