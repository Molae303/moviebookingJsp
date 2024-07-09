<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="/moviebooking/js/noticecontent.js"></script>
<div class="noticeContent">
	<h1>공지사항 보기</h1>
	<table class="notice-details">
		<tr>
			<th>제목</th>
			<td>[${article.category}] ${article.subject}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${article.writer}</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${article.regDate}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${article.readcount}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<pre>${article.content}</pre> 
			</td>
		</tr>
	</table>
	<div class="form-actions">
	<c:if test="${type == 0}">
		<button type="button" onclick="location.href='/moviebooking/ad/noticeUpdateForm.do?num=${num}&pageNum=${pageNum}'">수정</button>
		<c:if test="${!empty sessionScope.id}">
			<button type="button" id="deleteProcess" name="${num}">삭제</button>
		</c:if>
		<button type="button" onclick="location.href='/moviebooking/ad/noticeList.do?pageNum=${pageNum}'">목록</button>
	</c:if>
	<c:if test="${type == 1}">
		<button type="button" onclick="location.href='/moviebooking/noticeList.do?pageNum=${pageNum}'">목록</button>
	</c:if>
	</div>
</div>