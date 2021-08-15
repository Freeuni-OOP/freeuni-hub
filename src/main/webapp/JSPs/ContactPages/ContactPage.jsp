<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("/");
    }
%>

<html>


<head>
    <title> კონტაქტი </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link rel="stylesheet" href="../../PageStyles/ContactPageStyle.css">
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

<div class="container" style="margin-top: 50px">
    <div class="row">
        <div class="col-lg-6">
            <div class="section-title">
                <h2> კონტაქტი </h2>
                <p>შეკითხვების შემთხვევაში აუცილებლად მოგვწერეთ</p>
            </div>
        </div>
    </div>
    <div class="row flex-row-reverse">
        <div class="col-md-7 col-lg-8 m-15px-tb">

            <div class="contact-form">
                <form action="mailto:dkhve19@freeuni.edu.ge" method="post"
                      class="contactform contact_form" id="contact_form" enctype="text/plain">
                    <div class="returnmessage valid-feedback p-15px-b"
                         data-success="Your message has been received, We will contact you soon."></div>
                    <div class="empty_notice invalid-feedback p-15px-b">
                        <span>გთხოვთ შეავსოთ მოთხოვნილი ველები</span></div>
                    <div class="row">

                        <!-------------------------------------message------------->

                        <%--                            <input type="submit" name = "გაგზავნა">--%>
                        <div class="col-md-12">
                            <div class="send">
                                <a id="send_message" class="px-btn theme"
                                   href="mailto:gadik19@freeuni.edu.ge"> <span> მოგვწერე ფრიუნის ფოსტაზე </span> <i
                                        class="arrow"></i></a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="col-md-5 col-lg-4 m-15px-tb">
            <div class="contact-name">
                <h5>ჩვენი ელ-ფოსტები</h5>
                <p>dkhve19@freeuni.edu.ge</p>
                <p>gadik19@freeuni.edu.ge</p>
                <p>lsamk19@freeuni.edu.ge</p>
                <p>lmach19@freeuni.edu.ge</p>
            </div>


            <div class="contact-name">
                <h5>მობილური</h5> <!------------------------ეს დასამთავრებელია,
                                              ყველას ნომერია შესაყვანი ან შეგვიძლია დავიკიდოთ-->
                <p>ლუკა მაჭარაშვილი: 577-35-19-17</p>
                <p>გიორგი ადიკაშვილი: 551-74-40-45</p>
                <p>დავით ხვედელიძე: 599-12-73-19</p>
            </div>


            <div class="social-share nav">
                <a class="dribbble" href="https://www.facebook.com/d.xvedelidze">
                    <img src="../../Images/Us/xvedo2.jpg" alt="fb" width="50" height="50" id="fb_image1">
                </a>

                <a class="dribbble" href="https://www.facebook.com/giorgi.adikashvili.7">
                    <img src="../../Images/Us/ado.jpg" alt="fb" width="50" height="50" id="fb_image2">
                </a>

                <a class="dribbble" href="https://www.facebook.com/luka.macharashvili.779">
                    <img src="../../Images/Us/macho.jpg" alt="fb" width="50" height="50" id="fb_image3">
                </a>

                <a class="dribbble" href="https://www.facebook.com/LukeSamkharadze">
                    <img src="../../Images/UserImages/default.png" alt="fb" width="50" height="50" id="fb_image4">
                </a>

            </div>
        </div>
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
