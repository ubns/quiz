<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<h3 class="site-title"><a href="/">Quiz</a></h3>
	<div class="user">
		<c:choose>
			<c:when test="${name eq null}"><a href="/login">Login</a> or <a href="/signin">Sign in</a></c:when>
			<c:when test="${name ne null}">${name}さん <a href="/auth">ログアウト</a></c:when>
		</c:choose>
	</div>
</header>