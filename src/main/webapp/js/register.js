document.addEventListener('DOMContentLoaded', function() {
	let checkIdButton = document.getElementById('checkId');
	checkIdButton.addEventListener('click', function() {
		let idValue = document.getElementById('id').value;
		if (idValue) {
			let query = { id: idValue };
			let xhr = new XMLHttpRequest();
			xhr.open("POST", "/moviebooking/registerConfirmId.do", true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
					let data = xhr.responseText;
					let str1 = '<p id="ck">';
					let loc = data.indexOf(str1);
					let len = str1.length;
					let check = data.substr(loc + len, 1);
					if (check === '1') {
						alert('이미 사용중인 아이디입니다.');
						document.getElementById('id').value = '';
					} else {
						let result = confirm(`${idValue} 는 사용 가능합니다.\n 사용하시곘습니까?`)
						if(!result){
							document.getElementById('id').value = '';
						}
					}
				} else {
					console.error('Error:', xhr.statusText);
				}
			};
			xhr.send("id=" + encodeURIComponent(query.id));
		} else { // 아이디를 입력하지 않고 [ID 중복확인] 버튼을 클릭한 경우
			alert('사용할 아이디를 입력');
			document.getElementById('id').focus();
		}
	});
	
	let checkEmailButton = document.getElementById('checkEmail');
	checkEmailButton.addEventListener('click', function() {
		let emailValue = document.getElementById('email').value;
		if (emailValue) {
			let query = { email: emailValue };
			let xhr = new XMLHttpRequest();
			xhr.open("POST", "/moviebooking/registerConfirmEmail.do", true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
					let data = xhr.responseText;
					let str1 = '<p id="ck">';
					let loc = data.indexOf(str1);
					let len = str1.length;
					let check = data.substr(loc + len, 1);
					if (check === '1') {
						alert('이미 사용중인 이메일입니다.');
						document.getElementById('email').value = '';
					} else {
						let result = confirm(`${emailValue} 는 사용 가능합니다.\n 사용하시곘습니까?`)
						if(!result){
							document.getElementById('email').value = '';
						}
					}
				} else {
					console.error('Error:', xhr.statusText);
				}
			};
			xhr.send("email=" + encodeURIComponent(query.email));
		} else { // 아이디를 입력하지 않고 [ID 중복확인] 버튼을 클릭한 경우
			alert('이메일을 입력해주세요');
			document.getElementById('email').focus();
		}
	});

});

//다음주소 api 함수
function daumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			let addr = '';
			let extraAddr = '';

			if (data.userSelectedType === 'R') {
				addr = data.roadAddress;
			} else {
				addr = data.jibunAddress;
			}

			if (data.userSelectedType === 'R') {

				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraAddr += data.bname;
				}

				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}

				if (extraAddr !== '') {
					extraAddr = ' (' + extraAddr + ')';
				}
				document.getElementById('address').value = data.zonecode;
				document.getElementById('address2').value = addr;

				document.getElementById('address3').focus();

			}
		}
	}).open();
}

function requiredMsg() {
	const requiredInput = document.querySelectorAll('input[required]');
	requiredInput.forEach((input) => {
		input.addEventListener("blur", () => {
			if (input.value === "") {
				input.parentElement.lastElementChild.innerHTML = "필수입력항목입니다.";
				return false;
			} else {
				input.parentElement.lastElementChild.innerHTML = "OK";
				return true;
			}
		});
	});
}
document.addEventListener("DOMContentLoaded", requiredMsg());

function idCheck() {
	const id = document.querySelector("#id");
	let idExp = /^[0-9a-zA-Z_]{3,11}$/;
	if (id.value === "") {
		id.parentElement.lastElementChild.innerHTML = "필수입력항목입니다.";
		return false;
	} else if (!id.value.match(idExp)) {
		id.parentElement.lastElementChild.innerHTML = "아이디 조건에 맞지 않습니다."
		return false;
	} else {
		id.parentElement.lastElementChild.innerHTML = "ok";
		return true;
	}
}
function pwCheck() {
	const passwd = document.querySelector("#passwd");
	let pwExp = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,16}$/;
	if (passwd.value === "") {
		passwd.parentElement.lastElementChild.innerHTML = "필수입력항목입니다.";
		return false;
	} else if (!passwd.value.match(pwExp)) {
		passwd.parentElement.lastElementChild.innerHTML = "비밀번호 조건에 맞지 않습니다."
		return false;
	} else {
		passwd.parentElement.lastElementChild.innerHTML = "ok";
		return true;
	}
}
function pwConfirmCheck() {
	const passwd = document.querySelector("#passwd")
	const repass = document.querySelector("#repass")
	if (repass.value === "") {
		repass.parentElement.lastElementChild.innerHTML = "필수입력항목입니다.";
		return false;
	} else if (repass.value === passwd.value) {
		repass.parentElement.lastElementChild.innerHTML = "ok"
		return true;
	} else {
		repass.parentElement.lastElementChild.innerHTML = "비밀번호가 일치하지 않습니다.";
		return false;
	}
}
function process() {
	if (idCheck() && pwCheck() && pwConfirmCheck()) {
		let signUpPathValue = document.querySelector('input[name="signUpPath"]:checked').value;

		let query = {
			id: document.getElementById('id').value,
			passwd: document.getElementById('passwd').value,
			name: document.getElementById('name').value,
			nickName: document.getElementById('nickName').value,
			email: document.getElementById('email').value,
			signUpPath: signUpPathValue,
			tel: document.getElementById('tel').value,
			phone: document.getElementById('phone').value,
			birth: document.getElementById('birth').value,
			address: document.getElementById('address').value,
			address2: document.getElementById('address2').value,
			address3: document.getElementById('address3').value,
			address4: document.getElementById('address4').value
		};
		let xhr = new XMLHttpRequest();
		xhr.open('POST', '/moviebooking/registerPro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				alert(query.id + "님 가입완료");
				window.location.href = '/moviebooking/index.do';
			} else if (xhr.readyState !== 4) {
				console.error('Error:', xhr.statusText);
			}
		};
		xhr.onerror = function() {
			console.error('Request failed');
		};
		let queryString = Object.keys(query)
			.map(function(key) {
				return encodeURIComponent(key) + '=' + encodeURIComponent(query[key]);
			})
			.join('&');
		xhr.send(queryString);
	} else {
		alert(`가입내용에 문제가 있습니다.\n
        아이디 = ${memberIdCheck()}\n
        비밀번호 = ${memberPwCheck()}\n
        비밀번호확인 = ${pwConfirmCheck()}\n
        이름 = ${memberNameCheck()}\n
        닉네임 = ${memberNicknameCheck()}`);
	}
}


