<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh" content="0;url=/moviebooking/ad/loginForm.do">
</c:if>
<c:if test="${type != 0}">
	<meta http-equiv="Refresh" content="0;url=/moviebooking/index.do">
</c:if>
<div class="noticeBoardWriter">
	<h1>공지사항 작성</h1>
	<form action="/moviebooking/ad/noticePro.do" method="post">
		<div class="form-group">
			<label for="subject">제목</label> <input type="text" id="subject"
				name="subject" required>
		</div>
		<div class="form-group">
			<label for="category">구분</label> <select id="category"
				name="category" required>
				<option value="기타">기타</option>
				<option value="극장">극장</option>
				<option value="이벤트">이벤트</option>
			</select>
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea id="content" name="content" cols="100" wrap="hard" required></textarea>
		</div>
		<div class="form-actions">
			<button type="submit">등록</button>
			<button type="reset">취소</button>
		</div>
	</form>
</div>