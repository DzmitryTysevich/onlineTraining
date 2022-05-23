<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Update form</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:set var="user" value="${sessionScope.userById}" scope="request"/>
<form action="access/adminView/update/users/*" method="POST">
    <div class="updateContainer">
        <h1>Update form for ${user.firstName} ${user.lastName}</h1>
        <p>Please fill in this form to update a data.</p>
        <hr>
        <div class="id">
            <label><b>ID</b></label>
            <input type="number" name="id" value="${user.id}" readonly/>
            <br><br>
        </div>
        <div class="role">
            <label><b>Role</b></label>
            <select name="role" required>
                <c:forEach var="roles" items="${sessionScope.roles}">
                    <option><c:out value="${roles}"/></option>
                </c:forEach>
            </select>
            <label> current: ${user.role}</label>
            <br><br>
        </div>
        <div class="firstName">
            <label><b>First name</b></label>
            <input type="text" name="firstName" value="${user.firstName}" required/>
            <div class="errors"></div>
            <br>
        </div>
        <div class="lastName">
            <label><b>Last name</b></label>
            <input type="text" name="lastName" value="${user.lastName}" required/>
            <div class="errors"></div>
            <br>
        </div>
        <div class="email">
            <label><b>Email</b></label>
            <input type="email" name="email" value="${user.email}" required/>
            <div class="errors"></div>
            <br>
        </div>
        <div class="inputPassword">
            <label><b>Password</b></label>
            <input type="password" name="password" id="password" value="${user.password}" placeholder="Enter password"/>
            <div class="errors"></div>
            <br>
        </div>
        <div class="confirmPassword">
            <label><b>Confirm password</b></label>
            <input type="password" name="confPassword" id="confPassword" placeholder="Confirm password" required/>
            <div class="errors"></div>
            <br>
        </div>
        <div class="course">
            <label><b>Course</b></label>
            <select name="course">
                <option></option>
                <option>MATH</option>
                <option>BIOLOGY</option>
                <option>ENGLISH</option>
                <option>CHEMISTRY</option>
            </select>
            <label> current: ${user.course.name}</label>
            <br><br>
        </div>
        <div class=assessment"">
            <label><b>Assessment</b></label>
            <input type="number" name="assessment" min="1" max="10" size="20" value="${user.assessment}"/>
            <br><br>
        </div>
        <div class="review">
            <textarea name="review" placeholder="Write review..."></textarea>
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