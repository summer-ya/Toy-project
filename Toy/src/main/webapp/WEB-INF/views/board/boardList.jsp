<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<style type="text/css">
.container {
	width: auto;
    padding: 57px 175px 0px;
}
table {
	table-layout: fixed;
}
table, th {
	text-align: center;
}
td:nth-child(2) {
	text-align: left;
}
#btnWrite{
	float: right;
    width: 54px;
    height: 34px;
    border-radius: 5px;
    font-size: 14px;
    border: none;
    color: #555;
    border: 1px solid #ccc;
}
.search { text-align: right; font-size: 12px; top: 10px;}
.search-op { height: 40px; width: 100px; border: 1px solid #e8e8e8; }
.search-text { height: 40px; width: 240px; border: 1px solid #e8e8e8; }
.search-btn { height: 40px; width:37px;  border: 1px solid #6bacce; background-color: #6bacce; color: #fff; }
#title > a{ color: #333; }
.order_wrap{
	display: inline-block;
    position: relative;
    top: -40px;
}
#orderType { 
	height: 40px; 
	width: 100px; 
	border: 1px solid #e8e8e8;
	
	
}
.pull-left {
	position: relative;
    left: 109px;
    top: 40px;
}
</style>



</head>
<body>


<div class="container">

<h1>QnA게시판입니다</h1>
<hr>

<span class="pull-left">total : <span id="totalCnt">${paging.totalCount }</span></span>

<div class="search">
	<form id="form" id="searForm" style="display: inline-block;">
		<select class="search-op" id="searchType">
			<option value="title">제목</option>
			<option value="content">내용</option>
			<option value="writer">작성자</option>
		</select>
		<input type="text" class="search-text" placeholder="🔍 검색어를 입력하세요" id="keyword">
	</form>
		<button class="search-btn" id="searBtn">찾기</button	>
</div> 


<div class="order_wrap">	
	<select id="orderType">
		<option value="newest">최신순</option>
		<option value="comm">댓글순</option>
		<option value="hit">조회순</option>
	</select>
</div>


<div class="clearfix" style="padding-bottom: 30px;"></div>

<div id="apeend_wrap">
	<table class="table table-striped table-hover table-condensed">
	<thead>
		<tr>
			<th style="width: 10%;">글번호</th>
			<th style="width: 45%; text-align: center;">제목</th>
			<th style="width: 20%;">작성자</th>
			<th style="width: 10%;">조회수</th>
			<th style="width: 15%;">작성일</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${list }" var="fboard">
		<tr>
			<td>${fboard.bno }</td>
			<td id="title">
				<a href="/board/boardList?bno=${fboard.bno }">${fboard.title }&nbsp;</a>
				<c:if test="${fboard.fileCnt > 0 }">
<!-- 					<img src="/resources/img/pic.png" style="width: 17px; height: 17px;"> -->
				</c:if>
<%-- 				<span style="color: tomato;">[${fboard.commCnt }]</span> --%>
			</td>
			<td>${fboard.userid }</td>
			<td>${fboard.hit }</td>
			<td><fmt:formatDate value="${fboard.writeDate }" pattern="yy-MM-dd"/></td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
	<c:if test="${login eq true }">
	<button id="btnWrite">글쓰기</button>
	</c:if>
	<div class="clearfix"></div>
	
	
	<div id="paging">
<%-- 		<c:import url="/WEB-INF/views/board/boardList.jsp" /> --%>
	</div>
</div>
</div> <!-- container -->



</body>
</html>