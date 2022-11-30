<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h1>WellCome To My WebSite</h1>
	<c:if test='${id eq "park"  || respsibility eq "admin"}'>
		<a id="aa" onclick="ondis()">호호</a>
	</c:if>
	<c:if test='${empty id }'>호호</c:if>
</div>

<script type="text/javascript">
	ondis = function() {
		alert("보여주는 군");
	}
</script>
</body>
</html>
