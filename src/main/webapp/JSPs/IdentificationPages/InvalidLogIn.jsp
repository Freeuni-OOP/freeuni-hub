<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if(session.getAttribute("username") != null) {
        response.sendRedirect("../PersonalHomePages/HomePage.jsp");
    }
%>

<html>

<head>
    <title> წარუმატებელი ავტორიზაცია </title>
    <link rel="stylesheet" href="../../PageStyles/LogInStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>


<body>

<form action="/login" method="post" id="form_login">
    <p class="text-danger"> იუზერნეიმი ან პაროლი არასწორია, გთხოვთ სცადოთ ხელახლა </p>
    <label for="username"> იუზერნეიმი: </label>
    <input
            required
            minlength="4"
            maxlength="16"
            class="form-control"
            type="text"
            name="username"
            id="username"
    >
    <label for="password"> პაროლი: </label>
    <input
            required
            minlength="4"
            maxlength="16"
            class="form-control"
            type="password"
            name="password"
            id="password"
    >
    <div class="input">
        <input
                class="form-check-input"
                type="checkbox"
                onclick=change()
                id="show"
        >
        <label for="show"> პაროლის ჩვენება </label>
    </div>
    <input class="btn btn-primary" type="submit" value="შესვლა">
    <a style="display: block;" href="${pageContext.request.contextPath}/JSPs/IdentificationPages/RegistrationPage.jsp">
        <input style="width: 100%" type="button" class="btn btn-warning" value="ახალი მომხმარებლის რეგისტრაცია">
    </a>
</form>


<!-- ------------------------------------------------------------------------java script -->
<script>
    document.getElementById('form_login').addEventListener('submit', async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const formProps = Object.fromEntries(formData);

        await fetch('/login', {
            method: "POST",
            body: new URLSearchParams(formProps),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        }).then(res => {
            const a = document.createElement('a');
            a.href = res.url;
            a.click();
        });
    });

    function change() {
        let field = document.getElementById("password");
        if (field.type === "password") field.type = "text";
        else field.type = "password";
    }
</script>

</body>


</html>