<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="aside">
	<h2>게시판</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath }/guest/list">일반방명록</a></li>
		<li><a href="${pageContext.request.contextPath }/guest/ajaxlist">ajax방명록</a></li>
	</ul>
</div>