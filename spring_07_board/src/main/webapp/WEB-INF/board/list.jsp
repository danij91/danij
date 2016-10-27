<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
</head>
<body>

	<form action="write.sb" id="frm" name="frm" method="get">
		<input type="submit" id="btnWrite" value="글쓰기" />
	</form>
	<!-- 리스트출력 -->
	<div id="bodywrap">
		<table>
			<tr>
				<th width="5%">번호</th>
				<th width="45%">제목</th>
				<th width="20%">글쓴이</th>
				<th width="5%">조회수</th>
			</tr>
			<c:forEach var="dto" items="${aList}">
				<tr>
					<td>${dto.num}</td>
					<td><c:url var="content" value="view.sb">
							<c:param name="currentPage" value="${pv.currentPage}" />
							<c:param name="num" value="${dto.num}" />
						</c:url> <c:if test="${dto.re_level!=0}">
							<img src="images/level.gif" width="${20*dto.re_level}"
								height="15">
							<img src="images/re.gif">
						</c:if> <a href="${content}">${dto.subject}</a></td>
					<td>${dto.writer}</td>
					<td>${dto.readcount}</td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${pv.startPage>1}">
			<a href="list.sb?currentPage=${pv.startPage-pv.blockPage}"> <c:out
					value="이전" />
			</a>
		</c:if>
		<c:forEach begin="${pv.startPage}" end="${pv.endPage}" var="i">
			<c:url var="currPage" value="list.sb">
				<c:param name="currentPage" value="${i}" />
			</c:url>
			<a href="${currPage}"> <c:out value="${i}" />
			</a>
		</c:forEach>
		<c:if test="${pv.endPage<pv.totalPage}">
			<a href="list.sb?currentPage=${pv.startPage+pv.blockPage}"> <c:out
					value="다음" />
			</a>
		</c:if>
	</div>
</body>
</html>