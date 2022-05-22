<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Error page</title>
</head>
<body>
<c:set var="servletName" value="${sessionScope.servletName}" scope="request"/>
<c:set var="statusCode" value="${sessionScope.statusCode}" scope="request"/>
<c:set var="exception" value="${sessionScope.exception}" scope="request"/>
<c:set var="requestUri" value="${sessionScope.requestUri}" scope="request"/>
<c:set var="auth" value="${sessionScope.authEmail}" scope="session"/>

<c:if test="${servletName == null}">
    <c:set var="servletName" value="Unknown" scope="request"/>
</c:if>
<c:if test="${requestUri == null}">
    <c:set var="requestUri" value="Unknown" scope="request"/>
</c:if>
<c:choose>
    <c:when test="${auth == null}">
        <h2>Access denied!</h2>
        <p>Please return to the <a href="<c:url value="/"/>">Home Page.</a></p>
    </c:when>
    <c:when test="${exception == null && statusCode == null}">
        <h2>Error information is missing</h2>
        <p>Please return to the <a href="<c:url value="/"/>">Home Page.</a></p>
    </c:when>
    <c:when test="${statusCode != null}">
        <h2>The status code : ${statusCode}</h2>
        <p>Please return to the <a href="<c:url value="/"/>">Home Page.</a></p>
    </c:when>
    <c:otherwise>
        <h2>Error information</h2>
        <p>Servlet Name : ${servletName}</p>
        <p>Exception Type : ${exception.getClass.getName}</p>
        <p>The request URI: ${requestUri}</p>
        <p>The exception message: ${exception.getMessage}</p>
    </c:otherwise>
</c:choose>
</body>
</html>