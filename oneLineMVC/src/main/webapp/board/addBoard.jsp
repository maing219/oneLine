<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String name = (String) request.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="../menu.jsp"></jsp:include>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-4">게시판</h1>
		</div>
	</div>
	<div class="container">
		<form method="post" name="newWrite" class="form-horizontal"
			action="./BoardWriteAction.do">
			<input name="id" type="hidden" class="form-control"
				value="${ sessionId }">
			<div class="form-group row">
				<label class="col-sm-2 control-label">성명</label>
				<div class="col-sm-3">
					<input name="name" type="text" class="form-control"
						value="<%=name%>" placeholder="name">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">제목</label>
				<div class="col-sm-5">
					<input name="subject" type="text" class="form-control"
						placeholder="제목">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">내용</label>
				<div class="col-sm-8">
					<textarea rows="5" cols="50" name="content" class="form-control"
						placeholder="내용"></textarea>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-primary" value="등록"> <a
						href="./BoardListAction.do" class="btn btn-primary">취소</a>
				</div>
			</div>
		</form>
	</div>
	<hr>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>