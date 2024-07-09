<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty sessionScope.id}">
	<meta http-equiv="Refresh"
		content="0;url=/moviebooking/ad/loginForm.do">
</c:if>
<c:if test="${type != 0}">
	<meta http-equiv="Refresh" content="0;url=/moviebooking/index.do">
</c:if>
<script src="/moviebooking/js/movieupdate.js">
</script>
<div class="movieUpdate">
	<form id="editForm"
		action="/moviebooking/ad/movie/movieUpdatePro.do" method="post"
		enctype="multipart/form-data">
		<input type="hidden" id="movieCd" value="${movie.movieCd}" name="movieCd">
		<div class="header">
			<div class="movieImg">
				<label for="movieImgInput">영화 이미지:</label> <input type="file"
					id="image" name="image">
			</div>
			<div class="Info">
				<h2>${movie.movieNm}</h2>
				<span>${movie.audits} · ${movie.openDt} · ${movie.genres} · ${movie.showTm}분</span>
				<div class="etc">
					<h3>배우/제작진</h3>
					<ul>
						<li><span>감독</span> ${movie.director}</li>
						<li><span>배우</span> ${movie.actors}</li>
						<li><span>배급사</span> ${movie.companys}</li>
					</ul>
				</div>
				<div class="salesShare">
					예매율 <span>${movie.salesShare}</span>
				</div>
			</div>
		</div>
		<div class="content">
			<h3>줄거리 수정</h3>
			<textarea id="content" name="content" rows="10"
				placeholder="줄거리를 입력하세요">${movie.content}</textarea>
			<div class="btns">
				<button type="submit">저장하기</button>
			</div>
		</div>
	</form>
</div>