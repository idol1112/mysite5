<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div id="header" class="clearfix">
			<h1>
				<a href="${pageContext.request.contextPath}/main">MySite</a>
			</h1>
			
			<!-- 로그인 안했을때 -->
			<c:choose>
				<c:when test="${empty sessionScope.authUser}">
					<ul>
						<li><a href="${pageContext.request.contextPath}/user/lform" class="btn_s">로그인</a></li>
						<li><a href="${pageContext.request.contextPath}/user/jform" class="btn_s">회원가입</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul>
						<li>${authUser.name} 님 안녕하세요^^</li>
						<li><a href="${pageContext.request.contextPath}/user/logout" class="btn_s">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath}/user/mform" class="btn_s">회원정보수정</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
			
		</div>
		<!-- //header -->

		<div id="nav">
			<ul class="clearfix">
				<li><a href="">입사지원서</a></li>
				<li><a href="./board?action=list">게시판</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="./gbc?action=addList">방명록</a></li>
			</ul>
		</div>
		<!-- //nav -->