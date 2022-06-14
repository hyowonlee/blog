<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Test Page</title>
</head>
<body>
<h1>this page is test page</h1>
<form action="/dummy/join" method="post">
    <label>join</label>
    <input type="text" name="username" placeholder="username">
    <input type="password" name="password" placeholder="password">
    <input type="text" name="email" placeholder="email">
    <%--<button type="submit">submit</button>--%>
    <button>submit</button>
</form>
<form action="/dummy/search" method="get">
    <label>search</label>
    <input type="text" name="id">
    <button>search</button>
</form>
<form action="/dummy/search/list/page">
    <input type="text" name="page">
    <label>pageSearch (start = 0, size = 2)</label>
    <button>submit</button>
</form>
</body>
</html>