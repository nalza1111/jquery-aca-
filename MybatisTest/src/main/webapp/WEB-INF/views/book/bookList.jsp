<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<div><h1>책 목록보기</h1></div>
	<div>
		<c:forEach items="${books }" var="b">
			${b.bookCode } : ${b.bookTitle } : ${b.bookAuthor }<br/>
		</c:forEach>
		<button type="button" onclick="location.href='bookInsertForm.do'">도서등록</button>
	</div>
</div>
</body>
</html>