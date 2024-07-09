<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="/moviebooking/js/adlogin.js"></script>
<div class="login-container">
	<h2>관리자로그인</h2>
	<form method="post" action="#">
		<div class="input-group">
			<label for="id">아이디</label> <input type="text" id="id" name="id">
		</div>
		<div class="input-group">
			<label for="passwd">비밀번호</label> <input type="password" id="passwd"
				name="passwd">
		</div>
		<button type="button" class="login-button" id="adLogin">로그인</button>
	</form>
</div>