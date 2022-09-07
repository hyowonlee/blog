<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> <!-- html 태그들이 있는 라이브러리를 가져옴(태그라이브러리) 여기서 가져온걸 쓰려면 접두사prefix에 c를 붙여라 -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <%--pom.xml에서 추가한 spring security 태그 라이브러리를 가져옴 시큐리티 공식문서:https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html--%>
<sec:authorize access="isAuthenticated()">
    <%--  스프링 시큐리티는 로그인 성공 시 스프링이 세션을 만들어서 principal이라는 곳에 저장해줌 --%>
    <sec:authentication property="principal" var="principal"/> <!-- 위의 공식문서 보면 property principal에서 현재 유저의 principal(접속주체,세션)객체를 가지고있다고 하는데 그래서 property(재산)에 principal을 변수 principal에 저장해줌 -->
</sec:authorize>

<div class="container">
    <br />
    <div>
        글 번호 : <span id="id"><i>${board.id}</i></span> <br/>
        글 작성자 : <span><i>${board.user.username}</i></span>
    </div>
    <hr/>
    <div class="form-group">
        <h3>${board.title}</h3>
    </div>
    <hr />
    <div class="form-group">
        <div>${board.content}</div>
    </div>
    <hr />

    <div class="float-right">
    <%--<button class="btn btn-secondary">돌아가기</button>--%>
        <c:if test="${board.user.id == principal.user.id}">
            <a id="/board/updateForm/${board.id}" class="btn btn-warning">수정</a>
            <button id="btn-delete" class="btn btn-danger">삭제</button>
        </c:if>

    </div>

    <div><br /><br /></div>

    <div class="card">
        <div class="card-body">
            <textarea class="form-control" row="1"></textarea>
        </div>
        <div class="card-footer">
            <input type="button" value="등록" class="btn btn-primary float-right" />
        </div>
    </div>
    <br />
    <div class="card">
        <div class="card-header">댓글 리스트</div>
        <ul id="reply--box" class="list-group">
            <c:forEach var="reply" items="${board.replys}">
              <li id="reply--1" class="list-group-item d-flex justify-content-between">
                  <div>${reply.content}</div>
                  <div class="d-flex">
                    <div class="font-italic">작성자: ${reply.user.username} &nbsp;</div>
                    <c:if test="${reply.user.id == principal.user.id}">
                        <button class="badge">삭제</button>
                    </c:if>
                  </div>
              </li>
            </c:forEach>
        </ul>
    </div>

</div>


<script src="/js/board.js"></script>
<%--headerIndex.js의 a 클릭 ajax 페이지 로딩이 a태그에 바인딩 되어야 되는데 헤더 페이지 로딩되고 a태그에 바인딩 되고 이 페이지가 불러와져서 여긴 a태그에 바인딩이 안됨 그래서 js파일 따로 만들어서 따로 import해줌--%>
<script src="/js/index.js"></script>