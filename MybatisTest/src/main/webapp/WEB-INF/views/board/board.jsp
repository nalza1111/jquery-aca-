<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
    <style>
        #write{
            margin: 50px;
            width: 500px;
            height: 200px;
            border: 1px solid;
            padding: 25px 50px 50px 50px;
        }
        label {
            float: left;
            width: 500px;
            padding: 10px 0px;
        }
        label>p{
            float: left;
            margin: 0px;
            padding: 0px;
            width: 120px;
        }
        #title, #content, #writer{
            float: left;
        }
        #title{
            width: 370px;
        }
        textarea{
            height: 100px;
        }
        table{
            margin: 50px;
            border: 1px solid black;
            border-collapse: collapse;
            border-left: none;
            border-right: none;
            width: 800px;
        }
        th{
        	width:33.33%;
            height: 40px;
            border: 1px solid black;
            border-left: none;
            border-right: none;
        }
        td{
            height: 30px;
            border: 1px solid black;
            border-left: none;
            border-right: none;
            text-align: center;
            
            
        }
        td:nth-child(2):hover{
            cursor: pointer;
            text-decoration: underline;
            text-decoration-color: blue;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
    <c:if test="${not empty id}">
    <form id="write" name="write">
        <label for="title"><p>제목</p>
            <input type="text" id="title" name="title" placeholder="jquery 작성하기.">
        </label>
        <label for="content"><p>내용</p>
            <textarea id="content" name="content" row="50" cols="50"  placeholder="jquery로 게시판 작성하기를 해보고 있습니다."></textarea>
        </label>
        <label for="writer"><p>작성자</p>
            <input type="text" id="writer" name="writer" value="${sessionScope.id }" readonly>
        </label>
		<!-- <c:if test="${empty id }">
			<label for="writer"><p>작성자</p>
                <input type="text" id="writer" name="writer"  value="guest" readonly>
            </label>
		</c:if> -->
        <button id="insert">등록</button>
        <input type="reset" value="취소">
    </form>
    </c:if>
    <table>
        <thead>
            <th>No.</th>
            <th>제목</th>
            <th>작성자</th>
        </thead>
        <tbody></tbody>
    </table>
    
    <div class="pagenation">
    	<c:if test="${page.prev}">
    		<a href="bulletin.do?page=${page.startPage-1}">&lt;&lt;</a>
    	</c:if>
    	
    	<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}" step="1">
		<c:choose>
			<c:when test="${page.pageNum==i}">
				<a class ='active' href="bulletin.do?page=${i }">${i}</a>
			</c:when>
			<c:otherwise>
				<a href="bulletin.do?page=${i }">${i }</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${page.next}"><!-- 참이면 -->
		<a href="bulletin.do?page=${page.endPage+1}">&gt;&gt;</a>
	</c:if>
	
    </div>
    
    <script>
    	$(document).ready(make());
	   function make(){
		   $('tbody').children().remove();
    		$.ajax({
	            url: 'getBoard.do',
	            method: 'get',
	            dataType: 'json',
	            success: function(result){
	                //console.log(result);
	                $.each(result, function(prop, row){
	                    $('tbody').append(makeTd(row));
	                })
	            },
	            error: function(error){
	                console.log(error);
	            }
	        });
    	}
        function makeTd(row){
            //console.log(row)
            return $('<tr />').append(
                $('<td />').text(row.boardNo),
                $('<td />').text(row.title),
                $('<td />').text(row.writer),
            ).on('click', row, newWindow)
            
        }
        function newWindow(e) {
			
            let boardNo = e.data.boardNo;
            console.log(boardNo);
            sessionStorage.setItem('boardNo', boardNo);
            location.href = 'detailBoard.do';

            //console.log(e.data);
            //let board ={boardNo:e.data.boardNo}
            // $.ajax({
            //     url: 'detailBoard.do',
            //     method: 'post',
            //     contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            //     data: board,
            //     dataType: 'html',
            //     success: function(result){
            //        	$('body').children().remove();
            //        	$('body').html(result);
            //     },
            //     error: function(error){
            //         console.log(error);
            //     }
            // })
		}
        
        
        $('#insert').on('click', function(e){
        	e.preventDefault();
            let title = $('#title').val();
            let content = $('#content').val();
            let writer = $('#writer').val();

            if(!title||!content){
                alert('타이틀 글써서 제출')
            }

            console.log($('form[name="write"]').serialize());

           $.ajax({
                url:'boardInsert.do',
                method: 'post',
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                data: $('form[name="write"]').serialize(),
                dataType:'json',
                success: function(result){
                    $('tr').remove();
                    make();
                    $('#title').val("");
                    $('textarea').val("");
                },
                error: function(error){
                    console.log(error);
                }
           }) 
            
        })
    </script>
</body>
</html>