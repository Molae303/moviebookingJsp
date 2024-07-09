<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="content">
	<script src="/moviebooking/js/register.js?ver=2" defer></script>
	<h1>회원가입</h1>
	<form action="#" method="post" class="memberRegForm">
		<h4>사이트 이용정보 입력</h4>
		<table>
			<tr>
				<td class="title"><label for="id">아이디</label></td>
				<td class="content"><span>영문자, 숫자, _만 입력 가능 최소3자이상 입력하세요</span><br>
					<input type="text" name="id" id="id" class="inputArea" size="25"
					minlength="3" maxlength="12" required onkeyup="idCheck()">
					<button type="button" id="checkId">중복확인</button>
					<span class="requiredMsg"></span></td>
			</tr>
			<tr>
				<td class="title"><label for="passwd">비밀번호</label></td>
				<td class="content"><span>영문자, 숫자, 특수문자를 포함하여 6~12자를
						입력하세요</span><br> <input type="password" name="passwd" id="passwd"
					class="inputArea" size="25" minlength="3" maxlength="12" required
					onkeyup="pwCheck()"> <span class="requiredMsg"></span></td>
			</tr>
			<tr>
				<td class="title"><label for="repass">비밀번호 확인</label></td>
				<td class="content"><input type="password" name="repass"
					id="repass" class="inputArea" size="25" minlength="3"
					maxlength="12" required onkeyup="pwConfirmCheck()"> <span
					class="requiredMsg"></span></td>
			</tr>
		</table>
		<h4>개인정보 입력</h4>
		<table>
			<tr>
				<td class="title"><label for="name">이름</label></td>
				<td class="content"><span>영문자, 숫자, _만 입력 가능 최소3자이상 입력하세요</span><br>
					<input type="text" name="name" id="name" class="inputArea"
					size="25" minlength="3" maxlength="12" required> <span
					class="requiredMsg"></span></td>
			</tr>
			<tr>
				<td class="title"><label for="nickName">닉네임</label></td>
				<td class="content">
					<p>
						공백없이 한글,영문,숫자만 입력 가능 (한글2자, 영문4자 이상)<br> 닉네임을 바꾸시면 앞으로 1일
						이내에는 변경할 수 없습니다.
					</p> <input type="text" name="nickName" id="nickName" class="inputArea"
					size="25" minlength="2" maxlength="12" required> <span
					class="requiredMsg"></span>
				</td>
			</tr>
			<tr>
				<td class="title"><label for="email">E-mail</label></td>
				<td class="content"><input type="email" name="email" id="email"
					class="inputArea" size="70" minlength="5" maxlength="40" required>
					<button type="button" id="checkEmail">중복확인</button>
					<span class="requiredMsg"></span></td>
			</tr>
			<tr>
				<td class="title">가입경로</td>
				<td class="content2"><input type="radio" name="signUpPath"
					id="internet" value="internet"> <label for="internet">인터넷검색</label>
					<input type="radio" name="signUpPath" id="banner" value="banner">
					<label for="banner">배너광고</label> <input type="radio"
					name="signUpPath" id="news" value="news"> <label for="news">관련기사를보고</label>
					<input type="radio" name="signUpPath" id="introduction"
					value="introduction"> <label for="introduction">주변사람소개</label>
					<input type="radio" name="signUpPath" id="etc" value="etc">
					<label for="etc">기타</label></td>
			</tr>
			<tr>
				<td class="title"><label for="tel">전화번호</label></td>
				<td class="content"><input type="tel" name="tel" id="tel"
					class="inputArea" size="25" minlength="8" maxlength="40" required>
					<span class="requiredMsg"></span></td>
			</tr>
			<tr>
				<td class="title"><label for="phone">휴대폰번호</label></td>
				<td class="content"><input type="tel" name="phone" id="phone"
					class="inputArea" size="25" minlength="8" maxlength="40" required>
					<span class="requiredMsg"></span></td>
			</tr>
			<tr>
				<td class="title"><label for="birth">생년월일</label></td>
				<td class="content"><input type="date" name="birth" id="birth">
				</td>
			</tr>
			<tr>
				<td class="title" rowspan="4"><label for="address">주소</label></td>
				<td class="content"><input type="text" name="address"
					id="address" class="inputArea" size="25" minlength="5"
					maxlength="40"required >
					<button type="button" onclick="daumPostcode()">주소검색</button>
					<span class="requiredMsg"></span></td>
			</tr>
			<tr>
				<td class="content"><input type="text" name="address2"
					id="address2" class="inputArea" size="70" minlength="5"
					maxlength="40" required> <label for="address2">기본주소</label>
					<span class="requiredMsg"></span></td>
			</tr>
			<tr>
				<td class="content"><input type="text" name="address3"
					id="address3" size="70" minlength="2" maxlength="40"> <label
					for="address3">상세주소</label></td>
			</tr>
			<tr>
				<td class="content"><input type="text" name="address4"
					id="address4" size="70" minlength="2" maxlength="40"> <label
					for="address4">창고함목</label></td>
			</tr>
		</table>
		<h4>기타 개인설정</h4>
		<table>
			<tr>
				<td class="title"><label for="kakao">카카오톡 메세지</label></td>
				<td class="content"><input type="checkbox" name="kakao"
					id="kakao"> <label for="kakao">카카오톡 메세지를 받겠습니다.</label> <span>
						> 수신체크를 하시면 세일 소식을 가장 먼저 받아보실 수 있습니다.</span></td>
			</tr>
			<tr>
				<td class="title"><label for="mailing">메일링서비스</label></td>
				<td class="content"><input type="checkbox" name="mailing"
					id="mailing"> <label for="mailing">정보 메일을 받곘습니다.</label></td>
			</tr>
			<tr>
				<td class="title"><label for="sms">SMS 수신여부</label></td>
				<td class="content"><input type="checkbox" name="sms" id="sms">
					<label for="sms">휴대폰 문자메세지를 받겠습니다.</label></td>
			</tr>
			<tr>
				<td class="title"><label for="infoOpen">정보공개</label></td>
				<td class="content"><span>정보공개를 바꾸시면 앞으로 0일 이내에는 변경이
						안됩니다.</span><br> <input type="checkbox" name="infoOpen"
					id="infoOpen"> <label for="infoOpen">다른분들이 나의 정보를 볼
						수 있도록 합니다.</label></td>
			</tr>
			<tr>
				<td class="title"><label for="">자동등록방지</label></td>
				<td class="content3">
					<div>
						<span>XHYwW2nq</span>
					</div> <input type="button" value="코드 생성"> <input type="text"
					name="compare" id="compare" class="inputArea" required> <input
					type="button" value="비교"> <br> <span>자동등록방지 문자와
						숫자를 순서대로 입력해주세요</span> <span class="requiredMsg"></span>
				</td>
			</tr>
		</table>
		<div class="footer">
			<button onclick="process()">회원가입</button>
			<button type="button" onclick="window.location.href='/moviebooking/index.do'">취소</button>
		</div>
	</form>
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>