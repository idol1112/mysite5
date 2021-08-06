<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<!--  header(로고, 로그인버튼) nav(메뉴들) -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<div id="container" class="clearfix">
		
			<!-- aside -->
			<c:import url="/WEB-INF/views/includes/asideBoard.jsp"></c:import>
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
						<form action="${pageContext.request.contextPath}/board/list2" method="get">
							<div class="form-group text-right">
								<input type="text" name="keyword" value="" 
								 	   placeholder="검색어를 입력하세요">
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
							<c:forEach items="${requestScope.listMap.boardList}" var="vo">
								<tr>
									<td>${vo.no}</td>
									<td class="text-left"><a href="${pageContext.request.contextPath}/board/read?no=${vo.no}">${vo.title}</a></td>
									<td>${vo.uName}</td>
									<td>${vo.hit}</td>
									<td>${vo.regDate}</td>
									<td>
									<c:if test="${vo.userNo == authUser.no}">
										<a href="${pageContext.request.contextPath}/board/delete?no=${vo.no}">[삭제]</a>
									</c:if>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
			
						<div id="paging">
							<ul>
								<c:if test="${listMap.prev }">
									<li><a href="${pageContext.request.contextPath}/board/list2?crtPage=${listMap.startPageBtnNo-1}&keyword=${param.keyword}">◀</a></li>
								</c:if>
								
								<c:forEach begin="${listMap.startPageBtnNo}" end="${listMap.endPageBtnNo}" step="1" var="page">
									<c:choose>
										<c:when test="${param.crtPage eq page }">
											<li class="active"><a href="${pageContext.request.contextPath}/board/list2?crtPage=${page}&keyword=${param.keyword}">${page}</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="${pageContext.request.contextPath}/board/list2?crtPage=${page}&keyword=${param.keyword}">${page}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								<c:if test="${listMap.next }">
									<li><a href="${pageContext.request.contextPath}/board/list2?crtPage=${listMap.endPageBtnNo+1}&keyword=${param.keyword}">▶</a></li>
								</c:if>
							</ul>
							
							
							<div class="clear"></div>
						</div>
						<c:if test="${authUser != null }">
							<a id="btn_write" href="${pageContext.request.contextPath}/board/wform">글쓰기</a>
						</c:if>
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->
		

		<!-- footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>