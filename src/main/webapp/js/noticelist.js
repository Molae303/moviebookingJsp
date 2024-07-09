document.addEventListener('DOMContentLoaded', function() {
	let noticeSearchButton = document.getElementById('noticeSearch');

	noticeSearchButton.addEventListener('click', function() {
		let searchCategory = document.getElementById("searchCategory").value
		let keyword = document.getElementById("searchKeyword").value
		
		let query = (document.getElementById('type').value === '0')?("/moviebooking/ad/noticeSearch.do?searchCategory="):("/moviebooking/noticeSearch.do?searchCategory=");
		query = query + searchCategory+"&keyword="+keyword;
		window.location.href = query;
	});
});