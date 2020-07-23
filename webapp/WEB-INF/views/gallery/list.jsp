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

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
		<!-- //aside -->


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
				
					<c:if test="${sessionScope.authUser != null }">
						<button id="btnImgUpload">이미지올리기</button>
						<div class="clear"></div>
					</c:if>
			
					<ul id="viewArea">
						
						<c:forEach items="${galleryList}" var="Gvo">
						<!-- 이미지반복영역 -->
							<li>
								<div class="view" id="v${Gvo.no }" data-no=${Gvo.no }>
									<img class="imgItem" src="${pageContext.request.contextPath }/upload/${Gvo.saveName}">
									<div class="imgWriter">작성자: <strong>${Gvo.name}</strong></div>
								</div>
							</li>
						<!-- 이미지반복영역 -->
						</c:forEach>
						
						
					</ul>
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

	
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form method="post" action="${pageContext.request.contextPath}/gallery/upload" enctype="multipart/form-data" >
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="file" value="" >
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	


	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup" >
						<img id="viewModelImg" src =""> <!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>
					
				</div>
				<form method="post" action="${pageContext.request.contextPath}/gallery/delete">
					<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-danger" id="btnDel" value="${sessionScope.authUser != null }">삭제</button>
				</div>
					<input type="text" id="delNo" name="no" value="">
					
				</form>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	


</body>

<script type="text/javascript">


//이미지삭제
$("#btnDel").on("click", function(){
	console.log("삭제버튼 클릭");
	
	var no = $("#delNo").val();
	
	$.ajax({
		//data보낼 때 옵션
		url : "${pageContext.request.contextPath}/gallery/delete",		
		type : "post",
		data : {no: no},
		
		//data받을 때 옵션
		dataType : "json",
		success : function(cnt){ /*성공시 처리해야될 코드 작성*/
			if(cnt == 1) { // 삭제 성공 시 
				$("#v" + no).remove();  // 해당 게시글 삭제 (line 61번 내용 참고)
				$("#viewModal").modal("hide"); // 모달 창 닫기
			}
			else { // 실패 시
				$("#viewModal").modal("hide"); // 모달 창 닫기
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
		
	
	
});

//이미지올리기 
$("#btnImgUpload").on("click", function(){
	console.log("btnImgUpload");
	
	$("#addModal").modal();//업로드 클릭버튼 우르면 모달폼 뜨게하기
	
});

//사진이미지 
$(".view").on("click", function(){
	//이벤트체크
	console.log("상세보기모달");
	$("#viewModal").modal(); //메소드=함수
	
	//데이터수집
	var no = $(this).data("no");
	$("#delNo").val(no);
	
	console.log(no);
	
	$.ajax({ //form, a링크
		
		url : "${pageContext.request.contextPath }/gallery/read",		
		type : "post",
		//contentType : "application/json",
		data : {no: no}, //왼쪽 파라미터이름 오른쪽 값
		
		
		//data 가져온 후
		dataType : "json",
		success : function(no){
			console.log(no);
		//성공시 처리해야될 코드 작성
		var imgurl = "/mysite4/upload/" +no.saveName;
		console.log(imgurl)
		
		//이미지출력
		$("#viewModelImg").attr("src", imgurl);
		
		//코멘트출력
		$("#viewModelContent").html(no.content);
		
		//버튼출력(자신의 글일때만 삭제버튼 보이게 처리)
		if("${authUser.no}" != no.user_no){
			console.log("userNo: "+no.user_no);
			$("#btnDel").hide();
		}else {
			$("#btnDel").show();
		}
		
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
		
	});

});






//이미지를 가져와야해.



</script>




</html>

