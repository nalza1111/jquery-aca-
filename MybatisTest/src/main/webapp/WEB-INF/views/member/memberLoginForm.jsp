<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버로그인</title>
</head>
<body>
<div align="center">
	<div><h1>로 그 인</h1></div>
	<div>
		<form id="frm" action="memberLogin.do" method="post">
			<div>
				<table border="1">
					<tr>
						<th width="100">아이디</th>
						<td width="150">
							<input type="text" id="id" name="id">
						</td>
					</tr>
					<tr>
						<th>패스워드</th>
						<td>
							<input type="password" id="passwd" name="passwd">
						</td>
					</tr>
				</table>
			</div><br/>
			<div>
				<input type="submit" value="로그인">&nbsp;&nbsp;
				<input type="reset" value="취소">&nbsp;&nbsp;
				<input type="button" value="홈 가기" onclick="location.href='main.do'">
			</div>
		</form>
	</div>
</div>
</body>
</html>