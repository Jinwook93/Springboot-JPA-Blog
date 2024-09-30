<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input type="hidden" id="id" value="${board.id}" />
		<div class="form-group">
			<label for="title">Title</label> <input value="${board.title}"
				class="form-control" placeholder="Enter title" id="title">
		</div>
		<div class="form-group">
			<label for="content">content:</label>
			<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
		</div>
	</form>
	<button id="btn-update" class="btn btn-primary">글 수정완료</button>
	<!--  JSON으로 요청할 것임 -->
</div>
<br>
<script>
	$('.summernote').summernote({
		placeholder : '내용을 입력하세요',
		tabsize : 2,
		height : 100
	});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


