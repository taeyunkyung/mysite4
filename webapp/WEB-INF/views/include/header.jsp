<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header" class="clearfix">
	<h1>
		<a href="/mysite4/main">MySite</a>
	</h1>

	<c:choose>
		<c:when test="${empty authUser}">
			<ul>
				<li><a href="/mysite4/user/loginForm" class="btn_s">로그인</a></li>
				<li><a href="/mysite4/user/joinForm" class="btn_s">회원가입</a></li>
			</ul>
		</c:when>

		<c:otherwise>
			<ul>
				<li>${authUser.name}님 안녕하세요^^</li>
				<li><a href="/mysite4/user/logout" class="btn_s">로그아웃</a></li>
				<li><a href="/mysite4/user/modifyForm" class="btn_s">회원정보수정</a></li>
			</ul>
		</c:otherwise>
	</c:choose>
</div>

<div id="nav">
	<ul class="clearfix">
		<li><a href="">입사지원서</a></li>
		<li><a href="/mysite4/board">게시판</a></li>
		<li><a href="">갤러리</a></li>
		<li><a href="/mysite4/guestbook">방명록</a></li>
	</ul>
</div>
