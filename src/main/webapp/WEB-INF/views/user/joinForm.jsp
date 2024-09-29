<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<form>
  <div class="form-group">
    <label for="username">사용자 이름</label>
    <input type="username" class="form-control" placeholder="Enter username" id="username">
  </div>
  <div class="form-group">
    <label for="password">비밀번호</label>
    <input type="password" class="form-control" placeholder="Enter password" id="password">
  </div>
    <label for="email">이메일</label>
    <input type="email" class="form-control" placeholder="Enter email" id="email">
  </div>
<br>
<!--   <button id = "btn-save"  class="btn btn-primary">회원가입</button> -->
</form>
  <button id = "btn-save"  class="btn btn-primary">회원가입</button>
<script src ="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
