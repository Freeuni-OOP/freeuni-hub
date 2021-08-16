<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.math.*, java.util.*,Manage.HelperClasses.User" %>

<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("/");
    }
%>

<html>

<head>
    <link rel="stylesheet" href="../../PageStyles/HomePageStyle.css"/>
    <title> სალამი ${username} </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

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
            <img src="${profilePic}" alt="Avatar" height="50" width="50"/>
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
<div class="container">

    <p> ქვემოთ ჩამოთვლილია თქვენთან საველე ლოკაციის გაცვლის მსურველები:</p>

    <%
        List<User> all = (List<User>) request.getSession().getAttribute("requesters");
        for (int i = 0; i < all.size(); i++) {
            int id = all.get(i).getId();
            String userName = all.get(i).getUserName();
            String userFirstName = all.get(i).getUserFirstName();
            String userLastName = all.get(i).getUserLastName();
    %>
    <form class="card" action="/answerSaveleRequest" , method="post">
        <input type="hidden" name="username" value=${username}>
        <input type="hidden" name="requesterName" value=<%=userName%>>
        <div class="card-header">
            <%=userName%>
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">სახელი: <%=userFirstName%>
            </li>
            <li class="list-group-item">გვარი:<%=userLastName%>
            </li>
            <li class="list-group-item">
                <input class="btn btn-dark text-warning text-capitalize" type="submit" name="action" value="accept">
                <input class="btn btn-dark text-warning text-capitalize" type="submit" name="action" value="delete">
            </li>
        </ul>
    </form>
    <%
        }
    %>
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
