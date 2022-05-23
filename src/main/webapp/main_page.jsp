<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Main page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:set var="userName" value="${sessionScope.userName}"/>
<c:set var="userRole" value="${sessionScope.authRole}"/>
<h3>Hello ${userRole} ${userName}</h3>
<div class="topNav">
    <a class="active" href="<c:url value="/"/>">Home</a>
    <a href="registration">Registration</a>
    <a href="access/adminView">Admin_menu</a>
    <a href="access/studentView">Student_menu</a>
    <a href="access/teacherView">Teacher_menu</a>
    <br><br>
    <div class="login-container">
        <form action="login" method="post">
            <label>
                <input type="email" placeholder="Email" name="authEmail">
            </label>
            <label>
                <input type="password" placeholder="Password" name="authPassword">
            </label>
            <button type="submit">Login</button>
        </form>
    </div>
    <div class="logout-container">
        <form action="logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>
</div>
</body>
</html>