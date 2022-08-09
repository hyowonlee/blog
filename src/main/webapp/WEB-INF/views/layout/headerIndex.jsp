<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> <!-- html 태그들이 있는 라이브러리를 가져옴(태그라이브러리) 여기서 가져온걸 쓰려면 접두사prefix에 c를 붙여라 -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <%--pom.xml에서 추가한 spring security 태그 라이브러리를 가져옴 시큐리티 공식문서:https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html--%>
<sec:authorize access="isAuthenticated()">
    <%--  스프링 시큐리티는 로그인 성공 시 스프링이 세션을 만들어서 principal이라는 곳에 저장해줌 --%>
    <sec:authentication property="principal" var="principal"/> <!-- 위의 공식문서 보면 property principal에서 현재 유저의 principal(접속주체,세션)객체를 가지고있다고 하는데 그래서 property(재산)에 principal을 변수 principal에 저장해줌 -->
</sec:authorize>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/htmlTag.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script> <%--jquery import--%>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/js/headerIndex.js"></script>
</head>

<body class="d-flex flex-column min-vh-100">
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a id='/index' class="navbar-brand">Collapsible Navbar</a> <%-- a태그들은 headerIndex.js에서 본문 ajax 로딩하기위해서 href를 다 없애고 js파일에서 url 로딩해줌 --%>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <%--  jstl  --%>
    <c:choose>
        <c:when test="${empty principal}"> <!-- 로그인 아직 안됐을땐(세션이 비어있으면) 이거, 전통로그인에선 로그인시 principal이라는 key 이름으로 객체를 세션에 바인딩했음 시큐리티 로그인에선 위에서 세션객체인 principal을 변수 principal에 매핑함-->
            <ul class="navbar-nav ml-auto"> <%--ml-auto is right align--%>
                <li class="nav-item">
                    <a id="/auth/loginForm" class="nav-link">로그인</a> <%-- a태그들은 headerIndex.js에서 본문 ajax 로딩하기위해서 href를 다 없애고 js파일에서 url 로딩해줌 --%>
                </li>
                <li class="nav-item">
                    <a id="/auth/joinForm" class="nav-link">회원가입</a> <%-- a태그들은 headerIndex.js에서 본문 ajax 로딩하기위해서 href를 다 없애고 js파일에서 url 로딩해줌 --%>
                </li>
            </ul>
        </c:when>
        <c:otherwise> <!-- 로그인 됐을땐(세션이 있으면) 이거 -->
           <ul class="navbar-nav ml-auto"> <%--ml-auto is right align--%>
                <li class="nav-item">
                    <a id="/board/form" class="nav-link">글쓰기</a> <%-- a태그들은 headerIndex.js에서 본문 ajax 로딩하기위해서 href를 다 없애고 js파일에서 url 로딩해줌 --%>
                </li>
                <li class="nav-item">
                    <a id="/user/form" class="nav-link">회원정보</a> <%-- a태그들은 headerIndex.js에서 본문 ajax 로딩하기위해서 href를 다 없애고 js파일에서 url 로딩해줌 --%>
                </li>
                <li class="nav-item">
                    <a href="/logout" class="nav-link">로그아웃</a> <%-- 이건 페이지가 리로딩 돼야 정보가 업데이트 되니 href로 리다이렉트 --%>
                </li>
            </ul>
        </c:otherwise>
    </c:choose>
    </div>
</nav>
<br />

<div id="bodyContents">
<%--    <%@ include file="../index.jsp"%> &lt;%&ndash;첫 홈페이지 요청시에 본문에 아무것도 없으면 안되니 처음엔 index 본문을 로딩&ndash;%&gt;--%>
</div>


<%@ include file="../layout/footer.jsp" %>