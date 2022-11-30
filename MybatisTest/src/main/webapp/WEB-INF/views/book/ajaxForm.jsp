<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="./css/modal.css">
<style>
	#list input{
		widht:80px;
	}
	#content {
		width: 300px;
		border: 1px solid rebeccapurple;
		margin-top: 20px;
	}

	#content span:nth-child(1) {
		display: inline-block;
		width: 80px;
		margin-left: 5px;
	}

	#content span:nth-child(2) {
		margin-right: 5px;
		display: inline-block;
		width: 200px;
	}
	
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="./js/ajaxjquery.js"></script>

<div id="header">
	<form name="myfrm">
	  <label>도서코드: <input type="text" name="bCode"></label><br>
	  <label>도서명: <input type="text" name="bTitle"></label><br>
	  <label>저자: <input type="text" name="bAuthor"></label><br>
	  <label>출판사: <input type="text" name="bPress"></label><br>
	  <label>가격: <input type="number" name="bPrice"></label><br>
	  <input type="button" id="addBtn" value="등록">
	</form>
</div>

<div id="show">
	<table border="1">
		<thead>
			<tr>
				<th>도서코드</th>
				<th>도서명</th>
				<th>저자</th>
				<th>출판사</th>
				<th>가격</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody id="list"></tbody>
	</table>
</div>
<div id="content">
	<!-- 도서코드, 도서명, 도서저자, 출판사, 가격 -->
	<div><span>도서코드:</span> <span>C001</span></div>
	<div><span>저자:</span> <span>김자바</span></div>
	<div><span>도서명:</span> <span>이것이자바다</span></div>
	<div><span>출판사:</span> <span>이지스퍼블리싱</span></div>
	<div><span>가격:</span> <span>20,000원</span> </div>
</div>
<div id="id01" class="modal">

	<form class="modal-content animate" action="/action_page.php" method="post">
		<div class="imgcontainer">
			<span onclick="document.getElementById('id01').style.display='none'" class="close"
				title="Close Modal">&times;</span>
		</div>

		<div class="container">
			<label for="uname"><b>도서코드</b></label>
			<input type="text" value="20221123-001" name="bCode"><br>

			<label for="psw"><b>도서명</b></label>
			<input type="text" value="이것은자바인가?" name="bTitle"><br>

			<label for="psw"><b>저자</b></label>
			<input type="text" value="이자바" name="bAuthor"><br>

			<label for="psw"><b>출판사</b></label>
			<input type="text" value="우리출판사" name="bPress"><br>

			<label for="psw"><b>가격</b></label>
			<input type="number" value="20000" name="bPrice">
		</div>

		<div class="container" style="background-color:#f1f1f1">
			<button type="button" onclick="document.getElementById('id01').style.display='none'"
				class="cancelbtn">Cancel</button>
			<button type="button" class="changeBtn">changeBtn</button>
			<button type="button">OK</button>
		</div>

	</form>
</div>
