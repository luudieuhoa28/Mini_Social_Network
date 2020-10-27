<%-- 
    Document   : article_detail
    Created on : Sep 20, 2020, 1:36:28 PM
    Author     : dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <title>Article Detail Page</title>

</head>
<body>
<script type="text/javascript" src="js/main.js"></script>
<c:set var="article" value="${requestScope.ARTICLE}"/>
<c:set var="numOfLike" value="${requestScope.NUM_OF_LIKE}"/>
<c:set var="numOfDislike" value="${requestScope.NUM_OF_DISLIKE}"/>
<c:set var="listComment" value="${requestScope.LIST_COMMENT}"/>
<c:set var="user" value="${sessionScope.USER_DTO}"/>
<jsp:include page="navbar.jsp">
    <jsp:param name="userId" value="${user.userId}"/>
    <jsp:param name="fullName" value="${user.fullName}"/>
    <jsp:param name="roleId" value="${user.roleId.roleId}"/>
</jsp:include>
<div class="card" style="margin: 20px 20%">
    <div class="card-header">
        <h4>${article.userId.fullName}</h4>
        <p>${article.date}</p>
        <c:if test="${sessionScope.USER_DTO.userId.equals(article.userId.userId) || sessionScope.USER_DTO.roleId.roleId == 2}">
            <button type="button" class="btn btn-danger"
                    onclick="showDialogDeleteArticle('${article.userId.userId}', '${article.articleId}')">
                Delete Article
            </button>
        </c:if>
    </div>
    <div class="card-body">
        <h5 class="card-title">${article.title}</h5>
        <p class="card-text">${article.description}</p>
        <c:if test="${article.imagePath.length() > 0}">
            <img src="images/${article.imagePath}" style="width:100%"></img></br>
        </c:if>
    </div>
    <div class="card-footer">
        <span>${numOfLike}</span>
        <c:if test="${sessionScope.USER_DTO.roleId.roleId == 1}">
            <form action="MainController" method="POST" class="inline">
                <input type="hidden" value="${article.userId.userId}" name="userId"/>
                <input type="hidden" value="${article.articleId}" name="articleId"/>
                <input type="submit" name="btnAction" value="Like" class="btn btn-primary"/>
            </form>
        </c:if>
        <c:if test="${sessionScope.USER_DTO.roleId.roleId == 2}">
            <button type="button" class="btn btn-secondary btn-lg" disabled>Like</button>
        </c:if>

        <span>${numOfDislike}</span>
        <c:if test="${sessionScope.USER_DTO.roleId.roleId == 1}">
            <form action="MainController" method="POST">
                <input type="hidden" value="${article.userId.userId}" name="userId"/>
                <input type="hidden" value="${article.articleId}" name="articleId"/>
                <input type="submit" name="btnAction" value="Dislike" class="btn btn-primary"/>
            </form>
        </c:if>
        <c:if test="${sessionScope.USER_DTO.roleId.roleId == 2}">
            <button type="button" class="btn btn-secondary btn-lg" disabled>Dislike</button>
        </c:if>

    </div>
</div>


<c:if test="${sessionScope.USER_DTO.roleId.roleId == 1}">

    <form class="form-inline" style="margin: 20px 20%">
        <input type="hidden" value="${article.userId.userId}" name="userId"/>
        <input type="hidden" value="${article.articleId}" name="articleId"/>
        <div class="form-group col-8 mb-2">
            <label for="inputPassword2" class="sr-only">Enter your comment...</label>
            <input type="text" maxlength="200" name="commentContent" required class="form-control" id="inputPassword2"
                   placeholder="Your comment..." style="width: 100%">
        </div>
        <button type="submit" name="btnAction" value="Post comment" class="btn btn-primary mb-2">Post Comment</button>
    </form>
</c:if>
<c:forEach var="comment" varStatus="counter" items="${listComment}">
    <div class="card" style="margin: 10px 20%">
        <div class="card-header">
            <h5>${comment.userId.fullName}</h5>
            <p font-size="smaller";>${comment.date}</p>
        </div>
        <div class="card-body">
            <p class="card-text">${comment.commentContent}</p>
            <c:set var="user" value="${sessionScope.USER_DTO}"/>
            <c:if test="${user.userId.equals(comment.userId.userId)}">
                <button class="btn btn-danger"
                        onclick="showDialogDeleteComment('${sessionScope.USER_DTO.userId}', '${comment.commentId}', '${article.articleId}')">
                    Delete
                </button>
            </c:if>
        </div>
    </div>
</c:forEach>
<!--<script>
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }
</script>-->

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
