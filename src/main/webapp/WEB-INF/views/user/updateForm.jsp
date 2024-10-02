<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<c:choose>
	<c:when test="${empty principal.user.oauth}">
		<form>
			<div class="form-group">
				<input type="hidden" value="${principal.user.id}" id="id"> <label
					for="username">사용자 이름</label> <input type="text"
					value="${principal.user.username}" class="form-control"
					placeholder="Enter username" id="username" readonly>
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label> <input type="password"
					class="form-control" placeholder="Enter password" id="password">
			</div>
			<div class="form-group">
				<label for="email">이메일</label> <input type="email"
					value="${principal.user.email}" class="form-control"
					placeholder="Enter email" id="email">
			</div>
			<br>
			<button id="btn-update" class="btn btn-primary">회원 정보 수정</button>
			<br>
		</form>
	</c:when>
	<c:otherwise>
		<p><b>oAuth 로그인 사용자는 회원 수정을 할 수 없습니다</b></p>
				<input type="hidden" value="${principal.user.id}" id="id"> <label
					for="username">사용자 이름</label> <input type="text"
					value="${principal.user.username}" class="form-control"
					placeholder="Enter username" id="username" readonly>
			</div>
			<br>
			<div class="form-group">
				<label for="email">이메일</label> <input type="email"
					value="${principal.user.email}" class="form-control"
					placeholder="Enter email" id="email" readonly>
			</div>
			<br>
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		<br>
	</c:otherwise>
</c:choose>


<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
