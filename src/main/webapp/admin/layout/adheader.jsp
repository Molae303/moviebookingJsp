<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<div class="logo">
		<a href="/moviebooking/ad/main.do">logo</a>
	</div>
	<nav>
		<ul>
			<li><a href="#" class="headerNavMenu">예매</a></li>
			<li><a href="#" class="headerNavMenu">영화</a></li>
			<li><a href="/moviebooking/ad/noticeList.do" class="headerNavMenu">공지사항</a></li>
			<li><a href="/moviebooking/ad/userList.do" class="headerNavMenu">관리자페이지</a></li>
			<li><a href="/moviebooking/ad/movie/boxofficeList.do">박스오피스리스트</a></li>
		</ul>
	</nav>
	<aside>
		<ul>
			<c:if test="${empty sessionScope.id}">
				<li><a href="/moviebooking/ad/loginForm.do">로그인</a></li>
			</c:if>
			<c:if test="${!empty sessionScope.id}">
				<li><a href="/moviebooking/ad/logout.do">로그아웃</a></li>
			</c:if>
		</ul>
	</aside>
</header>