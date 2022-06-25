<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>${appid}</h1>
<c:forEach var="item" items="${newsItems}">
    <c:out value="${item.title}"></c:out>
    <br>
    <a href="<c:url value="${item.url}"></c:url>">${item.url}</a>
    <br>
</c:forEach>
</body>
</html>