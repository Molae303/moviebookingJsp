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
<!DOCTYPE html>
<script src="/moviebooking/js/boxofficelist.js">
</script>
<div class="boxofficeList">
	<h1>박스오피스</h1>
	<form id="movieForm">
		<table>
			<thead>
				<tr>
					<th>순위</th>
					<th>영화CODE</th>
					<th>제목</th>
					<th>장르</th>
					<th>감독</th>
					<th>개봉일</th>
					<th>예매율</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="movie" items="${boxofficeList}" varStatus="i" >
				<tr>
					<td>${i.count}</td>
					<td><a href="/moviebooking/ad/movie/movieInfo.do?movieCd=${movie.movieCd}">${movie.movieCd}</a></td>
					<td>${movie.movieNm}</td>
					<td>${movie.genres}</td>
					<td>${movie.director}</td>
					<td>${movie.openDt}</td>
					<td>${movie.salesShare}%</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="buttons">
			<button type="button" id="apiLoad">영화정보로드&DB저장</button>
		</div>
	</form>
</div>
