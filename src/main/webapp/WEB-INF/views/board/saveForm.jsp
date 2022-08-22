<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%--<%@ include file="layout/headerIndex.jsp" %>--%>

<div class="container">
    <form> <%--컨트롤러에서 만들지는 않을것 SecurityConfig.java에서 세팅으로 스프링 시큐리티가 가로채게 할것--%>

        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" placeholder="Enter title" id="title">
        </div>

        <div class="form-group">
            <label for="content">Content</label>
            <textarea class="form-control summernote" rows="5" id="content"></textarea>
        </div>
    </form>
    <div class="float-right">
        <button id="btn-save" class="btn btn-primary">저장</button>
    </div>
</div>

<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
</script>
<script src="/js/board.js"></script>

<%--<%@ include file="layout/footer.jsp" %>--%>