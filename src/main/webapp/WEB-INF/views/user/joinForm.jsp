<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%--<%@ include file="../layout/headerIndex.jsp" %>--%>

<div class="container">

    <div class="form-group">
        <label for="username">Username</label>
<%--        <input id="input-username" type="text" class="form-control" placeholder="Enter Username" id="username">--%>
        <input id="username" type="text" class="form-control" placeholder="Enter Username" id="username">
        <button id="btn-checkUsername" class="btn btn-primary">중복확인</button>
    </div>

    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" placeholder="Enter Password" id="password">
    </div>

    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" placeholder="Enter Email" id="email">
    </div>

    <button id="btn-save" class="btn btn-primary" disabled>회원가입</button>
</div>

<script src="/js/user.js"></script>

<%--<%@ include file="../layout/footer.jsp" %>--%>