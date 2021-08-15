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
                            href="${pageContext.request.contextPath}/JSPs/SavelePages/TradePage.jsp"
                    >
                        გაცვალე საველე
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
                </li>
            </ul>

        </div>
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

    <p> ქვემოთ ჩამოთვლილია თქვენთან მეგობრების მსურველები:</p>

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

    <%
        ArrayList<User> all = (ArrayList<User>) request.getSession().getAttribute("requesters");
        for (int i = 0; i < all.size(); i++) {
            int id = all.get(i).getId();
            String userName = all.get(i).getUserName();
            String userFirstName = all.get(i).getUserFirstName();
            String userLastName = all.get(i).getUserLastName();
    %>
    <form action="/answerRequest" , method="post">
        <%=userName%>
        <input type="hidden" name="username" value=${username}>
        <input type="hidden" name=<%=id%>  value=<%=userName%>>
        <p class="info">სახელი: <%=userFirstName%>
        </p>
        <p class="info">გვარი:<%=userLastName%>
        </p>
        <input type="submit" name="action" value="accept">
        <input type="submit" name="action" value="delete">
        <p></p>
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
