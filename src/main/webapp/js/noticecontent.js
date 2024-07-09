document.addEventListener('DOMContentLoaded', function() {
	let deleteProcessButton = document.getElementById('deleteProcess');
	deleteProcessButton.addEventListener('click', function() {
		let result = confirm('확인시 글삭제가 진행됩니다\n계속 진행하시겠습니까?');
		if (result) {
			let query = "/moviebooking/ad/noticeDeletePro.do?num=" + deleteProcessButton.name;
			window.location.href = query;
		}
	});
});