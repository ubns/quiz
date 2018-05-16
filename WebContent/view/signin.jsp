<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sign In</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>

 <jsp:include page="/common/header.jsp" />

<div class="signin">
	<div class="main">
		<h3>Sign In</h3>
		<form action="/signin" method="Post">
			<span>${message}</span><br>
			<input type="text" name="name" placeholder="ユーザ名を入力" /><br>
			<input type="password" name="pass" placeholder="パスワードを入力" /><br>
			<button type="submit">登録</button>
		</form>
	</div>
</div>

</body>
</html>