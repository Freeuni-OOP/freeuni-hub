<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>


<head>
    <link rel="stylesheet" href="../../PageStyles/RegistrationStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title> ხელახალი მცდელობა </title>
</head>


<body>

<form action="register" method="post" id="form_register">
    <p class="text-danger">${problem}, სცადეთ ხელახლა </p>
        <label for="firstName"> სახელი: </label>
        <input
                required
                class="form-control"
                type="text"
                name="firstName"
                id="firstName"
                size="38"
                placeholder="ლათინური პატარა ასოები"
                oninvalid="this.setCustomValidity('სახელის შეყვანა აუცილებელია')"
                onvalid="this.setCustomValidity('')">
        <label for="lastName"> გვარი: </label>
        <input
                required
                class="form-control"
                type="text"
                name="lastName"
                id="lastName"
                size="40"
                placeholder="ლათინური პატარა ასოები"
                oninvalid="this.setCustomValidity('გვარის შეყვანა აუცილებელია')"
                onvalid="this.setCustomValidity('')">
        <label for="username"> იუზერნეიმი: </label>
        <input
                required
                class="form-control"
                type="text"
                name="username"
                id="username"
                size="33" placeholder="ლათინური ასოები, ციფრები, _"
                oninvalid="this.setCustomValidity('იუზერნეიმის შეყვანა აუცილებელია')"
                onvalid="this.setCustomValidity('')">
        <label for="password"> პაროლი: </label>
        <input
                required
                class="form-control"
                type="password"
                name="password"
                id="password"
                size="37"
                placeholder="ლათინური ასოები, ციფრები, _"
                oninvalid="this.setCustomValidity('პაროლის შეყვანა აუცილებელია')"
                onvalid="this.setCustomValidity('')">
        <div class="input">
            <input class="form-check-input" type="checkbox" onclick=change() id="show">
            <label for="show"> პაროლის ჩვენება </label>
        </div>
        <label for="repeatPassword"> პაროლის გამეორება: </label>
        <input
                required
                class="form-control"
                type="password"
                name="repeatPassword"
                id="repeatPassword"
                size="37"
                placeholder="გაიმეორეთ პაროლი"
                oninvalid="this.setCustomValidity('პაროლი აუცილებლად უნდა გაიმეოროთ')"
                onvalid="this.setCustomValidity('')">
        <label for="mail"> ელ-ფოსტა: </label>
        <input
                required
                class="form-control"
                type="text"
                name="mail"
                id="mail"
                size="35"
                placeholder="მაგ: glekv22@freeuni.edu.ge"
                oninvalid="this.setCustomValidity('ელ-ფოსტის შეყვანა აუცილებელია')"
                onvalid="this.setCustomValidity('')">
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
