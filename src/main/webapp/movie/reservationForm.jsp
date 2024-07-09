<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%	
String movieCd = null;
if(request.getAttribute("movieCd")!=null){
		movieCd = (String)request.getAttribute("movieCd");
}
%>
<script src="/moviebooking/js/reservationform.js?ver=2">
</script>
 <div class="reservation">
      <form action="#" method="post">
      	<input type="hidden" id="movieCd" value="<%if(movieCd != null){%><%= movieCd%><%}%>">
        <input type="hidden" id="cinemaId" value="">
        <input type="hidden" id="screenId" value="">
        <input type="hidden" id="selectDt" value="">
        <div class="header">
          <h2>영화예매</h2>
          <button type="button"><i class="fa-solid fa-rotate-right"></i> 예매다시하기</button>
        </div>
        <div class="content">
          <div class="cinemaList">
            <h3>영화관</h3>
            <div class="list">
              <ul>
              <c:forEach var="cinema" items="${cinemaList}">
                <li><button type="button" class="cinemaSelectBtn" data-id="${cinema.id}">${cinema.name}</button></li>
              </c:forEach>
              </ul>
            </div>
          </div>
          <div class="movieList">
            <h3>영화</h3>
            <div class="tab"><a href="#">예매율 순</a> <a href="#">가나다 순</a></div>
            <div class="list">
              <ul>
            	<c:forEach var="movie" items="${movieList}">
                <li>
                	<button type="button" class="movieSelectBtn" data-cd="${movie.movieCd}">
                		<c:choose>
											<c:when test="${movie.audits.contains('12')}">
												<img src="/moviebooking/images/movie/audits/12.svg" alt="등급표시">
											</c:when>		
											<c:when test="${movie.audits.contains('15')}">
												<img src="/moviebooking/images/movie/audits/15.svg" alt="등급표시">
											</c:when>		
											<c:when test="${movie.audits.contains('19')}">
												<img src="/moviebooking/images/movie/audits/19.svg" alt="등급표시">
											</c:when>
											<c:otherwise>
												<img src="/moviebooking/images/movie/audits/all.svg" alt="등급표시">
											</c:otherwise>
										</c:choose> 
										${movie.movieNm}
                	</button>
                </li>
              </c:forEach>
              </ul>
            </div>
          </div>
          <div class="dateList">
            <div class="list">
              <ul>
              	<c:forEach var="dateInfo" items="${dateList}" varStatus="i">
	              	<c:if test="${beforeMonth != dateInfo.date.month}">
	              		<li class="month"><fmt:formatDate value="${dateInfo.date}" pattern="yyyy.MM"/></li>
	              	</c:if>
	              	<c:choose>
	              		<c:when test="${dateInfo.day == '토'}">
	              			<li class="saturday"><button type="button" class="dateSelectBtn" data-dt="<fmt:formatDate value='${dateInfo.date}' pattern='yyyy.MM.dd (E)'/>">${dateInfo.day} <b>${dateInfo.date.date}</b></button></li>
	              		</c:when>
	              		<c:when test="${dateInfo.day == '일'}">
	              			<li class="sunday"><button type="button" class="dateSelectBtn" data-dt="<fmt:formatDate value='${dateInfo.date}' pattern='yyyy.MM.dd (E)'/>">${dateInfo.day} <b>${dateInfo.date.date}</b></button></li>
	              		</c:when>
	              		<c:otherwise>
		              		<li><button type="button" class="dateSelectBtn" data-dt="<fmt:formatDate value='${dateInfo.date}' pattern='yyyy.MM.dd (E)'/>">${dateInfo.day} <b>${dateInfo.date.date}</b></button></li>
	              		</c:otherwise>
	              	</c:choose>
              		<c:set var="beforeMonth" value="${dateInfo.date.month}"></c:set>
              	</c:forEach>
              </ul>
            </div>
          </div>
          <div class="aside">
            <div class="time">
              <h3>상영시간</h3>
              <div class="list">
                <h4>1관</h4>
                <ul>
                  <li>
                    <button type="button"><div class="time">13:00~14:36</div><div class="seat">86/<span>89석</span></div></button>
                  </li>
                  <li>
                    <button type="button"><div class="time">13:00~14:36</div><div class="seat">86/<span>89석</span></div></button>
                  </li>
                  <li>
                    <button type="button"><div class="time">13:00~14:36</div><div class="seat">86/<span>89석</span></div></button>
                  </li>
                  <li>
                    <button type="button"><div class="time">13:00~14:36</div><div class="seat">86/<span>89석</span></div></button>
                  </li>
                </ul>
                <h4>2관</h4>
                <ul>
                  <li>
                    <button type="button"><div class="time">13:00~14:36</div><div class="seat">86/<span>89석</span></div></button>
                  </li>
                  <li>
                    <button type="button"><div class="time">13:00~14:36</div><div class="seat">86/<span>89석</span></div></button>
                  </li>
                  <li>
                    <button type="button"><div class="time">13:00~14:36</div><div class="seat">86/<span>89석</span></div></button>
                  </li>
                  <li>
                    <button type="button"><div class="time">13:00~14:36</div><div class="seat">86/<span>89석</span></div></button>
                  </li>
                </ul>
              </div>
            </div>
            <div class="reserveInfo">
              <div class="info">
                <div class="movieImage"><img id="movieImage" src="" alt="Movie Image"></div>
                <div class="movieInfo">
                  <div class="movieNm"></div>
                  <ul>
                    <li><span class="info">영화관</span><span class="cinemaNm"></span></li>
                    <li><span class="info">상영관</span><span class="screenNm"></span></li>
                    <li><span class="info">상영등급</span><span class="audits"></span></li>
                    <li><span class="info">상영일</span><span class="screeningDt"></span></li>
                    <li><span class="info">상영시간</span><span class="startTm"></span>~<span class="endTm"></span></li>
                  </ul>
                </div>
              </div>
            </div>
            <div class="btn">
              <button type="button">인원/좌석 선택</button>
            </div>
          </div>
        </div>
      </form>
    </div>