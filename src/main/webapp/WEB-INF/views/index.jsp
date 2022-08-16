<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%--<%@ include file="layout/headerIndex.jsp" %>--%>

<div class="container">

    <c:forEach var="board" items="${boards}"> <%--${boards} el 표현식으로 컨트롤러단에서 Model 객체로 넘겨준 데이터를 의미함--%>
        <div class="card m-2">
        <%--<img class="card-img-top" src="#" alt="Card image">--%>
        <div class="card-body">
            <h4 class="card-title">${board.title}</h4> <%--${board.title}은 우리가 클래스에서 만들어준 getTitle() getter가 호출됨 데이터를 모델에서 다 들고오는게 아닌듯--%>
            <%--<p class="card-text">상세보기</p>--%>
            <a href="#" class="btn btn-primary">상세보기</a>
        </div>
    </div>
    </c:forEach>

</div>

<%--<%@ include file="layout/footer.jsp" %>--%>