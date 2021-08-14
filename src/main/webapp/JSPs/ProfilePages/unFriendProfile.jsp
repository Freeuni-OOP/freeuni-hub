<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link rel="script" href="../../PageScripts/PersonalPageScript.js"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="../../PageStyles/PersonalPageStyle.css"/>
    <title>${profileName}ს პროფილი </title>
</head>

<body>
<div class="container">
    <div id="content" class="content p-0">
        <div class="profile-header">
            <div class="profile-header-cover"></div>

            <div class="profile-header-content">
                <div class="profile-header-img">
                    <img src="../../Images/UserImages/default.png" alt=""/>
                </div>

                <div class="profile-header-info">
                    <h4 class="m-t-sm"> ${profileName} </h4>
                    <p class="m-b-sm"> თავისუფალი უნივერსიტეტის სტუდენტი </p>

                </div>
            </div>

            <ul class="profile-header-tab nav nav-tabs">
                <li class="nav-item"><a href="#profile-post" class="nav-link"> პოსტები </a></li>

                <form action="/addFriend" method="post">
                    <button> დაიმატე მეგობარი</button>
                    <input type="hidden" name="username" value= ${username}>
                    <input type="hidden" name="profileName" value= ${profileName}>
                </form>
                <form action="/changeLocation" method="post">
                    <button> გაუცვალე ლოკაცია</button>
                            <input type="hidden" name="username" value= ${username}>
                            <input type="hidden" name="profileName" value= ${profileName}>
                </form>
                <form action="/blockUser" method="post">
                    <button> დაბლოკე იუზერი</button>
                    <input type="hidden" name="username" value= ${username}>
                    <input type="hidden" name="profileName" value= ${profileName}>
                </form>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/JSPs/PersonalHomePages/HomePage.jsp"
                > მთავარი გვერდი </a></li>
            </ul>
        </div>


        <div class="profile-container">
            <div class="row row-space-20">
                <div class="col-md-8">
                    <div class="tab-content p-0">
                        <div class="tab-pane active show" id="profile-videos">
                            <!-----------------------------------current page description------------------>
                        </div>
                    </div>
                </div>


                <div id="personal-info" class="col-md-4 hidden-xs hidden-sm">
                    <ul class="profile-info-list">
                        <li class="title"> პერსონალური ინფორმაცია</li>

                        <li>
                            <div class="field"> ელ-ფოსტა:</div>
                            <div class="value">
                                ${profileMail}
                            </div>
                        </li>

                        <li>
                            <div class="field"> სახელი:</div>
                            <div class="value"> ${profileFirstName} </div>
                        </li>


                        <li>
                            <div class="field"> გვარი:</div>
                            <div class="value"> ${profileLastName} </div>
                        </li>

                        <li>
                            <div class="field"> ფაკულტეტი:</div>
                            <div class="value"> ${profileFaculty} </div>
                        </li>

                        <li>
                            <div class="field"> კურსი:</div>
                            <div class="value"> ${profileCourse} </div>
                        </li>


                        <li>
                            <div class="field"> სქესი:</div>
                            <div class="value"> ${profileSex} </div>
                        </li>

                        <li>
                            <div class="field"> საველეს ლოკაცია:</div>
                            <div class="value"> ${saveleLocation} </div>
                        </li>

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
