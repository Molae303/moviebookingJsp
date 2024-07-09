<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="/moviebooking/css/style.css">
<script src="/moviebooking/js/index.js"></script>
<script src="https://kit.fontawesome.com/83eeaf9147.js" defer
	crossorigin="anonymous"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap"
	rel="stylesheet">
<title>${titleCont}</title>
</head>
<body onload="call_js()">
	<c:if test="${type == 0}">
		<jsp:include page="admin/layout/adheader.jsp" />
	</c:if>
	<c:if test="${type == 1}">
		<jsp:include page="member/layout/header.jsp" />
	</c:if>
	<hr>
	<main id="mainArea">
		<jsp:include page="${cont}" />
	</main>
	<footer>
		<div class="head">
			<div class="logo">푸터푸터</div>
			<div class="etc">기타등등</div>
		</div>
		<div class="content">서울시 성동구</div>
	</footer>
</body>
</html>