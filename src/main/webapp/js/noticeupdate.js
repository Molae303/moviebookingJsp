document.addEventListener('DOMContentLoaded', function() {
	// 수정버튼 클릭
	let updateProcessButton = document.getElementById('updateProcess');
	updateProcessButton.addEventListener('click', function() {
		let query = {
			num: document.getElementById('num').value,
			subject: document.getElementById('subject').value,
			category: document.getElementById('category').value,
			content: document.getElementById('content').value,
		};
		let xhr = new XMLHttpRequest();
		xhr.open('POST', '/moviebooking/ad/noticeUpdatePro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				let data = xhr.responseText;
				let str1 = '<p id="ck">';
				let loc = data.indexOf(str1);
				let len = str1.length;
				let check = data.substr(loc + len, 1);
				if (check === '1') {
					alert('공지사항 수정 완료');
					window.location.href = '/moviebooking/ad/noticeContent.do?num='+document.getElementById("num").value+'&pageNum='+document.getElementById("pageNum").value;
				} else {
					alert('공지사항 수정에 오류가있습니다.');
					window.location.href = '/moviebooking/ad/noticeList.do';
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