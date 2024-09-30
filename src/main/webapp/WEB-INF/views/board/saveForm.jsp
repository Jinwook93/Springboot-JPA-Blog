<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
<form>
  <div class="form-group">
 <label for="title">Title</label>
    <input type="text" class="form-control" placeholder="Enter title" id="title">
  </div>
  <div class="form-group">
  <label for="content">content:</label>
  <textarea class="form-control summernote" rows="5" id="content"></textarea>

<!--   <div class="form-group form-check"> -->
<!--     <label class="form-check-label"> -->
<!--       <input class="form-check-input"  name = "remember" type="checkbox"> Remember me -->
<!--     </label> -->

  </div>

</form>
   <button id ="btn-save"  class="btn btn-primary">글쓰기</button> 			<!--  JSON으로 요청할 것임 -->
</div>
<br>
  <script>
      $('.summernote').summernote({
        placeholder: '내용을 입력하세요',
        tabsize: 2,
        height: 100
      });
    </script>
    <script src ="/js/board.js"></script>	
<%@ include file="../layout/footer.jsp"%>


