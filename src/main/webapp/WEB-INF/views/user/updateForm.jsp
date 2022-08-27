<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <%--pom.xml에서 추가한 spring security 태그 라이브러리를 가져옴 시큐리티 공식문서:https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html--%>
<sec:authorize access="isAuthenticated()">
    <%--  스프링 시큐리티는 로그인 성공 시 스프링이 세션을 만들어서 principal이라는 곳에 저장해줌 --%>
    <sec:authentication property="principal" var="principal"/> <!-- 위의 공식문서 보면 property principal에서 현재 유저의 principal(접속주체,세션)객체를 가지고있다고 하는데 그래서 property(재산)에 principal을 변수 principal에 저장해줌 -->
</sec:authorize>


<div class="container">

    <div class="form-group">
        <label for="username">Username</label>
        <input id="username" value="${principal.user.username}" type="text" class="form-control" id="username" readonly>
    </div>

    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" placeholder="Enter Password" id="password">
    </div>

    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter Email" id="email">
    </div>

    <button id="btn-update" class="btn btn-primary">회원수정</button>
</div>

<script src="/js/user.js"></script>

<%--<%@ include file="../layout/footer.jsp" %>--%>