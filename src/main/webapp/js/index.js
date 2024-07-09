// 콜백함수 (onLoad call back function)
function call_js() {
	
	//메뉴선택시 해당된메뉴만 active클래스를 추가한다.
  const navMenu = document.querySelectorAll('.headerNavMenu');
  navMenu.forEach((e) => {
		e.classList.remove('active');
    if(e.innerHTML === document.title){
      e.classList.add('active');
    }
  });
  
  // 메인슬라이드 함수
  // ui 객체참조변수 선언
  let slideshow = document.querySelector(".slideshow");
  let slideshow_slides = document.querySelector(".slideshow_slides");
  let slides = document.querySelectorAll(".slideshow_slides a");
  let prev = document.querySelector(".prev");
  let next = document.querySelector(".next");
  let indicators = document.querySelectorAll(".indicator a");
  //현재위치값, 시간설정, 슬라이드수
  let currentIndex = 0;
  let timer = null;
  let slideCount = slides.length;

  //이미지를 우측으로 배치시켜준다.
  for (let i = 0; i < slideCount; i++) {
    let newLeft = `${i * 100}%`;
    slides[i].style.left = newLeft;
  }
  // slideshow_slides 좌측으로 100% 이동
  function gotoSlide(index) {
    currentIndex = index;
    let newLeft = `${index * -100}%`;
    slideshow_slides.style.left = newLeft;
    indicators.forEach(e => e.classList.remove("active"));
    indicators[currentIndex].classList.add("active");
  }

  function startTimer() {
    timer = setInterval(() => {
      currentIndex += 1;
      let index = currentIndex % slideCount;
      gotoSlide(index);
    }, 5000);
  }
  startTimer();
  slideshow_slides.addEventListener("mouseover", function () {
    clearInterval(timer);
  });
  slideshow_slides.addEventListener("mouseout", function () {
    startTimer();
  });
  prev.addEventListener("mouseover", function () {
    clearInterval(timer);
  });
  next.addEventListener("mouseover", function () {
    clearInterval(timer);
  });

  prev.addEventListener("click", function (e) {
    e.preventDefault();
    currentIndex -= 1;
    if (currentIndex < 0) {
      currentIndex = (slideCount - 1);
    }
    gotoSlide(currentIndex);
  });

  next.addEventListener("click", function (e) {
    e.preventDefault();
    currentIndex += 1;
    if (currentIndex > (slideCount - 1)) {
      currentIndex = 0;
    }
    gotoSlide(currentIndex);
  });

  indicators.forEach((e) => {
    e.addEventListener("mouseover", () => clearInterval(timer));
  });

  for (let i = 0; i < indicators.length; i++) {
    indicators[i].addEventListener("click", (e) => {
      e.preventDefault();
      gotoSlide(i);
    });
  }

  //top10 슬라이드 함수
  let slidesItem = document.querySelectorAll(".slideshow-type1_slides .item");
  let type1Slides = document.querySelector(".slideshow-type1_slides");
  let type1Prev = document.querySelector(".type1_prev");
  let type1Next = document.querySelector(".type1_next");
  let currentIndex2 = 0;
  //이미지 우측 배치
  for (let i = 0; i < slidesItem.length; i++) {
    let newLeft = `${i * 225}px`;
    slidesItem[i].style.left = newLeft;
  }

  function gotoSlide2(index) {
    currentIndex2 = index;
    let newLeft = `${index * -225}px`;
    type1Slides.style.left = newLeft;
  }

  type1Prev.addEventListener("click", function (e) {
    e.preventDefault();
    currentIndex2 -= 1;
    if (currentIndex2 < 0) {
      currentIndex2 = (slidesItem.length - 5);
    }
    gotoSlide2(currentIndex2);
  });

  type1Next.addEventListener("click", function (e) {
    e.preventDefault();
    currentIndex2 += 1;
    if (currentIndex2 > (slidesItem.length - 5)) {
      currentIndex2 = 0;
    }
    gotoSlide2(currentIndex2);
  });

  //readyon 슬라이드 함수
  let slidesItem2 = document.querySelectorAll(".slideshow-type2_slides .item");
  let type2Slides = document.querySelector(".slideshow-type2_slides");
  let type2Prev = document.querySelector(".type2_prev");
  let type2Next = document.querySelector(".type2_next");
  let currentIndex3 = 0;
  //이미지 우측 배치
  for (let i = 0; i < slidesItem2.length; i++) {
    let newLeft = `${i * 225}px`;
    slidesItem2[i].style.left = newLeft;
  }

  function gotoSlide3(index) {
    currentIndex3 = index;
    let newLeft = `${index * -225}px`;
    type2Slides.style.left = newLeft;
  }

  type2Prev.addEventListener("click", function (e) {
    e.preventDefault();
    currentIndex3 -= 1;
    if (currentIndex3 < 0) {
      currentIndex3 = (slidesItem2.length - 5);
    }
    gotoSlide3(currentIndex3);
  });

  type2Next.addEventListener("click", function (e) {
    e.preventDefault();
    currentIndex3 += 1;
    if (currentIndex3 > (slidesItem2.length - 5)) {
      currentIndex3 = 0;
    }
    gotoSlide3(currentIndex3);
  });
}