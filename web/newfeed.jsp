<%-- 
    Document   : newfeed
    Created on : Sep 19, 2020, 12:45:19 AM
    Author     : dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>News feed</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <%--    <script type="text/javascript" src="js/main.js"></script>--%>
</head>

<body>


<c:if test="${param.page == null}">
    <c:set var="page" value="${1}"/>
</c:if>
<c:if test="${param.page != null}">
    <c:set var="page" value="${param.page}"/>
</c:if>
<c:set var="user" value="${sessionScope.USER_DTO}"/>
<c:set var="articleError" value="${requestScope.ARTICLE_ERROR}"/>
<jsp:include page="navbar.jsp">
    <jsp:param name="userId" value="${user.userId}"/>
    <jsp:param name="fullName" value="${user.fullName}"/>
    <jsp:param name="roleId" value="${user.roleId.roleId}"/>
</jsp:include>

<c:if test="${user.roleId.roleId == 1}">
    <form style="margin: 20px 20%; padding: 20px" class="card" action="MainController?btnAction=Post article"
          method="post" enctype="multipart/form-data">
        <div class="card-header">
            <h6>Ban dang nghi gi?...</h6>
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput1">Title</label>
            <input type="text" name="title" maxlength="50" value="${requestScope.ARTICLE.title}" class="form-control"
                   id="exampleFormControlInput1" placeholder="title">
        </div>
        <p>${articleError.titleError}</p></br>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Description</label>
            <input type="text" name="description" maxlength="4000" value="${requestScope.ARTICLE.description}"
                   class="form-control" id="exampleFormControlTextarea1" rows="3"></input>
        </div>
        <p>${articleError.descriptionError}</p></br>
        <input type="file" name="imagePath" value="${requestScope.ARTICLE.imagePath}" class="btn btn-light"/></br>
        <p>${articleError.imageError}</p></br>
        <input class="btn btn-primary" type="submit" value="Post article"/></br>
        <p>${articleError.emptyError}</p></br>
    </form>
</c:if>

<c:set var="listArticle" value="${requestScope.LIST_ARTICLE}"/>
<c:if test="${listArticle != null}">
    <c:forEach var="article" varStatus="counter" items="${listArticle}">
        <a class="card" style="margin: 20px 20%"
           href="MainController?btnAction=ShowDetail&articleId=${article.articleId}">
            <div class="card-header">
                <h4>${article.userId.fullName}</h4>
                <p>${article.date}</p>
            </div>
            <div class="card-body">
                <h5 class="card-title">${article.title}</h5>
                <p class="card-text">${article.description}</p>
                <c:if test="${article.imagePath.length() > 0}">
                    <img src="images/${article.imagePath}" style="width:100%"></img></br>
                </c:if>
            </div>
        </a>
        </div>
    </c:forEach>
    <c:if test="${listArticle != null}">

        <c:if test="${listArticle.size() == 0}">
            <div class="card" style="margin: 20px 20%">
                <p class="card-body">There is no Article!!!</p>
            </div>
        </c:if>
    </c:if>

    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">

            <form action="MainController">
                <input type="hidden" name="searchArticle" value="${param.searchArticle}"/>
                <c:set var="page" value="${requestScope.PAGE}"/>
                <input type="hidden" value="${page}" name="page"/>
                <input type="submit" value="Previous" name="btnAction" class="page-link"/>
            </form>
            </li>
            <li class="page-item"><a class="page-link" href="#">${page}</a></li>
            <li class="page-item">
            <li class="page-item">
                    <%--                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>--%>
                <form action="MainController">
                    <input type="hidden" name="searchArticle" value="${param.searchArticle}"/>
                    <c:set var="page" value="${requestScope.PAGE}"/>
                    <input type="hidden" value="${page}" name="page"/>
                    <input type="submit" value="Next" name="btnAction" class="page-link"/>
                </form>
            </li>
        </ul>
    </nav>
</c:if>

<script>
    if (window.history.replaceState) {
        window.history.replaceState(null, null, window.location.href);
    }
</script>
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
