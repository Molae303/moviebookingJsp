<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="/moviebooking/js/ulogin.js"></script>
<div class="login-container">
	<h2>로그인</h2>
	<form method="post" action="#">
		<div class="input-group">
			<label for="id">아이디</label> <input type="text" id="cid" name="id">
		</div>
		<div class="input-group">
			<label for="passwd">비밀번호</label> <input type="password" id="cpasswd"
				name="passwd">
		</div>
		<button type="button" class="login-button" id="uLogin">로그인</button>
		<div class="links">
			<a href="#">아이디/비밀번호 찾기</a> <span>|</span> <a href="/moviebooking/registerForm.do">회원가입</a>
		</div>
	</form>
</div>