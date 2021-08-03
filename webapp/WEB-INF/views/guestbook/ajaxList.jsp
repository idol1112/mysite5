<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">
		<!--  header(로고, 로그인버튼) nav(메뉴들) -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<div id="container" class="clearfix">

			<!-- aside -->
			<c:import url="/WEB-INF/views/includes/asideGuest.jsp"></c:import>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>ajax방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">ajax방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label>
									</td>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label>
									</td>
									<td><input id="input-pass" type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>

						</table>

					</form>


					<div id="listArea"></div>

				</div>
				<!-- //guestbook -->

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
	//화면 로딩되기 직전
	$(document).ready(function() {
		console.log("화면 로딩 직전");
		//요청
		$.ajax({

				url : "${pageContext.request.contextPath }/api/guestbook/list",
				type : "post",
				/* contentType : "application/json", */
				//data : {name: "홍길동"},
				//dataType : "json",
				success : function(guestList) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestList);

				//화면에 그리기
				for (var i = 0; i < guestList.length; i++) {
					render(guestList[i], "down");
				}

				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
				});

	});
	
	//로딩이 끝난 후
	//등록 버튼 클릭할 때
	$("#btnSubmit").on("click", function() {
		event.preventDefault();
		console.log("등록버튼 클릭");
		/*
		//name 값 읽어오기
		var userName = $("#input-uname").val();
		console.log(userName);
		//password 값 읽어오기
		var password = $("#input-pass").val();
		console.log(password);
		//content 값 읽어오기
		var content = $("[name='content']").val();
		console.log(content);
		*/
		var guestbookVo = {
			name: $("#input-uname").val(),
			password: $("#input-pass").val(),
			content: $("[name='content']").val()		
		};
		
		//데이터 ajax방식으로 서버에 전송
		$.ajax({
			
			//url : "${pageContext.request.contextPath }/api/guestbook/write?name=" + userName + "&password=" + password + "&content=" + content,
			url : "${pageContext.request.contextPath }/api/guestbook/write",		
			type : "get",
			//contentType : "application/json",
			//data : {name: userName, password: password, content: content},
			data : guestbookVo,
			//dataType : "json",
			success : function(guestbookVo){
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookVo);
				render(guestbookVo, "up");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	function render(guestbookVo, type) {
		var str = "";
		str += '<table class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>' + guestbookVo.no
				+ '</td>';
		str += '		<td>' + guestbookVo.name
				+ '</td>';
		str += '		<td>'
				+ guestbookVo.regDate
				+ '</td>';
		str += '		<td><a href="${pageContext.request.contextPath}/guestbook/deleteForm?no='
				+ guestbookVo.no
				+ '">[삭제]</a></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">'
				+ guestbookVo.content
				+ '</td>';
		str += '	</tr>';
		str += '</table>';
		
		if(type === 'down') {
			$("#listArea").append(str);
		}else if(type === 'up') {
			$("#listArea").prepend(str);
		}else {
			console.log("방향을 지정해주세요");
		}
	}
		
	
</script>

</html>