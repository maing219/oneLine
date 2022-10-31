<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<script>
	function checkForm() {
		var form = document.addMember;
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
<body>
	<jsp:include page="/menu.jsp"></jsp:include>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">회원가입</h1>
		</div>
	</div>
	<div class="container">
		<form name="addMember" class="form-horizontal"
			action="./MemberAddAction.member" method="post">
			<div class="form-group row">
				<label class="col-sm-2">아이디</label>
				<div class="col-sm-3">
					<input name="id" type="text" class="form-control" placeholder="아이디">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">비밀번호</label>
				<div class="col-sm-3">
					<input name="password" type="text" class="form-control"
						placeholder="비밀번호">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">비밀번호 확인</label>
				<div class="col-sm-3">
					<input name="password_confirm" type="text" class="form-control"
						placeholder="비밀번호 확인">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">이름</label>
				<div class="col-sm-3">
					<input name="name" type="text" class="form-control"
						placeholder="이름">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-primary" onclick="checkForm()"
						value="등록"> <input type="reset" class="btn btn-primary"
						value="취소">
				</div>
			</div>
		</form>
	</div>
</body>
</html>