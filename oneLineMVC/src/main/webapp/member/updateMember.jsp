<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원수정</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<script>
	function checkForm() {
		var form = document.updateMember;
		if (!form.id.value) {
			alert("아이디를 입력하세요");
			return false;
		}

		if (!form.password.value) {
			alert("비밀번호를 입력하세요");
			return false;
		}

		if (!form.password_confirm.value) {
			alert("비밀번호 확인을 입력하세요");
			return false;
		}

		if (form.password.value != form.password_confirm.value) {
			alert("비밀번호를 동일하게 입력하세요");
			return false;
		}
	}
</script>
</head>
<jsp:include page="/menu.jsp"></jsp:include>
<div class="jumbotron">
	<div class="container">
		<h1 class="display-3">회원수정</h1>
	</div>
</div>
<div class="container">
	<form name="updateMember" class="form-horizontal"
		action="./MemberUpdateAction.member" method="post"
		onsubmit="return checkForm()">
		<div class="form-group row">
			<label class="col-sm-2">아이디</label>
			<div class="col-sm-3">
				<input name="id" type="text" class="form-control" placeholder="아이디"
					value="${ member.id }">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2">비밀번호</label>
			<div class="col-sm-3">
				<input name="password" type="password" class="form-control"
					placeholder="비밀번호" value="${ member.password }">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2">비밀번호 확인</label>
			<div class="col-sm-3">
				<input name="password_confirm" type="password" class="form-control"
					placeholder="비밀번호 확인">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2">이름</label>
			<div class="col-sm-3">
				<input name="name" type="text" class="form-control"
					placeholder="이름" value="${ member.name }">
			</div>
		</div>
		<div class="form-group row">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" class="btn btn-primary" value="수정"> <a
					href="./MemberDeleteAction.member?id=${ member.id }" class="btn btn-primary">회원탈퇴</a>
			</div>
		</div>
	</form>
</div>
</body>
</html>