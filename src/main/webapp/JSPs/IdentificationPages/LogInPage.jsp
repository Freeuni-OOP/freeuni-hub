<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title> სალამი! </title>
    <link rel="stylesheet" href="../../PageStyles/LogInStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>


<body onunload="disableBackButton()">
<script type="text/javascript">
    function disableBackButton() {
        window.history.forward();
    }

    setTimeout("disableBackButton()", 0);
</script>

<form action="/login" method="post" id="form_login">
    <h1> მოგესალმებით ფრიუნიჰაბზე! </h1>
    <p> გთხოვთ შეიყვანოთ თქვენი ინფორმაცია </p>
    <label for="username"> იუზერნეიმი: </label>
    <input class="form-control" type="text" name="username" id="username" size="25"> <br/> <br/>
    <label for="password"> პაროლი: </label>
    <input class="form-control" type="password" name="password" id="password" size="30"> <br/> <br/>
    <input class="form-check-input" type="checkbox" onclick=change() id="show">
    <label for="show"> პაროლის ჩვენება </label> <br> <Br>
    <label> <input type="submit" value="შესვლა"> </label> <br/> <br/>
    <a href="${pageContext.request.contextPath}/JSPs/IdentificationPages/RegistrationPage.jsp">ახალი მომხმარებლის რეგისტრაცია </a>
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
