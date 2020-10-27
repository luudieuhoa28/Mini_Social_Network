<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : notification
    Created on : Sep 24, 2020, 11:43:34 AM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<c:set var="user" value="${sessionScope.USER_DTO}"/>
<jsp:include page="navbar.jsp">
    <jsp:param name="userId" value="${user.userId}"/>
    <jsp:param name="fullName" value="${user.fullName}"/>
    <jsp:param name="roleId" value="${user.roleId.roleId}"/>
</jsp:include>
<c:set var="notificationList" value="${requestScope.LIST_NOTIFICATION}"/>
<c:if test="${notificationList.size() > 0}">
    <c:forEach var="notification" varStatus="counter" items="${notificationList}">
        <a href="ShowArticleDetailController?articleId=${notification.articleId.articleId}">
            <div class="card">
                <div class="card-body">
                    <c:if test="${notification.activityType == 0}">
                        <p>${notification.userId.fullName} dislike on your post. ${notification.date}</p>
                    </c:if>
                    <c:if test="${notification.activityType == 1}">
                        <p>${notification.userId.fullName} like on your post. ${notification.date}</p>
                    </c:if>
                    <c:if test="${notification.activityType == 2}">
                        <p>${notification.userId.fullName} comment on your post. ${notification.date}</p>
                    </c:if>
                </div>
            </div>
        </a>
    </c:forEach>
</c:if>
<c:if test="${notificationList.size() == 0}">
    <div class="card" style="margin: 20px 20%">
        <p class="card-body">There is no Notification!!!</p>
    </div>
</c:if>
<c:if test="${notificationList.size() == null}">
    <p>No notification for you!!!</p>
</c:if>
</body>
</html>
