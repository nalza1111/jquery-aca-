<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		#boardBody{
			margin: 50px;
			width: 800px;
			height: 300px;
		}
		#replyTable {
			width: 800px;
		}
		.boardHead{
			background-color: bisque;
			border-radius: 15px;
			text-align: center;
		}
		wrapper{
			width: 900px;
			display: inline-block;
			border: 1px solid black;
			margin: 20px 50px;
		}
		textarea{
			width: 600px;
            height: 100px;
			padding: 20px;
        }
		#replyBox {
			width: 800px;
		}
		#replyBox button{
			float: right;
			margin-top: 50px;
			width: 80px;
            height: 50px;
			background-color: rgb(75, 96, 215);
			color: white;
			font-size: 12px;
		}
		#replyTable{
			border-collapse: collapse;
			margin-top: 50px;
			margin-bottom: 50px;
		}
		#replyTBody>tr:nth-child(2n){
			border-bottom: 1px solid rgb(159, 130, 94);
		}

	</style>
	<script src="./js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<wrapper>
		<table id="boardBody">
			<tr>
				<td class="boardHead">글번호:</td>
				<td class="bno"></td>
				<td class="boardHead">제목:</td>
				<td class="bti"></td>
				<td class="boardHead">조회수:</td>
				<td class="cli"></td>
			</tr>
			<tr>
				<td class="boardHead">내용:</td>
				<td colspan="5" rowspan="3" class="con"></td>
			</tr>
			<tr><td>&nbsp; &nbsp;</td></tr>
			<tr><td>&nbsp; &nbsp;</td></tr>
			<tr>
				<td class="boardHead">이미지:</td>
				<td colspan="5"  class="img"></td>
			<tr>
				<td class="boardHead">작성자:</td>
				<td class="wri"></td>
				<td class="boardHead">작성일자:</td>
				<td class="date"></td>
				<td class="deleteDetail"></td>
			</tr>
		</table>
	</wrapper>
	<div id="replyBody">
		<table id="replyTable">
			<tbody  id="replyTBody">
			</tbody>
		</table>
	</div>
	<div id="replyBox">
		<input type="hidden" value="${sessionScope.id }">
		<input type="hidden" value="${board.boardNo}">
		<textarea placeholder="글을 등록하세요." row="30" cols="20"  ></textarea>
		<button>글쓰기</button>
	</div>
	<script>

		$(document).ready(function(){
			//댓글가져오기
			//let no = $('input[type="hidden"]:eq(-1)').val();
			let no = sessionStorage.getItem('boardNo');
			sessionStorage.setItem('boardNo', no);
			console.log(no);
			
			let rep = {no:no};
			$.ajax({
                url: 'replyInsert.do',
                method: 'post',
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				data: rep,
                dataType: 'json',
                success: function(result){
                   	console.log(result);
					$.each(result, function(prop, row){
						$('#replyTBody').append(makeRep(row, prop));
					})
					delEventNull();
                },
                error: function(error){
                    console.log(error);
                }
            })
			//글상세보기
			$.ajax({
                url: 'detailBoard2.do',
                method: 'post',
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				data: rep,
                dataType: 'json',
                success: function(result){
                   	console.log(result);
					$('.bno').text(result.boardNo);
					$('.bti').text(result.title);
					$('.cli').text(result.clickCnt);
					$('.con').text(result.content);
					$('.img').text(result.image);
					$('.wri').text(result.writer);
					$('.date').text(result.writeDate);
					$('.deleteDetail').append($('<input />').attr('type','button').prop('id',result.writer).val('삭제')
						.on('click',result, boardDelete)	
					)
					delEventNull2();
                },
                error: function(error){
                    console.log(error);
                }
            })
		})

		function boardDelete(e){
			let tf = confirm('삭제하시겠습니까?');
			if(!tf){
				return;
			}
			//글번호
			console.log(e.data);
			console.log(e.data.boardNo);
			let bno = e.data.boardNo;

			rep = {bno:bno}
			console.log(rep);

			$.ajax({
                url: 'boardDelete.do',
                method: 'post',
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                data: rep,
                success: function(result){
					location.href  = 'boardForm.do';
                },
                error: function(error){
                    console.log(error);
                }
            })

		}
		function makeRep(row , prop){
			return ($('<tr />').append(
				$('<td />').text("No: "+(++prop)+" "+(row.replyWriter)),
				$('<td />').attr('rowspan','2').text(row.replyContent),
				$('<td />').attr('rowspan','2').append($('<button />').addClass('del').attr('id', row.replyWriter).text('삭제')
				.on('click',deleteRep)),
				$('<input />').attr('type','hidden').val(row.replyNo)
				))
				.add($('<tr />').append($('<td />').text(row.replyDate)));
				
		}

		//댓글삭제
		function deleteRep(){
			let tf = confirm('삭제하시겠습니까?');
			if(!tf){
				return;
			}
			//댓글번호
			//console.log($(this).parent().parent().children('input[type="hidden"]').val());
			let repNo = $(this).parent().parent().children('input[type="hidden"]').val();
			let no = sessionStorage.getItem('boardNo');
			rep = {repNo:repNo, bno:no}
			console.log(rep);

			$.ajax({
                url: 'replyDelete.do',
                method: 'post',
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                data: rep,
                dataType: 'json',
                success: function(result){
					$('#replyTBody').children().remove();
                   	console.log(result);
					   $.each(result, function(prop, row){
						$('#replyTBody').append(makeRep(row, prop));
					})
					delEventNull();
                },
                error: function(error){
                    console.log(error);
                }
            })
		}



		$('button').on('click', replyInsert)
		
		function replyInsert(){
			let id = $('input[type="hidden"]:eq(-2)').val();
			//let no = $('input[type="hidden"]:eq(-1)').val();
			let no = sessionStorage.getItem('boardNo');
			console.log(no);

			if(id==''){
				alert('guest는 댓글작성권한이 없습니다.')
				return;
			}
			let txt = $('textarea').val();
			let rep = {id:id, content:txt, no:no};

			console.log(rep);

			$.ajax({
                url: 'replyInsert.do',
                method: 'post',
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                data: rep,
                dataType: 'json',
                success: function(result){
					$('textarea').val('');
					$('#replyTBody').children().remove();
                   	console.log(result);
					   $.each(result, function(prop, row){
						$('#replyTBody').append(makeRep(row, prop));
					})
					delEventNull();
                },
                error: function(error){
                    console.log(error);
                }
            })
		}

		//자기거아닌 댓글 삭제버튼 비활성화
		//자기글 아닌 글 삭제버튼 비활성화
		function delEventNull(){
			let id = $('input[type="hidden"]:eq(-2)').val();
			$('.del').each(function(a,b){
				if(b.id!=id){
					$(this).hide();
				}
			})
			
		}
		function delEventNull2(){
			let id = $('input[type="hidden"]:eq(-2)').val();
			console.log('자동 삭제 버튼의 글쓴이아이디가져오기 '+($('input[type="button"]').prop('id')))
			if($('input[type="button"]').prop('id')!=id){
				$('input[type="button"]').hide();
				}
		}
	</script>
</body>
</html>