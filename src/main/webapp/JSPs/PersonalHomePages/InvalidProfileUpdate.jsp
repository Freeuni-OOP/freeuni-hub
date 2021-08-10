
<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<html>


<head>
    <title> განაახლე ინფორმაცია </title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link rel = "stylesheet" href = "../../PageStyles/ProfileUpdateStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
</head>




<body>



<div class="container">
    <div class="row justify-content-center">
        <div class="col-12 col-lg-10 col-xl-8 mx-auto">
            <div class="my-4">
                <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home"
                           role="tab" aria-controls="home" aria-selected="false"> ინფორმაციის განახლება </a>

                        <h3> ${problems} <!-- message problems --> </h3>
                    </li>
                </ul>


                <form id = "update_form" method = "post" action = "/update"> <%---------update form------%>
                    <div class="row mt-5 align-items-center">
                        <div class="col-md-3 text-center mb-5">
                            <div class="avatar avatar-xl">
                                <img src="../../Images/UserImages/default.png" alt="..." class="avatar-img rounded-circle" />
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
                                        ფრიუნი ჩემი სტილია and other hilarious jokes <%----------here can be used jsp jstl--------%>
                                    </p>
                                </div>

                            </div>
                        </div>
                    </div>

                    <hr class="my-4" />
                    <div class="form-row">

                        <div class="form-group col-md-6">
                            <label for="user_name"> ახალი იუზერნეიმი (შეგიძლიათ იგივე დატოვოთ, უბრალოდ იგივე იუზერნეიმი შეიყვანეთ) </label>
                            <input type="text" id="user_name" name = "user_name"
                                   class="form-control" placeholder="მხოლოდ ლათინური ასოები, ციფრები, _" />
                        </div>


                        <div class="form-group col-md-6">
                            <label for="sex"> სქესი </label>
                            <select id="sex" class="form-control" name = "sex">
                                <option selected = ""> secret </option>
                                <option> male </option>
                                <option> female </option>
                            </select>
                        </div>

                    </div>

                    <br> <%-- skip line for more beauty-------------------------------------------------%>

                    <%------------choose course------------%>
                    <div class="form-row">

                        <div class="form-group col-md-6">
                            <label for="inputCompany5"> ფაკულტეტი </label>
                            <input type="text" class="form-control" id="inputCompany5" name = "faculty" placeholder="მაგ: macs" />
                        </div>

                        <div class="form-group col-md-4">
                            <label for="inputState5"> კურსი </label>
                            <select id="inputState5" class="form-control" name = "course">
                                <option selected = ""> აირჩიე...</option>
                                <option> I </option>
                                <option> II </option>
                                <option> III </option>
                                <option> IV </option>
                                <option> IV+ </option>
                            </select>
                        </div>

                    </div>



                    <hr class="my-4" />
                    <div class="row mb-4">
                        <div class="col-md-6">

                            <div class="form-group">
                                <label for="inputPassword5"> ძველი პაროლი </label>
                                <input type="password" class="form-control" id="inputPassword5" name = "oldPassword"/>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword6"> ახალი პაროლი </label>
                                <input type="password" class="form-control" id="inputPassword6" name = "newPassword"/>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword7"> პაროლის დადასტურება </label>
                                <input type="password" class="form-control" id="inputPassword7" name = "repeatedPassword"/>
                            </div>


                        </div>
                        <div class="col-md-6">
                            <p class="mb-2"> პაროლის მოთხოვნები </p>
                            <p class="small text-muted mb-2"> პაროლის განახლებამდე გაითვალისწინეთ შემდეგი მოთხოვნები: </p>
                            <ul class="small text-muted pl-4 mb-0">
                                <li> 4-16 სიგრძის</li>
                                <li> მინიმუმ 1 დიდი ასო და მინიმუმ 1 ციფრი უნდა ერიოს </li>
                                <li> შეგიძლიათ მხოლოდ ლათინური ასოების, ციფრებისა და ქვედა ტირეს("_") გამოყენება </li>
                                <li> არ შეიძლება იყოს იგივე რაც წინა </li>
                            </ul>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary"> შენახვა </button>
                </form>

            </div>
        </div>
    </div>
</div>

</body>

</html>
