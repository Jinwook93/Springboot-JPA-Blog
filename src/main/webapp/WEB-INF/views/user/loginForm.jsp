<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<form action ="/auth/loginProc" method = "post">
<!-- <form> -->
  <div class="form-group">
 <label for="username">사용자 이름</label>
    <input type="username"  name = "username" class="form-control" placeholder="Enter username" id="username">
  </div>
  <div class="form-group">
    <label for="password">비밀번호</label>
    <input type="password" name ="password" class="form-control" placeholder="Enter password" id="password">
  </div>
<!--   <div class="form-group form-check"> -->
<!--     <label class="form-check-label"> -->
<!--       <input class="form-check-input"  name = "remember" type="checkbox"> Remember me -->
<!--     </label> -->
  </div>
   <button id ="btn-login"  class="btn btn-primary">로그인</button> 			<!--  폼 로그인을 사용할 것이므로 form 안에 집어넣었다 -->
</form>
<!--    <button id ="btn-login"  class="btn btn-primary">로그인</button> -->
 
<!--  <script src ="/js/user.js"></script>							스프링시큐리티 로그인을 사용할 것이므로 주석 -->
<%@ include file="../layout/footer.jsp"%>
