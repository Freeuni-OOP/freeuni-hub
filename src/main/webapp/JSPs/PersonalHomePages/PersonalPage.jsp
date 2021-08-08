
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>


<head>
    <title> სალამი ${username} </title>


    <meta name = "viewport" content="width=device-width, initial-scale=1">
    <script src = "https://code.jquery.com/jquery-1.10.2.min.js"> </script>
    <link rel = "script" href = "../../PageScripts/PersonalPageScript.js"/>
    <link href = "https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src = "https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"> </script>
    <link rel = "stylesheet" href = "../../PageStyles/PersonalPageStyle.css"/>
</head>


<body>


<!-- main content ----------------------------------------------->
<div class = "container">
    <div id = "content" class = "content p-0">
        <div class="profile-header">
            <div class="profile-header-cover"></div>

            <div class="profile-header-content">
                <div class="profile-header-img">
                    <img src = "../../Images/UserImages/default.png" alt="" />
                </div>

                <div class="profile-header-info">
                    <h4 class="m-t-sm"> ${username} </h4>
                    <p class="m-b-sm"> თავისუფალი უნივერსიტეტის სტუდენტი </p>
                    <input id="fileupload" type="file" name="fileupload" value="აირჩიე ფაილი"/>
                    <button id="upload-button" onclick = "uploadFile()"> შეცვალე ფოტო </button>
                      <script>
                      async function uploadFile() {
                      let formData = new FormData();
                      formData.append("file", fileupload.files[0]);
                      await fetch('/photo_upload', {
                        method: "POST",
                        body: formData
                      });
                      alert('ფოტო წარმატებით აიტვირთა');
                      }
                      </script>
                </div>
            </div>

            <ul class="profile-header-tab nav nav-tabs">
                <li class="nav-item"><a href="#profile-post" class="nav-link" > პოსტები </a></li>
                <li class="nav-item"><a href="#profile-about" class="nav-link" > ჩემ შესახებ </a></li>
                <li class="nav-item"><a href="#profile-photos" class="nav-link" > ფოტოები </a></li>
                <li class="nav-item"><a href="#profile-friends" class="nav-link" > მეგობრები </a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/JSPs/PersonalHomePages/ProfileInfoUpdate.jsp"
                                        > ინფორმაციის განახლება </a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/JSPs/PersonalHomePages/HomePage.jsp"
                                        > მთავარი გვერდი </a></li>
                <li class="nav-item"><a href = "${pageContext.request.contextPath}/JSPs/IdentificationPages/LogInPage.jsp"
                                        > გასვლა </a></li>
            </ul>
        </div>


        <div class="profile-container">
            <div class="row row-space-20">
                <div class="col-md-8">
                    <div class="tab-content p-0">
                        <div class="tab-pane active show" id = "profile-videos">
                            <!-----------------------------------current page description------------------>
                        </div>
                    </div>
                </div>


                <div id = "personal-info" class = "col-md-4 hidden-xs hidden-sm">
                    <ul class="profile-info-list">
                        <li class="title"> პერსონალური ინფორმაცია </li>
                        <li>
                            <div class="field"> კურსი: </div>
                            <div class="value"> Macs </div>
                        </li>

                        <li>
                            <div class="field">დაბადების თარიღი:</div>
                            <div class="value">2001/31/01</div>
                        </li>

                        <li>
                            <div class="field"> მისამართი: </div>
                            <div class="value">
                                <address class="m-b-0">
                                    ვარკეთილი 3, I მკრ, <br>
                                    კორპუსი - 14, <br>
                                    ბინა - 44 <br>
                                </address>
                            </div>
                        </li>
                        <li>
                            <div class="field">მობილური:</div>
                            <div class="value">
                                (+995) 551 74 40 45
                            </div>
                        </li>
                        <li class="title">მეგობრების სია: </li>
                        <li class="img-list">

                            <!----------------------------------------friends avatars-->


                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


</body>

</html>





















