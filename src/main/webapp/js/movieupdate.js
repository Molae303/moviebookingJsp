document.addEventListener('DOMContentLoaded', function() {
	// 이미지를 포함한 영화 수정
	let movieCd = document.getElementById('movieCd').value;
	let form = document.getElementById('editForm');
	form.addEventListener('submit', function(event) {
		event.preventDefault();
		let formData = new FormData(form);
		let xhr = new XMLHttpRequest();
		xhr.open('POST', form.action, true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				window.location.href =
					"/moviebooking/ad/movie/movieInfo.do?movieCd="+movieCd;
			} else if (xhr.readyState !== 4) {
				console.error('Error:', xhr.statusText);
			}
		};
		xhr.onerror = function() {
			console.error('Request failed');
		};
		xhr.send(formData);
	});
})