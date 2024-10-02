<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<div>
		글 번호 : <span id="id"><i>${board.id}</i></span> <br> 작성자 : <span><i>${board.user.username}</i></span>
		<!-- board에 조인된 user의 정보를 들고 옴 -->
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
				<a href="/board/${board.id}/updateForm" class="btn btn-warning"
					id="btn-update_goForm">수정</a>
				<button class="btn btn-danger" id="btn-delete">삭제</button>
			</c:if>
		</div>


		<div class="card">
			<form>
				<input type="hidden" id="userId" value="${principal.user.id}" /><%--어느 사용자가 댓글을 썼는지 알아야함 --%>
				<input type="hidden" id="boardId" value="${board.id}" />		<%--어느 게시글에 댓글을 썼는지 알아야함 --%>
				<div class="card-body">
					<textarea id="reply-content" class="form-control" rows="1"></textarea>
				</div>
				<div class="card-footer">
					<button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
				</div>
			</form>
		</div>

		<br>
		<div class="card">
			<div class="card-header">댓글 리스트</div>
			<ul id="reply-box" class="list-group">
				<c:forEach var="reply" items="${board.replys}">
					<li id="reply-${reply.id}"
						class="list-group-item d-flex justify-content-between">
						<div>${reply.content }</div>
						<div class="d-flex">
							<div class="font-italic">작성자 : ${reply.user.username}
								&nbsp;</div>
								<c:if test="${reply.user.id == principal.user.id}">
							<button onClick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
							</c:if>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<br>

		<!-- 	<div class ="card"> -->
		<!-- 		<div class ="card-header"> 댓글 리스트</div> -->
		<!-- 		<ul id ="reply--box" class ="list-group"> -->
		<!-- 			<li id ="reply--1" class ="list-group-item d-flex justify-content-between"> -->
		<!-- 			<div>댓글 내용</div> -->
		<!-- 			<div class ="d-flex"> -->
		<!-- 				<div class="font-italic">작성자 : lee &nbsp;</div> -->
		<!-- 				<button class="badge">삭제</button> -->
		<!-- 				</div> -->
		<!-- 				</li> -->
		<!-- 				</ul> -->
		<!-- 			</div> -->

	</div>

</div>
<br>
<script src="../js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


