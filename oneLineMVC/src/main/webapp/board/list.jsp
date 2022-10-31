<%@page import="model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String sessionId = (String) session.getAttribute("sessionId");
List boardList = (List) request.getAttribute("boardlist");
int total_record = ((Integer) request.getAttribute("total_record")).intValue();
int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
int total_page = ((Integer) request.getAttribute("total_page")).intValue();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<script>
	function checkForm() {
		if(${ sessionId == null }) {
			alert("로그인 해주세요.");
		} else {
			location.href="./BoardWriterForm.do?id=<%=sessionId%>";
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
		<form method="post" action='<c:url value="./BoardListAction.do"/>'>
			<div>
				<div class="text-right">
					<span class="badge badge-success">전체 <%=total_record%>건
					</span>
				</div>
			</div>
			<div style="padding-top: 50px;">
				<table class="table table-hover">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성일</th>
						<th>조회</th>
						<th>추천수</th>
						<th>글쓴이</th>
					</tr>
					<%
					for (int i = 0; i < boardList.size(); i++) {
						BoardDTO notice = (BoardDTO) boardList.get(i);
					%>
					<tr>
						<td><%=notice.getNum()%></td>
						<td><a
							href="./BoardViewAction.do?num=<%=notice.getNum()%>&pageNum=<%=pageNum%>"><%=notice.getSubject()%></a></td>
						<td><%=notice.getRegist_day()%></td>
						<td><%=notice.getHit()%></td>
						<td><%=notice.getRecommend()%></td>
						<td><%=notice.getId()%></td>
					</tr>
					<%
					}
					%>
				</table>
			</div>
			<div align="center">
				<c:set var="pageNum" value="<%=pageNum%>" />
				<c:forEach var="i" begin="1" end="<%=total_page%>">
					<a href="<c:url value='./BoardListAction.do?pageNum=${ i }'/>">
						<c:choose>
							<c:when test="${ i == pageNum }">
								<font color="4C5317"><b>[${ i }]</b></font>
							</c:when>
							<c:otherwise>
								<font color="4C5317">[${ i }]</font>
							</c:otherwise>
						</c:choose>
					</a>
				</c:forEach>
			</div>
			<div align="left">
				<table>
					<tr>
						<td width="100%" align="left">&nbsp;&nbsp; <select
							name="items" class="txt">
								<option value="subject">제목에서</option>
								<option value="content">본문에서</option>
								<option value="name">글쓴이에서</option>
						</select> <input name="text" type="text" /> <input type="submit"
							id="btnAdd" class="btn btn-primary" value="검색" />
						</td>
						<td width="100%" align="right"><a href="#"
							onclick="checkForm(); return false;" class="btn btn-primary">&laquo;글쓰기</a>
				</table>
			</div>
		</form>
	</div>
	<hr>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>