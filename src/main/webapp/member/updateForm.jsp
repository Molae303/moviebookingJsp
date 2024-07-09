<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content">
	<script src="/moviebookingdb/js/register.js" defer></script>
	<script src="/moviebooking/js/mupdateForm.js"></script>
	<h1>회원정보수정</h1>
	<form action="#" method="post" class="memberRegForm">
		<h4>사이트 이용정보</h4>
		<table>
			<tr>
				<td class="title"><label for="id">아이디</label></td>
				<td class="content"><span>영문자, 숫자, _만 입력 가능 최소3자이상 입력하세요</span><br>
					<input type="text" name="id" id="id" class="inputArea" size="25"
					minlength="3" maxlength="12" readonly value="${id}"> <span
					class="requiredMsg"></span></td>
			</tr>
		</table>
		<h4>개인정보 입력</h4>
		<table>
			<tr>
				<td class="title"><label for="name">이름</label></td>
				<td class="content"><span>영문자, 숫자, _만 입력 가능 최소3자이상 입력하세요</span><br>
					<input type="text" name="name" id="name" class="inputArea"
					size="25" minlength="3" maxlength="12" readonly value="${mvo.getName()}"> <span
					class="requiredMsg"></span></td>
			</tr>
			<tr>
				<td class="title"><label for="nickName">닉네임</label></td>
				<td class="content">
					<p>
						공백없이 한글,영문,숫자만 입력 가능 (한글2자, 영문4자 이상)<br> 닉네임을 바꾸시면 앞으로 1일
						이내에는 변경할 수 없습니다.
					</p> <input type="text" name="nickName" id="nickName" class="inputArea"
					size="25" minlength="2" maxlength="12" required value="${mvo.getNickName()}"> <span
					class="requiredMsg"></span>
				</td>
			</tr>
			<tr>
				<td class="title"><label for="email">E-mail</label></td>
				<td class="content"><input type="email" name="email" id="email"
					class="inputArea" size="70" minlength="5" maxlength="40" required value="${mvo.getEmail()}">
					<span class="requiredMsg"></span></td>
			</tr>
			<tr>
				<td class="title"><label for="tel">전화번호</label></td>
				<td class="content"><input type="tel" name="tel" id="tel"
					class="inputArea" size="25" minlength="8" maxlength="40" required value="${mvo.getTel()}">
					<span class="requiredMsg"></span></td>
			</tr>
			<tr>
				<td class="title"><label for="phone">휴대폰번호</label></td>
				<td class="content"><input type="tel" name="phone" id="phone"
					class="inputArea" size="25" minlength="8" maxlength="40" required value="${mvo.getPhone()}">
					<span class="requiredMsg"></span></td>
			</tr>
			<tr>
				<td class="title"><label for="birth">생년월일</label></td>
				<td class="content"><input type="date" name="birth" id="birth" value="${mvo.getBirth()}">
				</td>
			</tr>
			<tr>
				<td class="title" rowspan="4"><label for="address">주소</label></td>
				<td class="content"><input type="text" name="address"
					id="address" class="inputArea" size="25" minlength="5"
					maxlength="40"required value="${mvo.getAddress()}"> 
					<button onclick="daumPostcode()">주소검색</button>
					<span class="requiredMsg"></span></td>
			</tr>
			<tr>
				<td class="content"><input type="text" name="address2"
					id="address2" class="inputArea" size="70" minlength="5"
					maxlength="40" required value="${mvo.getAddress2()}"> <label for="address2">기본주소</label>
					<span class="requiredMsg"></span></td>
			</tr>
			<tr>
				<td class="content"><input type="text" name="address3"
					id="address3" size="70" minlength="2" maxlength="40" value="${mvo.getAddress3()}"> <label
					for="address3">상세주소</label></td>
			</tr>
			<tr>
				<td class="content"><input type="text" name="address4"
					id="address4" size="70" minlength="2" maxlength="40" value="${mvo.getAddress4()}"> <label
					for="address4">창고함목</label></td>
			</tr>
		</table>
		<div class="footer">
			<button type="button" id="updateProcess">수정</button>
			<button type="button" onclick="window.location.href='/moviebooking/index.do'">취소</button>
		</div>
	</form>
</div>