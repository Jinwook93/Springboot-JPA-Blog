<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<form>
  <div class="form-group">
  	   <input type="hidden" value = "${principal.user.id}" id="id">
    <label for="username">사용자 이름</label>
    <input type="username" value = "${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly>
  </div>
  <div class="form-group">
    <label for="password">비밀번호</label>
    <input type="password" class="form-control" placeholder="Enter password" id="password">
  </div>
    <label for="email">이메일</label>
    <input type="email" value = "${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
  </div>
<br>
<!--   <button id = "btn-save"  class="btn btn-primary">회원가입</button> -->
</form>
  <button id = "btn-update"  class="btn btn-primary">회원 정보 수정</button>
<script src ="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
