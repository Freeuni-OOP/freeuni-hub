<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("/");
    } else {
        response.sendRedirect("/Posts");
    }
%>

<html>

<head>
    <link rel="stylesheet" href="../../PageStyles/HomePageStyle.css"/>
    <title> სალამი ${username} </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <%--    <link rel="stylesheet" href="../../PageStyles/PersonalPageStyle.css"/>--%>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
</head>

<body>


<nav class="navbar navbar-expand-lg navbar-light bg-warning">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/JSPs/PersonalHomePages/HomePage.jsp">
            <img style="border-radius: 5px" src="../../favicon.ico" width="50" height="50"
                 class="d-inline-block align-top" alt="">
            <%--            Freeuni Hub--%>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a
                            class="nav-link"
                            href="${pageContext.request.contextPath}/FriendRequests"
                    >
                        მეგობრების თხოვნები
                    </a>
                </li>
                <li class="nav-item">
                    <a
                            class="nav-link"
                            href="${pageContext.request.contextPath}/SaveleRequests"
                    >
                        საველეს გაცვლები
                    </a>
                </li>

                <li class="nav-item">
                    <a
                            class="nav-link"
                            id="delete-account-button"
                            href="#"
                    >
                        გააუქმე ექაუნთი
                    </a>
                    <%--                    <form action="${pageContext.request.contextPath}/DeleteAccount" method="post" id="Delete_Request">--%>
                    <%--                        <input type="hidden" name="username" value=${username}>--%>
                    <%--                        <button> გააუქმე ექაუნთი</button>--%>
                    <%--                    </form>--%>
                </li>
            </ul>

        </div>

        <form class="nav-item"
              style="display: flex; margin-top: 15px; align-items: center; gap: 10px; position: relative"
              action="${pageContext.request.contextPath}/Search" method="post" id="Search_Request">
            <svg style="display: inline-block" xmlns="http://www.w3.org/2000/svg" width="30" height="30"
                 fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
            </svg>
            <input style="display: inline" class="form-control" type="text" name="username" placeholder="მოძებნე უზერი">
        </form>

        <span class="nav-item">
            <a
                    class="nav-link"
                    id="logout-button"
                    href="#"
            >
                გასვლა
            </a>
        </span>
        <a href="${pageContext.request.contextPath}/JSPs/PersonalHomePages/HomePage.jsp" id="personal_photo_home">
            <img style="background: white; border-radius: 5px;" src="${profilePic}" alt="Avatar" height="50"
                 width="50"/>
        </a>
    </div>
</nav>

<script>
    document.getElementById("logout-button").addEventListener('click', async () => {
        await fetch('/logout', {
            method: "POST",
        }).then(res => {
            const a = document.createElement('a');
            a.href = res.url;
            a.click();
        });
    })

    document.getElementById("delete-account-button").addEventListener('click', async () => {
        await fetch('${pageContext.request.contextPath}/DeleteAccount', {
            method: "POST",
        }).then(res => {
            const a = document.createElement('a');
            a.href = res.url;
            a.click();
        });
    })
</script>

<div class="container" style="margin-top: 25px">
    <div class="profile-header"
         style="padding: 20px; margin: 0; background-color: black; border-radius: 10px; display: flex; flex-direction: column; justify-content: flex-end;">

        <div class="profile-header-content" style="display: flex; align-items: center; gap: 10px">
            <div class="profile-header-img" style="background-color: white; border-radius: 10px; width: fit-content; ">
                <img style="width: 175px; object-fit: cover; border-radius: 10px;" id="profile-pic" src="${profilePic}" alt=""/>
            </div>
            <div class="text-white fs-5">
                <p style="text-transform: capitalize"> ${firstname} ${lastname} </p>
                <p> ${username} </p>
                <p> თავისუფალი უნივერსიტეტის სტუდენტი </p>
            </div>
        </div>
    </div>

    <ul class="nav bg-dark mt-2 mb-2 justify-content-center" style="border-radius: 10px;">
        <li class="nav-item">
            <a class="nav-link text-warning" href="/Posts">პოსტები</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-warning" href="/showFriends">მეგობრები</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-warning"
               href="${pageContext.request.contextPath}/JSPs/PersonalHomePages/ProfileInfoUpdate.jsp">
                ინფორმაციის განახლება
            </a>
        </li>
    </ul>

    <div class="card" style="width: 20rem;">
        <div class="card-header">
            პერსონალური ინფორმაცია
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">ელ-ფოსტა: ${mail}</li>
            <li class="list-group-item text-capitalize">სახელი: ${firstname}</li>
            <li class="list-group-item text-capitalize">გვარი: ${lastname}</li>
            <li class="list-group-item">ფაკულტეტი: ${faculty}</li>
            <li class="list-group-item">კურსი: ${course}</li>
            <li class="list-group-item">სქესი: ${sex}</li>
            <li class="list-group-item">საველეს ლოკაცია: ${saveleLocation}</li>
        </ul>
    </div>

</div>


<footer style="position: fixed; bottom: 0; right: 0; left: 0">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a
                            class="nav-link"
                            href="${pageContext.request.contextPath}/JSPs/ContactPages/ContactPage.jsp"
                    >
                        კონტაქტი
                    </a>
                </li>
                <li class="nav-item">
                    <a
                            class="nav-link"
                            href="${pageContext.request.contextPath}/JSPs/AboutUsPages/AboutUsPage.jsp"
                    >
                        ჩვენს შესახებ
                    </a>
                </li>
            </ul>
            <span class="text-light">
                All rights reserved &copy;
            </span>
        </div>
    </nav>
</footer>

</body>

</html>
