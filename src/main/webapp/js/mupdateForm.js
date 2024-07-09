document.addEventListener('DOMContentLoaded', function() {
	// 수정버튼 클릭
	var updateProcessButton = document.getElementById('updateProcess');
	updateProcessButton.addEventListener('click', function() {
		var query = {
			id: document.getElementById('id').value,
			nickName: document.getElementById('nickName').value,
			email: document.getElementById('email').value,
			tel: document.getElementById('tel').value,
			phone: document.getElementById('phone').value,
			birth: document.getElementById('birth').value,
			address: document.getElementById('address').value,
			address2: document.getElementById('address2').value,
			address3: document.getElementById('address3').value,
			address4: document.getElementById('address4').value
		};
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/moviebooking/updatePro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var data = xhr.responseText;
				var str1 = '<p id="ck">';
				var loc = data.indexOf(str1);
				var len = str1.length;
				var check = data.substr(loc + len, 1);
				if (check == '1') {
					alert('회원정보가 수정되었습니다.');
					window.location.href = '/moviebooking/index.do';
				} else {
					alert('회원정보수정에 오류가있습니다.');
					window.location.href = '/moviebooking/index.do';
				}
			} else if (xhr.readyState === 4) {
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
	});
});