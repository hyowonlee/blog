<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<div class="container">
    <br />
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
    <button id="btn-update" class="btn btn-warning">수정</button>
    <button id="btn-delete" class="btn btn-danger">삭제</button>
    </div>
</div>


<script src="/js/board.js"></script>