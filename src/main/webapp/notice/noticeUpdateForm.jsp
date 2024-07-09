<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh"
		content="0;url=/moviebooking/ad/loginForm.do">
</c:if>
<c:if test="${type != 0}">
	<meta http-equiv="Refresh" content="0;url=/moviebooking/index.do">
</c:if>
<script src="/moviebooking/js/noticeupdate.js">
</script>
<div class="noticeBoardWriter">
	<h1>공지사항 수정</h1>
	<form action="#" method="post">
		<div class="form-group">
			<input type="hidden" id="num" name="num" value="${num}">
			<input type="hidden" id="pageNum" name="pageNum" value="${pageNum}">
			<label for="title">제목</label> <input type="text" id="subject"
				name="subject" required value="${article.subject}">
		</div>
		<div class="form-group">
			<label for="category">구분</label> <select id="category"
				name="category" required >
				<option value="기타">기타</option>
				<option value="극장">극장</option>
				<option value="이벤트">이벤트</option>
			</select>
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea id="content" name="content" rows="10" required>${article.content}</textarea>
		</div>
		<div class="form-actions">
			<button type="button" id="updateProcess">수정</button>
			<button type="button" onclick="window.location.href='/moviebooking/ad/noticeContent.do?num=${num}&pageNum=${pageNum}'">취소</button>
		</div>
	</form>
</div>