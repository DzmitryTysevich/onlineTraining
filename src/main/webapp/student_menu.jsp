<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="usersTable">
    <h1>Online training student data</h1>
    <hr>
    <table>
        <tr>
            <th>ID</th>
            <th>ROLE</th>
            <th>FIRST NAME</th>
            <th>LAST NAME</th>
            <th>EMAIL</th>
            <th>COURSE ID</th>
            <th>COURSE NAME</th>
            <th>ASSESSMENT</th>
            <th>REVIEW</th>
        </tr>
        <c:set var="user" value="${sessionScope.user}"/>
        <tr>
            <td>${user.id}</td>
            <td>${user.role}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.course.id}</td>
            <td>${user.course.name}</td>
            <td>${user.assessment}</td>
            <td>${user.review}</td>
            <td>
                <form method="post" action="access/studentView/delete">
                    <button type="submit" id="submitButton" onclick="return confirm('Are you sure?')">Delete
                    </button>
                </form>
            </td>
            <td>
                <form method="get" action="access/studentView/update">
                    <button type="submit" id="updateButton" onclick="return confirm('Are you sure?')">Update
                    </button>
                </form>
            </td>
        </tr>
    </table>
</div>
<br><br>
<hr>
<div class="returnToUsersPage">
    <p><a href="<c:url value="/registration"/>">Return to registration page</a></p>
</div>
<div class="returnToMainPage">
    <p><a href="<c:url value="/"/>">Return to main page</a></p>
</div>
</body>
</html>