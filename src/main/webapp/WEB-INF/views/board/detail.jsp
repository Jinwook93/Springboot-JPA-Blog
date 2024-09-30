<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
<div>
	글 번호 : <span id= "id"><i>${board.id}</i></span>
	<br>
    작성자 : <span><i>${board.user.username}</i></span>		<!-- board에 조인된 user의 정보를 들고 옴 -->
</div>
	<br />
		<div class="form-group">
			<label for="title">Title</label>
			<h3>${board.title}</h3>
		</div>
		<hr />
		<div class="form-group">
			<label for="content">Content</label>
			<div>${board.content}</div>
		</div>
	<div>
	<!--  JSON으로 요청할 것임 -->
		<div class="text-right">
<!-- 			<button id="btn-save" class="btn btn-primary">글쓰기</button> -->
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${board.user.id == principal.user.id}">
	<button class="btn btn-warning" id="btn-update">수정</button>
	<button class="btn btn-danger" id="btn-delete">삭제</button>
	</c:if>
</div>
</div>
<br>
<script src ="../js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


