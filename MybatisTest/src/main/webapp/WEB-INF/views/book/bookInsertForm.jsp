<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<div><h3>도서등록</h3></div>
	<div>
		<form id="frm" action="bookInsert.do" method="post" enctype="multipart/form-data">
			<div>
				<table class="table">
					<tr>
						<th width="150">도서코드</th>
						<td width="250">
							<input type="text" id="bookCode" name="bookCode">
						</td>
					</tr>
					<tr>
						<th width="150">도서명</th>
						<td width="250">
							<input type="text" id="bookTitle" name="bookTitle">
						</td>
					</tr>
					<tr>
						<th width="150">저자</th>
						<td width="250">
							<input type="text" id="bookAuthor" name="bookAuthor">
						</td>
					</tr>
					<tr>
						<th width="150">출판사</th>
						<td width="250">
							<input type="text" id="bookPress" name="bookPress">
						</td>
					</tr>
					<tr>
						<th width="150">가격</th>
						<td width="250">
							<input type="text" id="bookPrice" name="bookPrice">
						</td>
					</tr>
					<tr>
						<th width="150">도서이미지1</th>
						<td width="250">
							<input type="file" id="file1" name="file1">
						</td>
					</tr>
					<tr>
						<th width="150">도서이미지2</th>
						<td width="250">
							<input type="file" id="file2" name="file2">
						</td>
					</tr>
					<tr>
						<th width="150">도서이미지3</th>
						<td width="250">
							<input type="file" id="file3" name="file3">
						</td>
					</tr>
				</table>
			</div><br>
			<div>
				<input type="submit" value="등록">
			</div>
		</form>
	</div>
</div>
</body>
</html>