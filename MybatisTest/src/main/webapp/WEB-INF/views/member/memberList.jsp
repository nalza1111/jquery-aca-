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
	<div><h3>회원목록</h3></div>
	<div>
		<table class="table">
			<thead>
				<tr>
					<th>아이디</th>
					<th>이 름</th>
					<th>이메일</th>
					<th>권한</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${members }" var="m">
					<tr>
						<td align="center">${m.id }</td>
						<td align="center">${m.name }</td>
						<td align="center">${m.email }</td>
						<td align="center">${m.resposibility }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div><br>
	<div>
	<form id="frm">
		<input type="radio" name="author" value="all" onclick="getAuthor(event)">전체
		<input type="radio" name="author" value="user" onclick="getAuthor(event)">일반유저
		<input type="radio" name="author" value="admin" onclick="getAuthor(event)">관리자
	</form>
	</div>
	
<script type="text/javascript">
	function getAuthor(event){
		let author = event.target.value;
		let url = "ajaxMemberAuthorSelect.do";
		fetch(url,{
			method: 'POST',
			headers: {'content-Type':'application/x-www-form-urlencoded'},
			body: 'author=' + author
		}).then(response => response.json())  //결과를 json으로 받음
		  .then(data => htmlViews(data));	//성공시 화면 출력메소드 호출
	}

	function htmlViews(datas) {  //json을 html로 변환해서 화면에 뿌림
		document.querySelector('tbody').remove();  //<tbody> 삭제
		const container = document.createElement('tbody'); //<tbod>태그 생성
		container.innerHTML = datas.map(data => createHTMLString(data)).join("");  //Html 변환
		document.querySelector('table').appendChild(container);  //화면에 추가
	}
	
	function createHTMLString(data){  //html 변환 코드 css, event Listner를 활용하면 깔끔하게 정리됨
		let str = "<tr>";
			str += "<td align='center'>" + data.id + "</td>" ;
			str += "<td align='center'>" + data.name + "</td>";
			str += "<td align='center'>" + data.email + "</td>";
			str += "<td align='center'>" + data.resposibility + "</td>";
			str += "</tr>";
		return str;
	}
</script>
</div>
</body>
</html>