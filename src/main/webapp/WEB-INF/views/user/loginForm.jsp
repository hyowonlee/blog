<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%--<%@ include file="../layout/headerIndex.jsp" %>--%>

<div class="container">
    <form action="/auth/api/login" method="post"> <%--컨트롤러에서 만들지는 않을것 SecurityConfig.java에서 세팅으로 스프링 시큐리티가 가로채게 할것--%>

        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" class="form-control" placeholder="Enter Username" id="username">
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Enter Password" id="password">
        </div>

        <button id="btn-login" class="btn btn-primary">로그인</button>
    </form>


</div>

<%--<script src="/js/user.js"></script>--%>

<%--<%@ include file="../layout/footer.jsp" %>--%>