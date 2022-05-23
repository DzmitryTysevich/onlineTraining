<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Teacher update form</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:set var="user" value="${sessionScope.userById}" scope="request"/>
<form action="access/teacherView/update/users/*" method="POST">
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
                <input type="text" name="firstName" value="${user.firstName}" readonly/>
            </label>
            <div class="errors"></div>
            <br>
        </div>
        <div class="lastName">
            <label><b>Last name</b></label>
            <label>
                <input type="text" name="lastName" value="${user.lastName}" readonly/>
            </label>
            <div class="errors"></div>
            <br>
        </div>
        <div class="email">
            <label><b>Email</b></label>
            <label>
                <input type="email" name="email" value="${user.email}" readonly/>
            </label>
            <div class="errors"></div>
            <br>
        </div>
        <div class="course">
            <label><b>Course</b></label>
            <label>
                <select name="course">
                    <option>${user.course.name}</option>
                </select>
            </label>
            <br><br>
        </div>
        <div class=assessment"">
            <label><b>Assessment</b></label>
            <label>
                <input type="number" name="assessment" min="1" max="10" size="20" value="${user.assessment}"/>
            </label>
            <br><br>
        </div>
        <div class="review">
            <label>
                <textarea name="review" placeholder="Write review..."></textarea>
            </label>
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