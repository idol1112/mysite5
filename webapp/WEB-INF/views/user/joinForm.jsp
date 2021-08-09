<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">
		<!--  header(로고, 로그인버튼) nav(메뉴들) -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<!-- aside -->
			<c:import url="/WEB-INF/views/includes/asideUser.jsp"></c:import>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>회원가입</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">회원가입</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="user">
					<div id="joinForm">
						<form action="${pageContext.request.contextPath}/user/join" method="get">

							<!-- 아이디 -->
							
							<div class="form-group">
									<label class="form-text" for="input-uid">아이디</label> <input
									 		type="text" id="input-uid" name="id" value=""
									 		placeholder="아이디를 입력하세요">
									<button type="button" id="checkBtn">중복체크</button>
									<p id=checkMsg></p>
							</div>

							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">패스워드</label> <input
								 		type="text" id="input-pass" name="pw" value=""
								  		placeholder="비밀번호를 입력하세요">
							</div>

							<!-- 이름 -->
							<div class="form-group">
								<label class="form-text" for="input-name">이름</label> <input
								 		type="text" id="input-name" name="name" value=""
								  		placeholder="이름을 입력하세요">
							</div>

							<!-- 성별 -->
							<div class="form-group">
								<span class="form-text">성별</span> 
								<label for="rdo-male">남</label> <input
								 		type="radio" id="rdo-male" name="gender" value="male"> 
								<label for="rdo-female">여</label> <input
								 		type="radio" id="rdo-female" name="gender" value="female">

							</div>

							<!-- 약관동의 -->
							<div class="form-group">
								<span class="form-text">약관동의</span> <input type="checkbox" 
								id="chk-agree" value="" name=""> <label for="chk-agree">서비스 약관에 동의합니다.</label>
							</div>

							<!-- 버튼영역 -->
							<div class="button-area">
								<button type="submit" id="btn-submit">회원가입</button>
							</div>
							
						</form>
					</div>
					<!-- //joinForm -->
				</div>
				<!-- //user -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->
		
		<!-- footer -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
	<!-- //wrap -->

</body>
<script type="text/javascript">
//데이터를 json형식으로 보내기
//form 사용x --> form 처럼 사용하기
//기존parameter 사용x -->json으로 데이터를 전송
$("#btn-submit").on("click", function() {
	event.preventDefault();
	console.log("json방식으로 데이터 보내기");
	
	//데이터 모으기
	var userVo = {
		id: $("#input-uid").val(),
		pw: $("#input-pass").val(),
		name: $("#input-name").val(),
		gender: $("[name=gender]").val()
	};
	console.log(userVo);
	
	$.ajax({
		url : "${pageContext.request.contextPath }/api/user/join",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(userVo), //js 객체를 json형식(문자열)으로 변경

		dataType : "json",
		success : function(count) {
			/*성공시 처리해야될 코드 작성*/
			console.log(count);				
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
});

/* //form에 전송 버튼 클릭했을 때
$("#joinForm").on("submit", function() {
	console.log("form에 전송 버튼 클릭했을 때");
	
	//패스워드 8글자 이상 체크
	var pw = $("#input-pass").val();
	if(pw.length < 4) {
		alert("패스워드를 4글자 이상 입력해주세요");
		return false;
	}
	
	//이름 체크
	var name = $("input-name").val();
	if(name.length < 2) {
		alert("이름을 입력해 주세요");
		return false;
	}
	
	//약관동의
	var agree = $("#chk-agree").is(":checked");
	if(agree == false){
		alert("약간에 동의해 주세요");
		return false;
	}
	return true;
}); */



//중복체크 버튼 클릭할 때
$("#checkBtn").on("click", function() {
	event.preventDefault();
	console.log("중복체크 클릭");
	var id = $("#input-uid").val();
	console.log(id);
	
	$.ajax({
		url : "${pageContext.request.contextPath }/api/user/idCheck",
		type : "post",
		//contentType : "application/json",
		data : {id:id},

		dataType : "json",
		success : function(check) {
			/*성공시 처리해야될 코드 작성*/
			console.log(check);
			if(check){
				$("#checkMsg").text("가입이 가능한 아이디입니다");
			}else{
				$("#checkMsg").text("아이디가 중복입니다");
			}
		
				
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
});



</script>

</html>