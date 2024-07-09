document.addEventListener('DOMContentLoaded', function() {
	let uLoginButton = document.getElementById('uLogin');
	uLoginButton.addEventListener('click', function() {
		let query = {
			id: document.getElementById('cid').value,
			passwd: document.getElementById('cpasswd').value
		};
		let xhr = new XMLHttpRequest();
		xhr.open('POST', '/moviebooking/loginPro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				let data = xhr.responseText;
				let str1 = '<p id="ck">';
				let loc = data.indexOf(str1);
				let len = str1.length;
				let check = data.substr(loc + len, 1);
				if (check == '1') {
					window.location.href = '/moviebooking/index.do';
				} else if (check == '0') {
					alert('비밀번호 틀림');
					document.getElementById('cpasswd').value = '';
				} else {
					alert('아이디 틀림');
					document.getElementById('cid').value = '';
					document.getElementById('cpasswd').value = '';
				}
			} else if (xhr.readyState === 4) {
				console.error('Error:', xhr.statusText);
			}
		};
		xhr.onerror = function() {
			console.error('Request failed');
		};
		xhr.send("id=" + encodeURIComponent(query.id) +
			"&passwd=" + encodeURIComponent(query.passwd));
	});
	
	let uLogout = document.getElementById('uLogout');
	uLogout.addEventListener('click', function() {
		fetch('/moviebooking/logout.do', {
			method: 'POST'
		}).then(function(response) {
			return response.text();
		}).then(function(data) {
			window.location.href = '/shoppingmall/index.do';
		}).catch(function(error) {
			console.error('Error:', error);
		});
	});
});