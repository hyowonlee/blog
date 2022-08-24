<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%--<%@ include file="layout/headerIndex.jsp" %>--%>

<div class="container">
    <form>

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