<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<script>
	function checkDelete() {
		var status = confirm("정말 삭제하시겠습니까?");
		if(status) {
			location.href = "./BoardDeleteAction.do?num=${ board.num }&pageNum=${ page }";
		} else {
			return false;
		}
	}
	
	function checkCommentDelete(comment_num) {
		var status = confirm("정말 삭제하시겠습니까?");
		if(status) {
			location.href = "./CommentDeleteAction.comment?num=${ board.num }&pageNum=${ page }&comment_num=" + comment_num;
		} else {
			return false;
		}
	}

	function checkUpdate() {
		var status = confirm("수정 하시겠습니까?");
		if(status) {
			updateForm.submit();
		} else {
			return false;
		}
	}
</script>
</head>
<body>
	<jsp:include page="../menu.jsp"></jsp:include>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-4">게시판</h1>
		</div>
	</div>
	<div class="container">
		<form method="post" name="updateForm" class="form-horizontal"
			action="./BoardUpdateAction.do?num=${ board.num }&pageNum=${ page }"
			onsubmit="return false;">
			<div class="form-group row">
				<label class="col-sm-2 control-label">글쓴이</label>
				<div class="col-sm-3">
					<input name="name" type="text" class="form-control"
						style="border: none;" value="${ board.name }" placeholder="name">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">제목</label>
				<div class="col-sm-5">
					<input name="subject" type="text" class="form-control"
						placeholder="subject" value="${ board.subject }"
						style="border: none;">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">내용</label>
				<div class="col-sm-8">
					<textarea rows="5" cols="50" name="content" class="form-control"
						placeholder="content" style="border: none; resize: none;">${ board.content }</textarea>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<c:set var="userId" value="${ board.id }" />
					<c:if test="${ userId == sessionId }">
						<!-- <a href="./BoardDeleteAction.do?num=${ board.num }&pageNum=${ page }" class="btn btn-danger">삭제</a> -->
						<button onclick="checkDelete()" class="btn btn-danger">삭제</button>
						<button onclick="checkUpdate()" class="btn btn-success">수정</button>
					</c:if>
					<a href="./BoardListAction.do?pageNum=${ page }"
						class="btn btn-primary">목록</a>
				</div>
			</div>
		</form>
		<!-- 댓글 부분 -->
		<div>
			<table border="1">
				<c:if test="${ commentList != null }">
					<c:forEach var="comment" items="${ commentList }">
						<tr>
							<td width="150">                    
								<div>
									${ comment.comment_id }<br> <font size="2"
										color="lightgray">${ comment.comment_date }</font>
								</div>
							</td>
							<td width="550">                    
								<div class="text_wrapper">${ comment.comment_content }</div>
							</td>
							<td width="100">                     <c:if
									test="${ comment.comment_id == sessionId }">
									<div id="btn" style="text-align: center;">
										<button onclick="checkCommentDelete(${ comment.comment_num })"
											class="btn btn-danger">삭제</button>
									</div>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${ sessionId != null }">
					<tr bgcolor="#F5F5F5">
						<form
							action="./CommentWriteAction.comment?comment_board=${ board.num }&num=${ board.num }&pageNum=${ page }"
							method="post">
							<input type="hidden" name="comment_id" value="${ sessionId }">
							<td>
								<div>${ sessionId }</div>
							</td>
							<td>
								<div>
									<textarea name="comment_content" rows="4" cols="70"></textarea>
								</div>                
							</td>
							<td>
								<div id="btn" style="text-align: center;">
									<p>
										<input type="submit" value="등록">
									</p>
								</div>
							</td>
						</form>
					</tr>
				</c:if>
			</table>
		</div>
	</div>
	<hr>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>