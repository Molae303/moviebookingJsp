document.addEventListener('DOMContentLoaded', function() {
	let adLoginButton = document.getElementById('adLogin');
	adLoginButton.addEventListener('click', function login() {
		var query = {
			id: document.getElementById("id").value,
			passwd: document.getElementById("passwd").value
		};
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "/moviebooking/ad/loginPro.do", true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				window.location.href = "/moviebooking/ad/main.do";
			}
		};
		xhr.send("id=" + encodeURIComponent(query.id) + "&passwd=" +
			encodeURIComponent(query.passwd));
	});
});