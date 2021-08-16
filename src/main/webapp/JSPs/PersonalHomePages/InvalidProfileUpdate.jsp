
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
                    <link rel="stylesheet" href="../../PageStyles/ProfileUpdateStyle.css">
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
                            <img class="profile-pic" style="background: white; border-radius: 5px;" src="${profilePic}" alt="Avatar" height="50"
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
                <div class="container">

                    <script>

                        async function fileToBase64(file) {
                            return new Promise((resolve, reject) => {
                                const reader = new FileReader()
                                reader.readAsDataURL(file)
                                reader.onload = () => resolve(reader.result)
                                reader.onerror = (e) => reject(e)
                            })
                        }


                        async function uploadFile(event) {
                            // document.getElementById('upload-form').submit();
                            const file = event.srcElement.files[0];
                            const imageStr = await fileToBase64(file);
                            const formData = new FormData();
                            formData.append("img", imageStr);
                            await fetch('/photo_upload', {
                                method: "POST",
                                body: new URLSearchParams({
                                    img: imageStr
                                }),
                                headers: {
                                    'Content-Type': 'application/x-www-form-urlencoded',
                                }
                            });

                            [...document.getElementsByClassName("profile-pic")].forEach(o => o.src =imageStr);
                        }
                    </script>

                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-12 col-lg-10 col-xl-8 mx-auto">
                                <div class="my-4">
                                    <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home"
                                               role="tab" aria-controls="home" aria-selected="false"> ინფორმაციის განახლება </a>

                                            <p class="text-danger">${problems}</p>
                                        </li>
                                    </ul>


                                    <form id="update_form" method="post" action="/update"> <%---------update form------%>
                                        <div class="row mt-5 align-items-center">
                                            <div class="col-md-3 text-center mb-5">
                                                <div class="avatar avatar-xl">
                                                    <img src="${profilePic}" alt="..." style="border-radius: 10px; background-color: white"
                                                         class="profile-pic avatar-img"/>
                                                    <input class="btn btn-dark text-warning mt-2" style="width: 200px" id="fileupload" type="file" name="fileupload" value="picture" onchange="uploadFile(event)"/>

                                                </div>
                                            </div>
                                            <div class="col">
                                                <div class="row align-items-center">
                                                    <div class="col-md-7">
                                                        <h4 class="mb-1"> ${username} </h4>
                                                        <p class="small mb-3"><span class="badge badge-dark"> ფრიუნი </span></p>
                                                    </div>
                                                </div>
                                                <div class="row mb-4">
                                                    <div class="col-md-7">
                                                        <p class="text-muted">
                                                            ფრიუნი ჩემი სტილია and other hilarious
                                                            jokes <%----------here can be used jsp jstl--------%>
                                                        </p>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        <hr class="my-4"/>
                                        <div class="form-row">

                                            <div class="form-group">
                                                <label for="user_name"> ახალი იუზერნეიმი (შეგიძლიათ იგივე დატოვოთ, უბრალოდ იგივე
                                                    იუზერნეიმი
                                                    შეიყვანეთ)
                                                </label>
                                                <input type="text" id="user_name" name="user_name"
                                                       class="form-control" placeholder="მხოლოდ ლათინური ასოები, ციფრები, _"/>
                                            </div><br>


                                            <div class="form-group">
                                                <label for="sex"> სქესი </label>
                                                <select id="sex" class="form-control" name="sex">
                                                    <option selected=""> secret</option>
                                                    <option> male</option>
                                                    <option> female</option>
                                                </select>
                                            </div> <br>
                                            <div class="form-group">
                                                <label for="saveleLocation"> საველეს ლოკაცია </label>
                                                <select id="saveleLocation" class="form-control" name="saveleLocation">
                                                    <option selected=""> აირჩიე</option>
                                                    <option> Qvabisxevi2</option>
                                                    <option> Qvabisxevi3</option>
                                                    <option> Fari2</option>
                                                    <option> Fari3</option>
                                                    <option> Baxmaro2</option>
                                                    <option> Baxmaro3</option>
                                                    <option> Already passed</option>
                                                </select>
                                            </div>
                                        </div> <br>

                                        <%------------choose course------------%>
                                        <div class="form-row">

                                            <div class="form-group">
                                                <label for="inputCompany5"> ფაკულტეტი </label>
                                                <input type="text" class="form-control" id="inputCompany5" name="faculty"
                                                       placeholder="მაგ: macs"/>
                                            </div><br>

                                            <div class="form-group col-md-4">
                                                <label for="inputState5"> კურსი </label>
                                                <select id="inputState5" class="form-control" name="course">
                                                    <option selected=""> აირჩიე...</option>
                                                    <option> I</option>
                                                    <option> II</option>
                                                    <option> III</option>
                                                    <option> IV</option>
                                                    <option> IV+</option>
                                                </select>
                                            </div>

                                        </div>


                                        <hr class="my-4"/>
                                        <div class="row mb-4">
                                            <div class="col-md-6">

                                                <div class="form-group">
                                                    <label for="inputPassword5"> ძველი პაროლი </label>
                                                    <input type="password" class="form-control" id="inputPassword5" name="oldPassword"/>
                                                </div>
                                                <div class="form-group">
                                                    <label for="inputPassword6"> ახალი პაროლი </label>
                                                    <input type="password" class="form-control" id="inputPassword6" name="newPassword"/>
                                                </div>
                                                <div class="form-group">
                                                    <label for="inputPassword7"> პაროლის დადასტურება </label>
                                                    <input type="password" class="form-control" id="inputPassword7"
                                                           name="repeatedPassword"/>
                                                </div>


                                            </div>
                                            <div class="col-md-6">
                                                <p class="mb-2"> პაროლის მოთხოვნები </p>
                                                <p class="small text-muted mb-2"> პაროლის განახლებამდე გაითვალისწინეთ შემდეგი
                                                    მოთხოვნები: </p>
                                                <ul class="small text-muted pl-4 mb-0">
                                                    <li> 4-16 სიგრძის</li>
                                                    <li> მინიმუმ 1 დიდი ასო და მინიმუმ 1 ციფრი უნდა ერიოს</li>
                                                    <li> შეგიძლიათ მხოლოდ ლათინური ასოების, ციფრებისა და ქვედა ტირეს("_") გამოყენება
                                                    </li>
                                                    <li> არ შეიძლება იყოს იგივე რაც წინა</li>
                                                </ul>
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-dark text-warning"> შენახვა</button>
                                    </form>

                                </div>
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
