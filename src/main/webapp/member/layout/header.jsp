<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
		<div class="logo">
			<a href="/moviebooking/index.do">logo</a>
		</div>
		<nav>
			<ul>
				<li><a href="/moviebooking/movie/reservationForm.do" class="headerNavMenu">예매</a></li>
				<li><a href="#" class="headerNavMenu">영화</a></li>
				<li><a href="/moviebooking/noticeList.do" class="headerNavMenu">공지사항</a></li>
				<li><a href="/moviebooking/update.do" class="headerNavMenu">마이페이지</a></li>
			</ul>
		</nav>
		<aside>
			<ul>
				<c:if test="${empty sessionScope.id}">
				<li><a href="/moviebooking/loginForm.do">로그인</a></li>
				<li><a href="/moviebooking/registerForm.do">회원가입</a></li>
				</c:if>
				<c:if test="${!empty sessionScope.id}">
				<li><a href="/moviebooking/logout.do" >로그아웃</a></li>
				</c:if>
			</ul>
		</aside>
	</header>