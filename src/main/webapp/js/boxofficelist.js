document.addEventListener('DOMContentLoaded', function() {
	let apiLoadButton = document.getElementById('apiLoad');
	apiLoadButton.addEventListener('click', function login() {
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "/moviebooking/ad/movie/boxofficeApiLoad.do", true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				window.location.href = "/moviebooking/ad/movie/boxofficeList.do";
			}
		};
		xhr.send();
	});
});