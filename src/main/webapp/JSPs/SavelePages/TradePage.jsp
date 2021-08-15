<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if(session.getAttribute("username") == null) {
        response.sendRedirect("/");
    }
%>

<html>

<head>
    <title> გაცვალე ლოკაცია </title>
    <style>

    </style>
</head>


<body>
<h1> აირჩიეთ თქვენთვის სასურველი ლოკაცია </h1>

<label for="have"> მაქვს </label>
<input type="text" name="have" id="have">

<label for="want"> მინდა </label>
<input type="text" name="want" id="want">
</body>

</html>