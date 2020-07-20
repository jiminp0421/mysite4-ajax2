<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>


</head>



<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/guestAside.jsp"></c:import>
		<!-- //aside -->
		
		<div id="content">
			<div id="content-head">
				<h3>ajax방명록</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>방명록</li>
						<li class="last">ajax방명록</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->
			<div id="guestbook">
				
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label></td>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label></td>
								<td><input id="input-pass" type="password" name="pw"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4"><button id="btnSubmit" type="submit">등록</button></td>
								
							</tr>
						</tbody>

					</table>
					<button id="test" type="button">모달테스트</button>
					
					<!-- //guestWrite -->
				
				
				<div id="guestbookListArea">
				
				
				</div>	
				
			</div>
			<!-- //guestbook -->
			
			
			
		</div>
		<!-- //content  -->
		<div class="clear"></div>
		
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->


	</div>
	<!-- //wrap -->

	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="delModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label> 
					<input type="password" name="modalPassword" id="modalPassword"><br> 
					<input type="text" name="modalNo" value="" id="modalNo"> <br>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>
<script type="text/javascript">
$(document).ready(function(){
	
	//전체리스트 불러오기
	fetchList();
	
});


//모달테스트
$("#test").on("click", function(){ 
	event.preventDefault(); //a태그 링크 기본기능 막으려고
	console.log("모달테스트");
	$("#delModal").modal();
}); 

//리스트 삭제버튼 클릭할때
$("#guestbookListArea").on("click", "a", function(){
	console.log("리스트 지역 클릭");
	event.preventDefault(); //a태그 링크 기본기능 막으려고
	
	//no값 구하기
	var $this = $(this);
	var no = $this.data("delno");
	console.log(no);
	console.log($this);
	
	//no값 모달창에 입력
	$("#modalNo").val(no);
	
	//input에 값 비우기
	$("modalPassword").val("");
	
	//모달창보이기
	$("#delModal").modal();
});
	
	
//모달창 삭제버튼 클릭할때
$("#btnDel").on("click", function(){
	//이벤트체크
	console.log("모달창 삭제버튼 클릭");
	//데이터 수집
	var pw = $("#modalPassword").val();
	var no = $("#modalNo").val();
	

	
	//데이터 전송--> 그리기 작업
	$.ajax({
		
		url: "${pageContext.request.contextPath}/api/guest/delete",
		type : "post",
		//contentType : "application/json",
		data : {pw: pw, no: no}, //파라미터로 보내는방식 //제이슨방식 두개(write가 제이슨방식)-데이터가많을때사용한다
		
		dataType: "json",
		success : function(count) {
			console.log(count);
			
			if(count == 1 ){
				//모달창닫고
				$("#delModal").modal("hide");
				//리스트 지우기
				$("#t-"+ no).remove();
			}else{
				//모달창만 닫기
				$("#delModal").modal("hide");
			}
		
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
			}
		
		
		});
	
			
	});

	



//글쓰기 클릭이벤트
$("#btnSubmit").on("click", function(){
	//이벤트체크
	console.log("글쓰기버튼");
	//event.prventDefault();
	
	//데이터수집
	var uname = $("#input-uname").val();
	var pass = $("#input-pass").val();
	var content = $("[name = 'content']").val();
	
	
	//제이슨 표기법
		
	var guestbookVo = {
		name : uname,
		pw : pass,
		content : content
		};
		console.log(guestbookVo);

		//데이터전송
		$.ajax({
			/*
			url: "${pageContext.request.contextPath}/api/guest/write",
			type : "post",
			//contentType : "application/json",
			data : guestbookVo,*/

			url : "${pageContext.request.contextPath}/api/guest/write",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(guestbookVo),

			dataType : "json",
			success : function(guestbookVo) {
				console.log(guestbookVo);
				/*성공시 처리해야될 코드 작성*/
				render(guestbookVo, "up");

				$("#input-uname").val("");
				$("input-pass").val("");
				$("[name = 'content']").val("");

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}

		});

	});

	function fetchList() {
		$.ajax({

			url : "${pageContext.request.contextPath }/api/guest/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: ”홍길동"},

			dataType : "json",
			success : function(guestbookList) {
				console.log(guestbookList);
				/*성공시 처리해야될 코드 작성*/
				//$("#guestbookListArea").html()

				for (var i = 0; i < guestbookList.length; i++) {
					render(guestbookList[i], "down");

				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}

		});

	}

	//리스트 그리기(1개씩)

	function render(guestVo, direction) {
		var str = "";

		str += "<table id='t-"+guestVo.no+"' class='guestRead'>";
		str += "		<colgroup>";
		str += "			<col style='width: 10%;'>";
		str += "			<col style='width: 40%;'>";
		str += "			<col style='width: 40%;'>";
		str += "			<col style='width: 10%;'>";
		str += "		</colgroup>";
		str += "		<tr>";
		str += "			<td>" + guestVo.no + "</td>";
		str += "			<td>" + guestVo.name + "</td>";
		str += "			<td>" + guestVo.regdate + "</td>";
		str += "			<td><a href='' data-delno='" + guestVo.no + "'>[삭제]</a></td>";
		str += "		</tr>";
		str += "		<tr>";
		str += "			<td colspan=4 class='text-left'>" + guestVo.content
				+ "</td>";
		str += "		</tr>";
		str += "</table>";

		if (direction == "up") {
			$("#guestbookListArea").prepend(str);
		} else if (direction == "down") {
			$("#guestbookListArea").append(str);
		} else {
			console.log("direction 오류");
		}
	}
</script>



</html>