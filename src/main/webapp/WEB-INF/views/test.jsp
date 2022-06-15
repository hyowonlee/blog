<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Test Page</title>
</head>
<body>
<h1>this page is test page</h1>
<%--회원가입--%>
<form action="/dummy/join" method="post">
    <label>join</label>
    <input type="text" name="username" placeholder="username">
    <input type="password" name="password" placeholder="password">
    <input type="text" name="email" placeholder="email">
    <%--<button type="submit">submit</button>--%>
    <button>submit</button>
</form>
<%--회원검색--%>
<form action="/dummy/search" method="get">
    <label>search</label>
    <input type="text" name="id">
    <button>search</button>
</form>
<%--회원정보수정--%>
<form action="/dummy/update" method="post">
    <label>update</label>
    <input type="text" name="username" placeholder="username">
    <input type="password" name="password" placeholder="password">
    <input type="text" name="email" placeholder="email">
    <button>submit</button>
</form>

<%--json 회원정보수정--%>
<label>json update</label>
<input type="text" id="jsonUsername" placeholder="username">
<input type="password" id="jsonPassword" placeholder="password">
<input type="text" id="jsonEmail" placeholder="email">
<button id="jsonSubmit">submit</button>
<%--json 회원정보수정끝--%>

<%--회원정보 조회 페이징--%>
<form action="/dummy/search/list/page">
    <label>pageSearch (start = 0, size = 2)</label>
    <input type="text" name="page">
    <button>submit</button>
</form>

</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script> <!--jquery import-->
<script>
    <%--json 회원정보수정--%>
    $(function () {
        $('#jsonSubmit').click(function () {
            let data =
                {
                    username: $('#jsonUsername').val(),
                    password: $('#jsonPassword').val(),
                    email: $('#jsonEmail').val()
                };
            $.ajax({
                type: 'post',
                url: '/dummy/jsonupdate',
                data: JSON.stringify(data),
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    console.log(data)
                },
                error: function (request, status, error) {
                    console.log("error");
                }
            });
        });
    });
    <%--json 회원정보수정끝--%>
</script>
</html>