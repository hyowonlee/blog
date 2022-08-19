<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%--<%@ include file="layout/headerIndex.jsp" %>--%>

<div class="container">

    <c:forEach var="board" items="${boards.content}"> <%--${boards} el 표현식으로 컨트롤러단에서 Model 객체로 넘겨준 데이터를 의미함 페이징을 위해 Page 객체를 받았기에 페이징 정보 없이 데이터만 뽑기위해 content 필드로 접근--%>
        <div class="card m-2">
        <%--<img class="card-img-top" src="#" alt="Card image">--%>
        <div class="card-body">
            <h4 class="card-title">${board.title}</h4> <%--${board.title}은 우리가 클래스에서 만들어준 getTitle() getter가 호출됨 데이터를 모델에서 다 들고오는게 아닌듯--%>
            <%--<p class="card-text">상세보기</p>--%>
            <a href="#" class="btn btn-primary">상세보기</a>
        </div>
    </div>
    </c:forEach>

    <ul class="pagination justify-content-center">
        <c:choose>
            <c:when test="${boards.first eq true}">
                <li class="page-item disabled"><a id="/index?page=${boards.number-1}" class="page-link">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a id="/index?page=${boards.number-1}" class="page-link">Previous</a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${boards.last eq true}">
                <li class="page-item disabled"><a id="/index?page=${boards.number+1}" class="page-link">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a id="/index?page=${boards.number+1}" class="page-link">Next</a></li>
            </c:otherwise>
        </c:choose>

    </ul>

</div>

<%--headerIndex.js의 a 클릭 ajax 페이지 로딩이 a태그에 바인딩 되어야 되는데 헤더 페이지 로딩되고 a태그에 바인딩 되고 이 페이지가 불러와져서 여긴 a태그에 바인딩이 안됨 그래서 js파일 따로 만들어서 따로 import해줌--%>
<script src="/js/index.js"></script>



<%--<%@ include file="layout/footer.jsp" %>--%>