<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<!-- 해더 네비 -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //해더 네비 -->


		<div id="container" class="clearfix">
			<!-- 게시판 aside -->
			<c:import url="/WEB-INF/views/includes/asideGallery.jsp"></c:import>
			<!-- //게시판 aside -->

			<div id="content">

				<div id="content-head">
					<h3>갤러리</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>갤러리</li>
							<li class="last">갤러리</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="gallery">
					<div id="list">

					<c:if test="${not empty authUser }">
						<button id="btnImgUpload">이미지올리기</button>
						<div class="clear"></div>
					</c:if>

						<ul id="viewArea">

							<!-- 이미지반복영역 -->
							<c:forEach items="${requestScope.galVo}" var="vo">
								<li id="l-${vo.no }" data-no="${vo.no }" data-uno="${vo.userNo }">
									<div class="view">
										<img class="imgItem" data-no="${vo.no }" src="${pageContext.request.contextPath }/upload/${vo.saveName }">
										<div class="imgWriter">
											작성자: <strong>${vo.uName }</strong>
										</div>
									</div>
								</li>
							</c:forEach>
							<!-- 이미지반복영역 -->

						</ul>
					</div>
					<!-- //list -->
				</div>
				<!-- //gallery -->


			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->


		<!-- 푸터 -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //푸터 -->
	</div>
	<!-- //wrap -->









	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>

				<form method="post" action="${pageContext.request.contextPath}/gallery/add" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label> <input id="addModalContent" type="text" name="content" value="">
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label> <input id="file-img" type="file" name="file" value="">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
					<input type="hidden" name="userNo" value="${authUser.no}">
				</form>


			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">

					<div id="imgForm" class="formgroup">
						
						<!-- ajax로 처리 : 이미지출력 위치-->
					</div>

					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>

				</div>
				<form method="" action="">
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						
						
							<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
							
					</div>

				</form>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>
<script type="text/javascript">
	//이미지 올리기 버튼 클릭할 때
	$("#btnImgUpload").on("click", function() {
		console.log("올리기버튼 클릭")
		$("#addModalContent").val("");
		$("#file-img").val("");

		//모달창 소환
		$("#addModal").modal();
	});

	//사진 리스트 클릭할 때
	$("#viewArea").on("click", "li", function() {
		console.log("사진 리스트 클릭");
		console.log($(this));

		var no = $(this).data("no");// 해당 li에 담겨진 no 꺼내기

		$.ajax({
			url : "${pageContext.request.contextPath }/api/gallery/viewModal",
			type : "post",
			//contentType : "application/json",
			data : {no : no},

			dataType : "json",
			success : function(galleryVo) {
				/*성공시 처리해야될 코드 작성*/
				console.log(galleryVo);
				var str  = '';
					str += '<img id="viewModelImg" src=${pageContext.request.contextPath}/upload/'+galleryVo.saveName+'>';
				$("#imgForm").html(str); //사진올릴 공간에 html로 이미지 태그 심어주기
				$("#viewModelContent").text(galleryVo.content);
				
				$("#viewModal").modal();
					
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});
	
	//모달창에서 삭제버튼 클릭할 때
	$("#btnDel").on("click", function() {
		console.log("삭제버튼 클릭");
	});
	
	
	
</script>

</html>

