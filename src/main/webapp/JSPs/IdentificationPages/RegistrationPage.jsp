<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>


<head>
    <title> რეგისტრაცია </title>
    <link rel="stylesheet" href="../../PageStyles/RegistrationStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<body>


<form action="/register" method="post" id="form_register">
    <p> გთხოვთ გაიაროთ რეგისტრაცია </p>
    <label for="firstName"> სახელი: </label>
    <input class="form-control" type="text" name="firstName" id="firstName"
           size="38" placeholder="ლათინური პატარა ასოები"> <br/>
    <label for="lastName"> გვარი: </label>
    <input class="form-control" type="text" name="lastName" id="lastName"
           size="40" placeholder="ლათინური პატარა ასოები"> <br/>
    <label for="username"> იუზერნეიმი: </label>
    <input class="form-control" type="text" name="username" id="username"
           size="33" placeholder="ლათინური ასოები, ციფრები, _"> <br/>
    <label for="password"> პაროლი: </label>
    <input class="form-control" type="password" name="password" id="password"
           size="37" placeholder="ლათინური ასოები, ციფრები, _"> <br/>
    <div>
        <input class="form-check-input" type="checkbox" onclick=change() id="show">
        <label for="show"> პაროლის ჩვენება </label>
    </div><br>
    <label for="repeatPassword"> პაროლის გამეორება: </label>
    <input class="form-control" type="password" name="repeatPassword" id="repeatPassword"
           size="37" placeholder="გაიმეორეთ პაროლი"> <br/>
    <label for="mail"> ელ-ფოსტა: </label>
    <input class="form-control" type="text" name="mail" id="mail"
           size="35" placeholder="მაგ: gadik19@freeuni.edu.ge"> <br/>
    <input class="btn btn-primary" type="submit" value="რეგისტრაცია"> <br/>
    <a class="btn btn-warning" href="${pageContext.request.contextPath}/JSPs/IdentificationPages/LogInPage.jsp">არსებული მომხარებლით შესვლა</a>
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
