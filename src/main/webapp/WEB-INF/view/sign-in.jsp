<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Login Page</title>
    <link href="<c:url value="/resources/css/sign-in.css" />" rel="stylesheet">
</head>

<body>
<div class="container">
    <form method="POST" action="${contextPath}/login" class="form-sign-in">
        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span class="message"> ${message}</span>
            <input name="username" value="${cookieUserName}" type="text" class="form-control" placeholder="Username"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="button" type="submit">Log In</button>
            <p class="text-left"><a href="${contextPath}/sign-up" style="text-decoration: none;">Registration</a></p>
        </div>
    </form>
</div>
</body>
</html>
