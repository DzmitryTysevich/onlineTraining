<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Student update form</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:set var="user" value="${sessionScope.userByEmail}" scope="request"/>
<form action="access/studentView/update" method="POST">
    <div class="updateContainer">
        <h1>Update form for ${user.firstName} ${user.lastName}</h1>
        <p>Please fill in this form to update a data.</p>
        <hr>
        <div class="id">
            <label><b>ID</b></label>
            <label>
                <input type="number" name="id" value="${user.id}" readonly/>
            </label>
            <br><br>
        </div>
        <div class="firstName">
            <label><b>First name</b></label>
            <label>
                <input type="text" name="firstName" value="${user.firstName}" required/>
            </label>
            <div class="errors"></div>
            <br>
        </div>
        <div class="lastName">
            <label><b>Last name</b></label>
            <label>
                <input type="text" name="lastName" value="${user.lastName}" required/>
            </label>
            <div class="errors"></div>
            <br>
        </div>
        <div class="email">
            <label><b>Email</b></label>
            <label>
                <input type="email" name="email" value="${user.email}" required/>
            </label>
            <div class="errors"></div>
            <br>
        </div>
        <div class="inputPassword">
            <label><b>Password</b></label>
            <label for="password">
                <input type="password" name="password" id="password" value="${user.password}" placeholder="Enter password"/>
            </label>
            <div class="errors"></div>
            <br>
        </div>
        <div class="confirmPassword">
            <label><b>Confirm password</b></label>
            <label for="confPassword">
                <input type="password" name="confPassword" id="confPassword" placeholder="Confirm password" required/>
            </label>
            <div class="errors"></div>
            <br>
        </div>
        <div class="course">
            <label><b>Course</b></label>
            <label>
                <select name="course">
                    <option></option>
                    <option>MATH</option>
                    <option>BIOLOGY</option>
                    <option>ENGLISH</option>
                    <option>CHEMISTRY</option>
                </select>
            </label>
            <label> current: ${user.course.name}</label>
            <br><br>
        </div>
        <div class="buttonUpdate">
            <input type="submit" value="Submit"/>
        </div>
    </div>
</form>
<script src="js/script.js"></script>
</body>
</html>