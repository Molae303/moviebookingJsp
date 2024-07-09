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
<div class="userList">
	<h1>회원 목록</h1>
	<form id="userForm" method="post" action="/moviebooking/ad/deleteUser.do">
		<table>
			<thead>
				<tr>
					<th>선택</th>
					<th>아이디</th>
					<th>이름</th>
					<th>이메일</th>
					<th>핸드폰번호</th>
					<th>생년월일</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="member" items="${userList}">
					<tr>
						<td><input type="checkbox" class="userCheckbox" id="selectUser" name="selectUser"
							value="${member.id}"></td>
						<td>${member.id}</td>
						<td>${member.name}</td>
						<td>${member.email}</td>
						<td>${member.phone}</td>
						<td>${member.birth}</td>
						<td><fmt:formatDate value="${member.regDate}" type="date" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="buttons">
			<button type="submit">선택 삭제</button>
		</div>
	</form>
</div>