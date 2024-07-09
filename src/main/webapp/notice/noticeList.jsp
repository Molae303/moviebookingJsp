<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="/moviebooking/js/noticelist.js">
</script>
<div class="noticeBoardList">
	<h1>공지사항</h1>
	<div class="notice-summary">
	<c:if test="${search != 1}">
		<span>총 <strong>${count}</strong>개의 게시글이 있습니다.</span>
	</c:if>
	<c:if test="${search == 1}">
		<span>&quot;<b>${keyword}</b>&quot; 검색된 게시글 수 <b>${count}</b>개</span>
	</c:if>
		<div class="search-box">
			<input type="hidden" id="type" value="${type}">
			<select name="searchCategory" id="searchCategory">
				<option value="subject">제목</option>
				<option value="content">내용</option>
			</select> <input type="text" id="searchKeyword" placeholder="검색어를 입력해주세요">
			<button type="button" id="noticeSearch">
				<i class="fa-solid fa-magnifying-glass"></i>
			</button>
		</div>
	</div>
	<table class="notice-list">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articleList}" varStatus="i">
				<tr>
				<c:if test="${type == 0}">
					<td>${number-i.index}</td>
					<td><a href="/moviebooking/ad/noticeContent.do?num=${article.num}&pageNum=${pageNum}">[${article.category}] ${article.subject}</a></td>
					<td>${article.writer}</td>
					<td><fmt:formatDate value="${article.regDate}" type="date" /></td>
					<td>${article.readcount }</td>
				</c:if>
				<c:if test="${type == 1}">
					<td>${number-i.index}</td>
					<td><a href="/moviebooking/noticeContent.do?num=${article.num}&pageNum=${pageNum}">[${article.category}] ${article.subject}</a></td>
					<td>${article.writer}</td>
					<td><fmt:formatDate value="${article.regDate}" type="date" /></td>
					<td>${article.readcount }</td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${search == 1}">
		<c:if test="${type == 0}">
			<div class="pagination">
			<c:if test="${startPage > pageBlock}">
				<a href="/moviebooking/ad/noticeSearch.do?searchCategory=${searchCategory}&keyword=${keyword}&pageNum=${startPage-pageBlock}">&laquo;</a> 
			</c:if>
			<c:forEach begin="${startPage}" end="${endPage}" step="1" varStatus="num">
				<a href="/moviebooking/ad/noticeSearch.do?searchCategory=${searchCategory}&keyword=${keyword}&pageNum=${num.count}" class="pageNum ${pageNum == num.count?'active':''}" >${num.count}</a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
				<a href="/moviebooking/ad/noticeSearch.do?searchCategory=${searchCategory}&keyword=${keyword}&pageNum=${startPage+pageBlock}">&raquo;</a>
			</c:if>
			</div>
		</c:if>
		<c:if test="${type == 1}">
			<div class="pagination">
			<c:if test="${startPage > pageBlock}">
				<a href="/moviebooking/noticeSearch.do?searchCategory=${searchCategory}&keyword=${keyword}&pageNum=${startPage-pageBlock}">&laquo;</a> 
			</c:if>
			<c:forEach begin="${startPage}" end="${endPage}" step="1" varStatus="num">
				<a href="/moviebooking/noticeSearch.do?searchCategory=${searchCategory}&keyword=${keyword}&pageNum=${num.count}" class="pageNum ${pageNum == num.count?'active':''}" >${num.count}</a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
				<a href="/moviebooking/noticeSearch.do?searchCategory=${searchCategory}&keyword=${keyword}&pageNum=${startPage+pageBlock}">&raquo;</a>
			</c:if>
			</div>
		</c:if>
	</c:if>
	
	<c:if test="${search != 1}">
		<c:if test="${type == 0}">
			<div class="pagination">
			<c:if test="${startPage > pageBlock}">
				<a href="/moviebooking/ad/noticeList.do?pageNum=${startPage-pageBlock}">&laquo;</a> 
			</c:if>
			<c:forEach begin="${startPage}" end="${endPage}" step="1" varStatus="num">
				<a href="/moviebooking/ad/noticeList.do?pageNum=${num.count}" class="pageNum ${pageNum == num.count?'active':''}" >${num.count}</a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
				<a href="/moviebooking/ad/noticeList.do?pageNum=${startPage+pageBlock}">&raquo;</a>
			</c:if>
			</div>
		</c:if>
		<c:if test="${type == 1}">
			<div class="pagination">
			<c:if test="${startPage > pageBlock}">
				<a href="/moviebooking/noticeList.do?pageNum=${startPage-pageBlock}">&laquo;</a> 
			</c:if>
			<c:forEach begin="${startPage}" end="${endPage}" step="1" varStatus="num">
				<a href="/moviebooking/noticeList.do?pageNum=${num.count}" class="pageNum ${pageNum == num.count?'active':''}" >${num.count}</a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
				<a href="/moviebooking/noticeList.do?pageNum=${startPage+pageBlock}">&raquo;</a>
			</c:if>
			</div>
		</c:if>
	</c:if>
	<div class="form-actions">
	<c:if test="${type == 0}">
		<button type="button"
			onclick="location.href='/moviebooking/ad/noticeForm.do'">글쓰기</button>
	</c:if>
	</div>
</div>