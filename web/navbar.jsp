<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : navbar
    Created on : Sep 26, 2020, 11:40:52 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>JSP Page</title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
    <c:set var="user" value="${sessionScope.USER_DTO}"/>
    <h1>Welcome ${param.fullName}</h1>
    <a href="NewsFeedController" class="navbar-brand">Home</a>
    <c:set var="tmpRole" value="1"/>
    <c:if test="${param.roleId.equals(tmpRole)}">
        <a href="MainController?userId=${param.userId}&btnAction=ShowNotification" class="navbar-brand">Notification</a>
    </c:if>
    <c:set value="${requestScope.ARTICLE_ERROR}" var="articleError"/>
    <a href="MainController?btnAction=Logout" class="navbar-brand">Logout</a>

    <c:if test="${param.page == null}">
        <c:set var="page" value="${1}"/>
    </c:if>
    <c:if test="${param.page != null}">
        <c:set var="page" value="${param.page}"/>
    </c:if>

    <form action="NewsFeedController" class="form-inline">
        <input type="text" name="searchArticle" maxlength="30" value="${param.searchArticle}" class="form-control mr-sm-2" placeholder="Search" aria-label="Search"/>
        <input type="hidden" value="${page}" name="page"/>
        <input type="submit" value="Search" name="btnAction" class="btn btn-outline-success my-2 my-sm-0"/>
    </form>
</nav>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
</body>
</html>
