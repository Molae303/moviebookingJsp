addEventListener("DOMContentLoaded", () => {
	const cinemaSelectBtn = document.querySelectorAll('.cinemaSelectBtn');
	const movieSelectBtn = document.querySelectorAll('.movieSelectBtn');
	const dateSelectBtn = document.querySelectorAll('.dateSelectBtn');

	if (document.getElementById('movieCd').value) {
		movieSelectBtn.forEach((item) => {
			if (item.dataset.cd === document.getElementById('movieCd').value) {
				item.classList.toggle('selected');
				fetch(`/moviebooking/getMovieInfo.do?movieCd=${document.getElementById('movieCd').value}`)
					.then(response => response.json())
					.then(data => updateReserveMovieInfo(data))
					.catch(error => console.error('Error getMovie info:', error));
			}
		});
	}

	dateSelectBtn.forEach((item) => {
		item.addEventListener('click', () => selectBtnClick(dateSelectBtn, item));
	});

	cinemaSelectBtn.forEach((item) => {
		item.addEventListener('click', () => selectBtnClick(cinemaSelectBtn, item));
	});

	movieSelectBtn.forEach((item) => {
		item.addEventListener('click', () => selectBtnClick(movieSelectBtn, item));
	});

	const movieCd = document.getElementById('movieCd');
	const cinemaId = document.getElementById('cinemaId');
	const selectDt = document.getElementById('selectDt');

	movieCd.addEventListener('change', () => {
		if (!movieCd.value) {
			updateReserveMovieInfo(null);
			return
		}
		fetch(`/moviebooking/getMovieInfo.do?movieCd=${movieCd.value}`)
			.then(response => response.json())
			.then(data => updateReserveMovieInfo(data))
			.catch(error => console.error('Error getMovie info:', error));
	});

	cinemaId.addEventListener('change', () => {
		console.log(cinemaId);
		if (!cinemaId.value) {
			updateReserveCinemaInfo(null);
			return;
		}
		fetch(`/moviebooking/getCinemaInfo.do?cinemaId=${cinemaId.value}`)
			.then(response => response.json())
			.then(data => updateReserveCinemaInfo(data))
			.catch(error => console.error('Error getCinema info:', error));
	});
	
	selectDt.addEventListener('change',()=>{
		if(!selectDt.value){
			updateReserveSelectDt(null);
			return;
		}
		updateReserveSelectDt(selectDt);
	});

});

function updateReserveMovieInfo(data) {
	const reserveInfo = document.querySelector('.reserveInfo');
	if (!data) {
		reserveInfo.querySelector('.movieNm').innerHTML = '';
		reserveInfo.querySelector('.audits').innerHTML = '';
		document.getElementById('movieImage').src = '';
	}
	reserveInfo.querySelector('.movieNm').innerHTML = data.movieNm;
	reserveInfo.querySelector('.audits').innerHTML = data.audits;
	document.getElementById('movieImage').src = `/moviebooking${data.image}`;
}

function updateReserveCinemaInfo(data) {
	const reserveInfo = document.querySelector('.reserveInfo');
	if (!data) {
		reserveInfo.querySelector('.cinemaNm').innerHTML = '';
	}
	reserveInfo.querySelector('.cinemaNm').innerHTML = data.cinemaNm;
}

function updateReserveSelectDt(data){
	const reserveInfo = document.querySelector('.reserveInfo');
	if(!data){
		reserveInfo.querySelector('.screeningDt').innerHTML = '';
	}
		reserveInfo.querySelector('.screeningDt').innerHTML = data.value;
}

//리스트버튼 선택클릭했을때 이벤트처리 한수
function selectBtnClick(SelectBtn, item) {
	if (item.classList.contains('selected')) {
		let key = item.className.substring(0, item.className.length - 18);
		switch (key) {
			case 'movie':
				document.getElementById('movieCd').value = '';
				document.getElementById('movieCd').dispatchEvent(new Event('change'));
				break;
			case 'cinema':
				document.getElementById('cinemaId').value = '';
				document.getElementById('cinemaId').dispatchEvent(new Event('change'));
				break;
			case 'date':
				document.getElementById('selectDt').value = '';
				document.getElementById('selectDt').dispatchEvent(new Event('change'));
				break;
			default:
				break;
		}
		item.classList.remove('selected');
		return;
	}
	if (item.classList.contains('disabled')) {
		return;
	}
	SelectBtn.forEach((e) => {
		e.classList.remove('selected');
	});
	item.classList.toggle('selected');
	let key = item.className.substring(0, item.className.length - 18);

	switch (key) {
		case 'movie':
			document.getElementById('movieCd').value = item.dataset.cd;
			document.getElementById('movieCd').dispatchEvent(new Event('change'));
			break;
		case 'cinema':
			document.getElementById('cinemaId').value = item.dataset.id;
			document.getElementById('cinemaId').dispatchEvent(new Event('change'));
			break;
		case 'date':
			document.getElementById('selectDt').value = item.dataset.dt;
			document.getElementById('selectDt').dispatchEvent(new Event('change'));
			break;
		default:
			break;
	}
}