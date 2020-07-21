<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/boardAside.jsp"></c:import>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="list">
					<form action="${pageContext.request.contextPath }/board/search" method="get">
						<div class="form-group text-right">
							<input type="text" name="keyword">
							<button type="submit" id=btn_search>검색</button>
						</div>
					</form>
					<table >
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pMap.bList}" var="bVo">
							<tr>
								<td>${bVo.no}</td>
								<td class="text-left"><a href="${pageContext.request.contextPath}/board/boardRead?no=${bVo.no}">${bVo.title}</a></td>
								<td>${bVo.name}</td>
								<td>${bVo.hit }</td>
								<td>${bVo.reg_date }</td>
								<td><c:if test="${authUser.no eq bVo.user_no}">
								<a href="${pageContext.request.contextPath}/board/delete?user_no=${bVo.no}">[삭제]</a>
									</c:if></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
		
					<%-- <div id="paging">
						<ul>
							<li><a href="">◀</a></li>
							<c:forEach begin = "1" end = "${count}" var = "var">
								<li><a href="">${var}</a></li>
							</c:forEach>
							
							<li><a href="">▶</a></li>
						</ul>
						
						
						<div class="clear"></div>
					</div> --%>
					
					<div id="paging">
						<ul>
							<c:if test="${pMap.prev == true }">
								<li><a href="${pageContext.request.contextPath}/board/boardList2?crtPage="${pMap.startPageBtnNo-1}">◀</a></li>
							</c:if>
						
							<c:forEach begin="${pMap.startPageBtnNo}" end ="${pMap.endPageBtnNo}" step="1" var="page">
								<c:choose>
									<c:when test="${param.crtPage eq page }">
										<li class="active">
											<a href="${pageContext.request.contextPath}/board/boardList2?crtPage=${page}">${page}</a>
										</li>
									</c:when>
								<c:otherwise>
									<li class="">
									 	<a href="${pageContext.request.contextPath}/board/boardList2?crtPage=${page}">${page}</a>
									</li>
								</c:otherwise>
								</c:choose>
							</c:forEach>
							
							
							<c:if test="${pMap.next == true }">
								<li><a href="${pageContext.request.contextPath}/board/boardList2?crtPage="${pMap.endPageBtnNo+1}">▶</a></li>
							</c:if>
							
						</ul>
				
						<div class="clear"></div>
					</div>
					
					
					<c:if test="${authUser != null }">
					<a id="btn_write" href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
					</c:if>
					
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>