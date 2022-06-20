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
    <label>searchId</label>
    <input type="text" name="id">
    <button>search</button>
</form>
<%--회원정보수정--%>
<form action="/dummy/update" method="post">
    <label>updateUsername</label>
    <input type="text" name="username" placeholder="username">
    <input type="password" name="password" placeholder="password">
    <input type="text" name="email" placeholder="email">
    <button>submit</button>
</form>

<%--json 회원정보수정--%>
<label>json update username</label>
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

<%--회원정보 삭제--%>
<label>delete username</label>
<input type="text" id="jsonDeleteUsername" placeholder="username">
<button id="jsonDelete">submit</button>


</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script> <!--jquery import-->
<script>

    $(function () {
        <%--json 회원정보수정--%>
        $('#jsonSubmit').click(function () {
            let data =
                {
                    username: $('#jsonUsername').val(),
                    password: $('#jsonPassword').val(),
                    email: $('#jsonEmail').val()
                };/*javascript 객체*/
            $.ajax({
                type: 'post',
                url: '/dummy/jsonupdate',
                data: JSON.stringify(data), /*객체를 전송하려면 객체 자체를 보내면 이해하지 못하니 javascript객체를 json(문자열)로 변환*/
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    console.log(data)
                },
                error: function (request, status, error) {
                    console.log(request.responseText);
                }
            });
        });

        <%--ajax 회원정보삭제--%>
        $('#jsonDelete').click(function () {
            $.ajax({
                type: 'delete',
                url: '/dummy/delete/'+$('#jsonDeleteUsername').val(),
                dataType: 'json',
                success: function (response) {
                    console.log(response)
                },
                error: function (request, status, error) {
                    console.log(request.responseText);
                }
            });
        });
    });
    <%--json 회원정보수정끝--%>
</script>
</html>