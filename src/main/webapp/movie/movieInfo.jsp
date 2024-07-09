<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="movieInfo">
	<div class="header">
		<div class="movieImg">
			<img src="<%=request.getContextPath()%>${movie.image}" alt="영화이미지">
		</div>
		<div class="Info">
			<c:choose>
				<c:when test="${movie.audits.contains('12')}">
					<img src="/moviebooking/images/movie/audits/12.svg" alt="등급표시">
				</c:when>		
				<c:when test="${movie.audits.contains('15')}">
					<img src="/moviebooking/images/movie/audits/15.svg" alt="등급표시">
				</c:when>		
				<c:when test="${movie.audits.contains('19')}">
					<img src="/moviebooking/images/movie/audits/19.svg" alt="등급표시">
				</c:when>
				<c:otherwise>
					<img src="/moviebooking/images/movie/audits/all.svg" alt="등급표시">
				</c:otherwise>
			</c:choose>
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
		<div class="btns">
			<button type="button" onclick="window.location.href='/moviebooking/movie/reservationForm.do?movieCd=${movie.movieCd}'">예매하기 <i class="fa-solid fa-arrow-right"></i></button>
			<c:if test="${type==0}">
				<button type="button" onclick="window.location.href='/moviebooking/ad/movie/movieUpdateForm.do?movieCd=${movie.movieCd}'">정보수정</button>
			</c:if>
		</div>
	</div>
	<div class="content">
		<h3>줄거리</h3>
		<pre>${movie.content}</pre>
	</div>
</div>
