<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Site</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>게시판</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/board">일반게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/rboard">댓글게시판</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">댓글게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="board">
					<div id="list">
						<form action="${pageContext.request.contextPath}/rboard" method="get">
							<div class="form-group text-right">
								<input type="text">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>

						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>댓글달기</th>
									<th>삭제하기</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${rboardList}" var="rboardVo">
									<tr>
										<td>${rboardVo.no}</td>										
										<td class="text-left">		
											<a href="${pageContext.request.contextPath}/rboard/read?no=${rboardVo.no}">
												<c:if test="${rboardVo.depth != 0}">
													<c:forEach begin="1" end="${rboardVo.depth}">
													&nbsp;&nbsp;&nbsp;
													</c:forEach>
													ㄴ											
												</c:if> 
												(g=${rboardVo.groupNo}, o=${rboardVo.orderNo}, d=${rboardVo.depth}) ${rboardVo.title}
											</a> 
										</td>										
										<td>${rboardVo.name}</td>
										<td>${rboardVo.hit}</td>
										<td>${rboardVo.regDate}</td>
										
										<c:if test="${!empty authUser}">
											<td>
												<a href="${pageContext.request.contextPath}/rboard/writeReply?no=${rboardVo.no}">
												[추가]</a>										
											</td>
										</c:if>										

										<c:if test="${rboardVo.userNo == authUser.no}">
											<td>
												<a href="${pageContext.request.contextPath}/rboard/delete?no=${rboardVo.no}">[삭제]</a>
											</td>											
										</c:if>										
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<div id="paging">
							<ul>
								<li><a href="">◀</a></li>
								<li><a href="">1</a></li>
								<li><a href="">2</a></li>
								<li><a href="">3</a></li>
								<li><a href="">4</a></li>
								<li class="active"><a href="">5</a></li>
								<li><a href="">6</a></li>
								<li><a href="">7</a></li>
								<li><a href="">8</a></li>
								<li><a href="">9</a></li>
								<li><a href="">10</a></li>
								<li><a href="">▶</a></li>
							</ul>

							<div class="clear"></div>
						</div>
	
						<c:if test="${!empty authUser}">
							<a id="btn_write" href="${pageContext.request.contextPath}/rboard/writeFirst">첫글쓰기</a>
						</c:if>				

					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

	</div>
	<!-- //wrap -->

</body>
</html>
