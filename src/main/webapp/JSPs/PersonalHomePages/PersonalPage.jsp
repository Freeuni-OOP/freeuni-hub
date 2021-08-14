<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>


<head>
    <title> სალამი ${username} </title>


    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link rel="script" href="../../PageScripts/PersonalPageScript.js"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="../../PageStyles/PersonalPageStyle.css"/>
</head>


<body>

<!-- main content ----------------------------------------------->
<div class="container">
    <div id="content" class="content p-0">
        <div class="profile-header">
            <div class="profile-header-cover"></div>

            <div class="profile-header-content">
                <div class="profile-header-img">
                    <img id="profile-pic" src="${profilePic}" alt=""/>
                </div>

                <div class="profile-header-info">
                    <h4 class="m-t-sm"> ${username} </h4>
                    <p class="m-b-sm"> თავისუფალი უნივერსიტეტის სტუდენტი </p>
                        <input id="fileupload" type="file" name="fileupload" value="picture" onchange="uploadFile(event)"/>
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
                            document.getElementById("profile-pic").src = imageStr;
                            alert('ფოტო წარმატებით აიტვირთა');
                        }
                    </script>
                </div>
            </div>

            <ul class="profile-header-tab nav nav-tabs">
                <form action="/Posts" method="post">
                    <button> პოსტები</button>
                    <input type="hidden" name="username" value=${username}>
                </form>
                <li class="nav-item"><a href="#profile-about" class="nav-link"> ჩემ შესახებ </a></li>
                <li class="nav-item"><a href="#profile-photos" class="nav-link"> ფოტოები </a></li>
                <form action="/showFriends" , method="post">
                    <input type="hidden" name="username" value= ${username}>
                    <button>მეგობრები</button>
                </form>
                <li class="nav-item"><a
                        href="${pageContext.request.contextPath}/JSPs/PersonalHomePages/ProfileInfoUpdate.jsp"
                > ინფორმაციის განახლება </a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/JSPs/PersonalHomePages/HomePage.jsp"
                > მთავარი გვერდი </a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/JSPs/IdentificationPages/LogInPage.jsp"
                > გასვლა </a></li>
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
                                ${mail}
                            </div>
                        </li>

                        <li>
                            <div class="field"> სახელი:</div>
                            <div class="value"> ${firstname} </div>
                        </li>


                        <li>
                            <div class="field"> გვარი:</div>
                            <div class="value"> ${lastname} </div>
                        </li>

                        <li>
                            <div class="field"> ფაკულტეტი:</div>
                            <div class="value"> ${faculty} </div>
                        </li>

                        <li>
                            <div class="field"> კურსი:</div>
                            <div class="value"> ${course} </div>
                        </li>


                        <li>
                            <div class="field"> სქესი:</div>
                            <div class="value"> ${sex} </div>
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





















