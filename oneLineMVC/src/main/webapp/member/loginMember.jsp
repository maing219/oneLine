<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="/menu.jsp"></jsp:include>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-4">로그인</h1>
		</div>
	</div>
	<div class="container" align="center">
		<div class="col-md-4 col-md-offset-4">
			<h3 class="form-signin-heading">Please sign in</h3>
			<c:set var="error" value="${ param.error }"/>
			<c:if test="${ error != null }">
				<div class="alert alert-danger">아이디와 비밀번호를 확인해 주세요.</div>
			</c:if>
			<form class="form-signin" action="./MemberLoginAction.member" method="post">
				<div class="form-group">
					<label for="inputUserName">User Name</label>
					<input type="text" class="form-control" placeholder="아이디" name="id"
						id="inputUserName" required autofocus>
				</div>
				<div class="form-group">
					<label for="inputPassword">Password</label>
					<input type="password" class="form-control" placeholder="비밀번호" name="password"
						id="inputPassword" required>
				</div>
				<button class="btn btn-lg btn-success btn-block" type="submit">로그인</button>
			</form>
		</div>
	</div>
</body>
</html>