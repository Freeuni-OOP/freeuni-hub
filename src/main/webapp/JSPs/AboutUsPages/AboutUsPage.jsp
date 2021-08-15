<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if(session.getAttribute("username") == null) {
        response.sendRedirect("/");
    }
%>

<html>
<head>
    <title> ჩვენ </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css"
          integrity="sha256-2XFplPlrFClt0bIdPgpz8H7ojnk10H69xRqd9+uTShA=" crossorigin="anonymous"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- -------------javascript included----------------------------------------------------------->

<div class="container">
    <div class="row align-items-center">
        <div class="col-lg-6 col-md-6 order-2 order-md-1 mt-4 pt-2 mt-sm-0 opt-sm-0">
            <div class="row align-items-center">
                <div class="col-lg-6 col-md-6 col-6">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 mt-4 pt-2">
                            <div class="card work-desk rounded border-0 shadow-lg overflow-hidden">
                                <img src="../../Images/Us/ado.jpg" class="img-fluid" alt="Image"/>
                                <div class="img-overlay bg-dark"></div>
                            </div>
                        </div>

                        <div class="col-lg-12 col-md-12 mt-4 pt-2">
                            <div class="card work-desk rounded border-0 shadow-lg overflow-hidden">
                                <img src="../../Images/Us/macho.jpg"
                                     class="img-fluid" alt="Image"/>
                                <div class="img-overlay bg-dark"></div>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="col-lg-6 col-md-6 col-6">
                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <div class="card work-desk rounded border-0 shadow-lg overflow-hidden">
                                <img src="../../Images/Us/xvedo.jpg" class="img-fluid" alt="Image"/>
                                <div class="img-overlay bg-dark"></div>
                            </div>
                        </div>

                        <div class="col-lg-12 col-md-12 mt-4 pt-2">
                            <div class="card work-desk rounded border-0 shadow-lg overflow-hidden">
                                <img src="../../Images/UserImages/default.png" class="img-fluid" alt="Image"/>
                                <div class="img-overlay bg-dark"></div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>


        <div class="col-lg-6 col-md-6 col-12 order-1 order-md-2">
            <div class="section-title ml-lg-5">
                <h5 class="text-custom font-weight-normal mb-3"> ჩვენ შესახებ </h5>
                <h4 class="title mb-4">
                    ოოპი რომ არა, <br>
                    არც კი გეცოდინებოდათ ვინ ვართ
                </h4>
                <p class="text-muted mb-0"> <!-----------------------text------------->
                    ეს საიტი მაქსელების: გიორგი ადიკაშვილის, ლუკა მაჭარაშვილის, ლუკა სამხარაძისა და დავით ხვედელიძის
                    მიერ
                    დიდი ჯაფის საფუძველზე შეიქმნა და გთხოვთ ისიამოვნოთ </p>

                <!-- 4 filed ---------------------------------------------------------------------------->
                <div class="row">
                    <div class="col-lg-6 mt-4 pt-2">
                        <div class="media align-items-center rounded shadow p-3">
                            <i class="fa fa-play h4 mb-0 text-custom"></i>
                            <h6 class="ml-3 mb-0"><a href="https://www.facebook.com/giorgi.adikashvili.7/"
                                                     class="text-dark"> გვერდის ავტორი </a></h6>
                        </div>
                    </div>

                    <div class="col-lg-6 mt-4 pt-2">
                        <div class="media align-items-center rounded shadow p-3">
                            <i class="fa fa-user h4 mb-0 text-custom"> </i>
                            <h6 class="ml-3 mb-0"><a href="../../JSPs/ContactPages/ContactPage.jsp" class="text-dark">
                                კონტაქტი </a></h6>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>


</body>


</html>
