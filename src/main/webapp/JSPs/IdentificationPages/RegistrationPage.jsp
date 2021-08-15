<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>


<head>
    <title> რეგისტრაცია </title>
    <link rel="stylesheet" href="../../PageStyles/RegistrationStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<body>


<form action="/register" method="post" id="form_register">
    <p> გთხოვთ გაიაროთ რეგისტრაცია </p>
    <label for="firstName"> სახელი: </label>
    <input
            required
            onkeyup="this.value=this.value.toLowerCase();"
            class="form-control" type="text" name="firstName" id="firstName"
            placeholder="ლათინური პატარა ასოები">
    <label for="lastName"> გვარი: </label>
    <input
            required
            class="form-control"
            type="text"
            name="lastName"
            id="lastName"
            placeholder="ლათინური პატარა ასოები">
    <label for="username"> იუზერნეიმი: </label>
    <input
            required
            class="form-control"
            type="text"
            minlength="4"
            name="username"
            id="username"
            placeholder="ლათინური ასოები, ციფრები, _">
    <label for="password"> პაროლი: </label>
    <input
            required
            class="form-control"
            type="password"
            minlength="4"
            maxlength="16"
            name="password"
            id="password"
            placeholder="ლათინური ასოები, ციფრები, _">
    <div class="input">
        <input class="form-check-input" type="checkbox" onclick=change() id="show">
        <label for="show"> პაროლის ჩვენება </label>
    </div>
    <label for="repeatPassword"> პაროლის გამეორება: </label>
    <input
            required
            class="form-control"
            type="password"
            minlength="4"
            maxlength="16"
            name="repeatPassword"
            id="repeatPassword"
            placeholder="გაიმეორეთ პაროლი">
    <label for="mail"> ელ-ფოსტა: </label>
    <input
            required
            class="form-control"
            type="email"
            name="mail"
            id="mail"
            placeholder="მაგ: glekv22@freeuni.edu.ge">
    <input class="btn btn-primary" type="submit" value="რეგისტრაცია">
    <a style="display: block" href="${pageContext.request.contextPath}/JSPs/IdentificationPages/LogInPage.jsp">
        <input style="width: 100%" class="btn btn-warning" type="button" value="არსებული მომხარებლით შესვლა">
    </a>
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
