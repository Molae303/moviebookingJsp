document.addEventListener('DOMContentLoaded', function() {
	// 확인버튼(비밀번호 한번더 확인)
	var confirmButton = document.getElementById('confirmButton');
	confirmButton.addEventListener('click', function() {
		var query = {
			id: document.getElementById('id').value,
			passwd: document.getElementById('passwd').value,
		};
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/moviebooking/updateConfirm.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				var data = xhr.responseText;
				var str1 = '<p id="ck">';
				var loc = data.indexOf(str1);
				var len = str1.length;
				var check = data.substr(loc + len, 1);
				if (check == '1') {
					window.location.href = '/moviebooking/updateForm.do?id='+ encodeURIComponent(query.id);
				} else {
					alert('비밀번호 틀림.');
					document.getElementById('passwd').value = '';
					document.getElementById('passwd').focus();
				}
			} else if (xhr.readyState === 4) {
				console.error('Error:', xhr.statusText);
			}
		};
		xhr.onerror = function() {
			console.error('Request failed');
		};
		xhr.send("id=" + encodeURIComponent(query.id) +
			"&passwd=" + encodeURIComponent(query.passwd)
		);
	});
});