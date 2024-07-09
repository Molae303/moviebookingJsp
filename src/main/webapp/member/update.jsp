<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=/moviebooking/loginForm.do">
</c:if>
<script src="/moviebooking/js/mupdate.js"></script>
<div class="updateConfirm">
	<div class="lock-icon">
		<i class="fa-solid fa-lock"></i>
	</div>
	<p class="message">
		회원님의 정보를 안전하게 보호하기 위해<br>비밀번호를 다시 한번 확인해 주세요.
	</p>
	<form action="#" method="post">
		<div class="form-group">
			<label for="id">아이디</label> <input type="text" id="id" name="id"
				readonly value="${sessionScope.id}">
		</div>
		<div class="form-group">
			<label for="passwd">비밀번호</label> <input type="password" id="passwd"
				name="passwd" placeholder="비밀번호 입력" required>
		</div>
		<div class="form-actions">
			<button type="button" onclick="window.location.href='/moviebooking/index.do'">취소</button>
			<button type="button" id="confirmButton">확인</button>
		</div>
	</form>
</div>