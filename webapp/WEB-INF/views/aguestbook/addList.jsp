<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Site</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet"	type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/guestbook">일반방명록</a></li>
					<li><a href="${pageContext.request.contextPath}/api/guestbook/addList">ajax방명록</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
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
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label></th>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label></th>
								<td><input id="input-pass" type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72"
										rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
							</tr>
						</tbody>
					</table>		
							
					<div id="listArea">
					</div>
					 													
				</div>						
				<!-- //guestbook -->
			</div>
			<!-- //content  -->			
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
	$(document).ready(function() {
		
		fetchList();
		console.log("리스트 요청");		
			
	});
	
	$("#btnSubmit").on("click", function() {
		console.log("버튼클릭");
		var name = $("#input-uname").val();
		var password = $("#input-pass").val();
		var content = $("[name='content']").val();
		
		var guestbookVo = {
			name: name,
			password: password,
			content: content
		};
		
		$.ajax({			
			url : "${pageContext.request.contextPath}/api/guestbook/add",		
			type : "post",
			// contentType : "application/json",
			data : guestbookVo,

			dataType : "json",
			success : function(guestbookVo){
				console.log(guestbookVo);
				render(guestbookVo, "up");
				
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}	
		}); 
	});
	
	function fetchList() {
		$.ajax({			
			url : "${pageContext.request.contextPath}/api/guestbook/list",		
			type : "post",
			// contentType : "application/json",
			// data : {name: "홍길동"},

			dataType : "json",
			success : function(guestList){
				console.log(guestList);	
				
				for(var i = 0; i < guestList.length; i++) {
					render(guestList[i], "down");
				}				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}				
		}); 	
	};
	
	function render(guestbookVo, updown) {		
		var str = '';
		str += '<table class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';	
		str += '		<td>'+guestbookVo.no+'</td>';
		str += '		<td>'+guestbookVo.name+'</td>';
		str += '		<td>'+guestbookVo.regDate+'</td>';
		str += '		<td><a';
		str += '				href="">[삭제]</a></td>';
		str += '	</tr>';	
		str += '	<tr>';	
		str += '		<td colspan=4 class="text-left">'+guestbookVo.content+'</td>';
		str += '	</tr>';	
		str += '</table>';	
		
		if(updown == 'down') {
			$("#listArea").append(str);
		} else {
			$("#listArea").prepend(str);
		}
	};
</script>

</html>