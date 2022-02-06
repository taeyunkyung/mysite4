<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Site</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
		
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
				
				<c:if test="${!empty authUser}">
					<button id="btnImgUpload">이미지올리기</button>
				</c:if>	
				
				<div class="clear"></div>

					<ul id="viewArea">						
						<!-- 이미지반복영역 -->
						<c:forEach items="${galleryList}" var="galleryVo">
							<li>
								<div class="view" data-no="${galleryVo.no}" id="img${galleryVo.no}">
									<img class="imgItem" src="${pageContext.request.contextPath}/upload/${galleryVo.saveName}">
									<div class="imgWriter">작성자: <strong>${galleryVo.writer}</strong></div>
								</div>
							</li>
						</c:forEach>							
						<!-- 이미지반복영역 -->
					</ul>
					
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		
	</div>
	<!-- //wrap -->	
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div id="addModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form method="post" action="${pageContext.request.contextPath}/gallery/add" enctype="multipart/form-data">
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
						<button id="btnUpload" type="submit" class="btn">등록</button>
					</div>
				</form>			
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

	<!-- 이미지보기 팝업(모달)창 -->
	<div id="viewModal" class="modal fade">		
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">					
					<div class="formgroup" >
						<img id="viewModalImg" src =""> 
						<!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModalContent"></p>
						<input id="authUserNo" type="hidden" name="userNo" value="${authUser.no}">
						<input id="modalNo" type="text" name="no" value="">						
					</div>					
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button id="btnDel" type="button" class="btn btn-danger">삭제</button>
				</div>	
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	
	
</body>

<script type="text/javascript">
	$("#btnImgUpload").on("click", function() {
		
		$("#addModalContent").val("");
		$("#file").val("");
		
		$("#addModal").modal("show");
	});
	
	$("#viewArea").on("click", ".view", function() {
		var $this = $(this);
		var no = $this.data("no");
		console.log(no);
		
		$.ajax({			
			url : "${pageContext.request.contextPath}/gallery/view",		
			type : "post",
			// contentType : "application/json",
			data : {no: no},

			dataType : "json",
			success : function(galleryVo){
				console.log(galleryVo);	
				
				$("#viewModalImg").attr("src", "${pageContext.request.contextPath}/upload/"+ galleryVo.saveName);
				$("#viewModalContent").html(galleryVo.content);	
				$("#modalNo").val(galleryVo.no);
				
				var authUserNo = $("#authUserNo").val();
				var voUserNo = galleryVo.userNo;
				
				if(authUserNo == voUserNo) {
					$("#btnDel").show();
				} else {
					$("#btnDel").hide();
				}
				
				$("#viewModal").modal("show");
								
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}				
		}); 	
		
	});
	
	$("#btnDel").on("click", function() {
		var userNo = $("#authUserNo").val();
		var no = $("#modalNo").val();		
		
		var delInfoVo = {
			userNo: userNo,
			no: no
		}
		
		$.ajax({			
			url : "${pageContext.request.contextPath}/gallery/remove",		
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(delInfoVo),

			dataType : "json",
			success : function(state){
				console.log(state);
				
				if(state === 'success') {
					$("#img"+ no).remove();
					$("#viewModal").modal("hide");					
				} 	
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}				
		}); 
	});
		
</script>

</html>

