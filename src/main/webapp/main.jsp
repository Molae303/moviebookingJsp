<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="slideshow">
	<div class="slideshow_slides">
		<a href="#"><img src="/moviebooking/images/main/slide05.png" alt="slide1"></a> <a
			href="#"><img src="/moviebooking/images/main/slide02.png" alt="slide2"></a> <a
			href="#"><img src="/moviebooking/images/main/slide03.png" alt="slide3"></a> <a
			href="#"><img src="/moviebooking/images/main/slide04.png" alt="slide4"></a>
	</div>
	<div class="slideshow_nav">
		<a href="" class="prev"><i class="fa-solid fa-circle-left"></i></a> <a
			href="" class="next"><i class="fa-solid fa-circle-right"></i></a>
	</div>
	<div class="indicator">
		<a href="#" class="active"><i class="fa-solid fa-circle-dot"></i></a>
		<a href="#"><i class="fa-solid fa-circle-dot"></i></a> <a href="#"><i
			class="fa-solid fa-circle-dot"></i></a> <a href="#"><i
			class="fa-solid fa-circle-dot"></i></a>
	</div>
</div>
<div class="content">
	<h2>일반영화 TOP10</h2>
	<div class="top10">
		<div class="slideshow-type1">
			<div class="slideshow-type1_slides">
			<c:forEach var="movie" items="${movieList}">
				<c:if test="${type == 0}">
					<div class="item">
						<a href="/moviebooking/ad/movie/movieInfo.do?movieCd=${movie.movieCd}">
							<div class="img">
								<img src="<%=request.getContextPath()%>${movie.image}" alt="">
							</div>
							<div class="info">
								<h4>${movie.movieNm}</h4>
								<span>예매율</span><b>${movie.salesShare}</b>
							</div>
						</a>
							<div class="btns">
								<button type="button" onclick="window.location.href='/moviebooking/ad/movie/movieInfo.do?movieCd=${movie.movieCd}'">영화정보</button>
								<button type="button" >예매하기<i class="fa-solid fa-arrow-right"></i></button>
							</div>
					</div>
				</c:if>
				<c:if test="${type==1}">
					<div class="item">
							<a href="/moviebooking/movie/movieInfo.do?movieCd=${movie.movieCd}">
								<div class="img">
									<img src="<%=request.getContextPath()%>${movie.image}" alt="">
								</div>
								<div class="info">
									<h4>${movie.movieNm}</h4>
									<span>예매율</span><b>${movie.salesShare}</b>
								</div>
							</a>
								<div class="btns">
									<button type="button" onclick="window.location.href='/moviebooking/movie/movieInfo.do?movieCd=${movie.movieCd}'">영화정보</button>
									<button type="button" onclick="window.location.href='/moviebooking/movie/reservationForm.do?movieCd=${movie.movieCd}'">예매하기<i class="fa-solid fa-arrow-right"></i></button>
								</div>
						</div>
				</c:if>
			</c:forEach>
			</div>
		</div>
		<div class="type1_nav">
			<a href="" class="type1_prev"><i class="fa-solid fa-angles-left"></i></a>
      <a href="" class="type1_next"><i class="fa-solid fa-angles-right"></i></a>
		</div>
	</div>
	<h2>독립·예술영화 TOP10</h2>
	<div class="readyon">
		<div class="slideshow-type2">
			<div class="slideshow-type2_slides">
				<div class="item">
					<a href="#">
						<div class="img">
							<img src="/moviebooking/images/main/top10_slide01.jpg" alt="">
						</div>
						<div class="info">
							<h4>원더랜드</h4>
							<span>예매율</span><b>7.89%</b>
						</div>
						<div class="btns">
							<button type="button">영화정보</button>
							<button type="button">예매하기<i class="fa-solid fa-arrow-right"></i></button>
						</div>
					</a>
				</div>
				<div class="item">
					<a href="#">
						<div class="img">
							<img src="/moviebooking/images/main/top10_slide01.jpg" alt="">
						</div>
						<div class="info">
							<h4>원더랜드</h4>
							<span>예매율</span><b>7.89%</b>
						</div>
						<div class="btns">
							<button type="button">영화정보</button>
							<button type="button">예매하기<i class="fa-solid fa-arrow-right"></i></button>
						</div>
					</a>
				</div>
				<div class="item">
					<a href="#">
						<div class="img">
							<img src="/moviebooking/images/main/top10_slide01.jpg" alt="">
						</div>
						<div class="info">
							<h4>원더랜드</h4>
							<span>예매율</span><b>7.89%</b>
						</div>
						<div class="btns">
							<button type="button">영화정보</button>
							<button type="button">예매하기<i class="fa-solid fa-arrow-right"></i></button>
						</div>
					</a>
				</div>
				<div class="item">
					<a href="#">
						<div class="img">
							<img src="/moviebooking/images/main/top10_slide01.jpg" alt="">
						</div>
						<div class="info">
							<h4>원더랜드</h4>
							<span>예매율</span><b>7.89%</b>
						</div>
						<div class="btns">
							<button type="button">영화정보</button>
							<button type="button">예매하기<i class="fa-solid fa-arrow-right"></i></button>
						</div>
					</a>
				</div>
				<div class="item">
					<a href="#">
						<div class="img">
							<img src="/moviebooking/images/main/top10_slide01.jpg" alt="">
						</div>
						<div class="info">
							<h4>원더랜드</h4>
							<span>예매율</span><b>7.89%</b>
						</div>
						<div class="btns">
							<button type="button">영화정보</button>
							<button type="button">예매하기<i class="fa-solid fa-arrow-right"></i></button>
						</div>
					</a>
				</div>
				<div class="item">
					<a href="#">
						<div class="img">
							<img src="/moviebooking/images/main/top10_slide01.jpg" alt="">
						</div>
						<div class="info">
							<h4>원더랜드</h4>
							<span>예매율</span><b>7.89%</b>
						</div>
						<div class="btns">
							<button type="button">영화정보</button>
							<button type="button">예매하기<i class="fa-solid fa-arrow-right"></i></button>
						</div>
					</a>
				</div>
				<div class="item">
					<a href="#">
						<div class="img">
							<img src="/moviebooking/images/main/top10_slide01.jpg" alt="">
						</div>
						<div class="info">
							<h4>원더랜드</h4>
							<span>예매율</span><b>7.89%</b>
						</div>
						<div class="btns">
							<button type="button">영화정보</button>
							<button type="button">예매하기<i class="fa-solid fa-arrow-right"></i></button>
						</div>
					</a>
				</div>
				<div class="item">
					<a href="#">
						<div class="img">
							<img src="/moviebooking/images/main/top10_slide01.jpg" alt="">
						</div>
						<div class="info">
							<h4>원더랜드</h4>
							<span>예매율</span><b>7.89%</b>
						</div>
						<div class="btns">
							<button type="button">영화정보</button>
							<button type="button">예매하기<i class="fa-solid fa-arrow-right"></i></button>
						</div>
					</a>
				</div>
				<div class="item">
					<a href="#">
						<div class="img">
							<img src="/moviebooking/images/main/top10_slide01.jpg" alt="">
						</div>
						<div class="info">
							<h4>원더랜드</h4>
							<span>예매율</span><b>7.89%</b>
						</div>
						<div class="btns">
							<button type="button">영화정보</button>
							<button type="button">예매하기<i class="fa-solid fa-arrow-right"></i></button>
						</div>
					</a>
				</div>
				<div class="item">
					<a href="#">
						<div class="img">
							<img src="/moviebooking/images/main/top10_slide01.jpg" alt="">
						</div>
						<div class="info">
							<h4>원더랜드</h4>
							<span>예매율</span><b>7.89%</b>
						</div>
						<div class="btns">
							<button type="button">영화정보</button>
							<button type="button">예매하기<i class="fa-solid fa-arrow-right"></i></button>
						</div>
					</a>
				</div>
			</div>
		</div>
		<div class="type2_nav">
			 <a href="" class="type2_prev"><i class="fa-solid fa-angles-left"></i></a>
       <a href="" class="type2_next"><i class="fa-solid fa-angles-right"></i></a>
		</div>
	</div>
	<div class="event"></div>
	<div class="mainNotice">
		<div class="notice">
	    <h2>공지사항</h2>
	    <c:if test="${type==1}">
	    <a href="/moviebooking/noticeList.do"><i class="fa-solid fa-list"></i></a>
	    </c:if>
	    <c:if test="${type==0}">
	    <a href="/moviebooking/ad/noticeList.do"><i class="fa-solid fa-list"></i></a>
	    </c:if>
	    <ul>
	    <c:forEach var="article" items="${articleList}">
	    <c:if test="${type==1}">
	      <li>
	        <a href="/moviebooking/noticeContent.do?num=${article.num}&pageNum=1">
	          <p>[${article.category}] ${article.subject}</p>
	          <span><fmt:formatDate value="${article.regDate}" type="date"/></span>
	        </a>
	      </li>
	    </c:if>
	    <c:if test="${type==0}">
	      <li>
	        <a href="/moviebooking/ad/noticeContent.do?num=${article.num}&pageNum=1">
	          <p>[${article.category}] ${article.subject}</p>
	          <span><fmt:formatDate value="${article.regDate}" type="date"/></span>
	        </a>
	      </li>
	    </c:if>
	    </c:forEach>
	    </ul>
	  </div>
	  <div class="guide">
	    <a href="#">
	      <img src="/moviebooking/images/main/guide.jpg" alt="">
	    </a>
	  </div>
	</div>
</div>