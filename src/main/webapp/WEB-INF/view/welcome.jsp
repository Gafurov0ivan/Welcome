<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome</title>
    <link href="<c:url value="/resources/css/welcome.css" />" rel="stylesheet">
</head>
<body>
<div class="title">
    <h1>${dayTime}, ${pageContext.request.userPrincipal.name}!</h1>
</div>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <button class="button" onclick="document.forms['logoutForm'].submit()">Quit</button>
    </c:if>
</div>
</body>
</html>

