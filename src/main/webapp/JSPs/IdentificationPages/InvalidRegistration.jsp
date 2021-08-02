<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <style>
        h2 {
            color: aquamarine;
        }
    </style>
    <title> ხელახალი მცდელობა </title>
</head>


<body>

<p>
    <%
        PrintWriter pw = response.getWriter();
        pw.println("<h1>" + request.getAttribute("problem") + "</h1>");
        pw.println("<h2> სცადეთ ხელახლა </h2>");
    %>
</p>

<form action="register" method="post">
    <label for = "username"> იუზერნეიმი: </label>
    <input type = "text" name = "username" id = "username"> <br/> <br/>
    <label for = "password"> პაროლი: </label>
    <input type = "text" name = "password" id = "password"> <br/> <br/>
    <label for = "firstName"> სახელი: </label>
    <input type = "text" name = "firstName" id = "firstName"> <br/> <br/>
    <label for = "lastName"> გვარი: </label>
    <input type = "text" name = "lastName" id = "lastName"> <br/> <br/>
    <label for = "mail"> ელ-ფოსტა: </label>
    <input type = "text" name = "mail" id = "mail"> <br/> <br/>
    <input type = "submit" value = "რეგისტრაცია"> <br/> <br/>
</form>

</body>


</html>
