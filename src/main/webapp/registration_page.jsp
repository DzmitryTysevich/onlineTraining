<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Registration form</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="registration" method="POST">
    <div class="regContainer">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>
        <div class="role">
            <label><b>Role</b></label>
            <label for="role">
                <select name="role" id="role">
                    <c:forEach var="roles" items="${sessionScope.roles}">
                        <option><c:out value="${roles}"/></option>
                    </c:forEach>
                </select>
            </label>
            <br><br>
        </div>
        <div class="firstName">
            <label><b>First name</b></label>
            <label for="firstName">
                <input type="text" name="firstName" id="firstName" placeholder="Enter first name" required/>
            </label>
            <br><br>
        </div>
        <div class="lastName">
            <label><b>Last name</b></label>
            <label for="lastName">
                <input type="text" name="lastName" id="lastName" placeholder="Enter last name" required/>
            </label>
            <br><br>
        </div>
        <div class="email">
            <label><b>Email</b></label>
            <label for="email">
                <input type="email" name="email" id="email" placeholder="Enter email" required/>
            </label>
            <br><br>
        </div>
        <div class="initPassword">
            <label><b>Password</b></label>
            <label for="password">
                <input type="password" name="password" id="password" placeholder="Enter password" required/>
            </label>
            <br><br>
        </div>
        <div class="confirmPassword">
            <label><b>Confirm password</b></label>
            <label for="confPassword">
                <input type="password" name="confPassword" id="confPassword" placeholder="Enter password" required/>
            </label>
            <br><br>
        </div>
        <div class="submitButton">
            <input type="submit" value="Submit" class="buttonReg"/>
        </div>
    </div>
    <div class="signInContainer">
        <p>Already have an account? <a href="<c:url value="/"/>">Sign in</a>.</p>
    </div>
</form>
<script src="js/script.js"></script>
</body>
</html>