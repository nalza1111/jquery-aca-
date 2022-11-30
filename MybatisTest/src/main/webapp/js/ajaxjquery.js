/**
 *  ajaxjquery.js
 */
console.log('ajaxjquery')

$(function() {
	$.ajax({
		url: 'ajaxBookList.do',
		method: 'get',
		dataType: 'json',
		success: function(result) {
			console.log(result); // [{},{},{}]
			$.each(result, function(prop, item){
				$('#list').append(makeTr(item));
			})
		},
		error: function(error) {
			console.log(error);
		}
	});
	
	//등록이벤트
	$('#addBtn').on('click', addBookFnc);
	
	//컨테이너 관련된 거 올라옴
	
	
	$("input[type='text']").keyup(function(event){
		if(event.which===13){
			$('.container #changeBtn').click();
		}
	})
});



function addBookFnc(){
	let code = $('input[name="bCode"]').val();
	let title = $('input[name="bTitle"]').val();
	let author = $('input[name="bAuthor"]').val();
	let press = $('input[name="bPress"]').val();
	let price = $('input[name="bPrice"]').val();
		
	console.log($('form[name="myfrm"]').serialize()); //키 밸류형식으로 만들기	
		
	$.ajax({
		url: 'ajaxBookAdd.do',
		method: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		//data: {bCode: code, bTitle: title, bAuthor: author, bPress: press, bPrice: price},
		data: $('form[name="myfrm"]').serialize(),
		dataType: 'json',
		success: function(result){
			console.log(result);
			$('#list').append(makeTr(result));
			init();
		},
		error: function(error){
			console.log(error);
		}
	})
}
function init(){
	$('input[name="bCode"]').val("");
	$('input[name="bTitle"]').val("");
	$('input[name="bAuthor"]').val("");
	$('input[name="bPress"]').val("");
	$('input[name="bPrice"]').val("");
	
}


function makeTr(book = {bookCode:"", bookTitle:"", bookAuthor:"",bookPress:"", bookPrice:""}){
	return $('<tr />').append(
		$('<td />').text(book.bookCode).hover(function(){searchDetail(book)},function(){searchDetailNo()}),
		$('<td />').text(book.bookTitle).hover(function(){searchDetail(book)},function(){searchDetailNo()}),
		$('<td />').text(book.bookAuthor).hover(function(){searchDetail(book)},function(){searchDetailNo()}),
		$('<td />').text(book.bookPress).hover(function(){searchDetail(book)},function(){searchDetailNo()}),
		$('<td />').text(book.bookPrice).hover(function(){searchDetail(book)},function(){searchDetailNo()}),
		$('<td />').append($('<button />').text('수정').on('click', book, modifyFrm)),
		$('<td />').append($('<button />').text('삭제').on('click', book, deleteData))
	).on('click',function(){ //모달창기능 넣을 거
		$('#id01').css('display', 'block');
	});
}


function modalModify(){
	
}


//한건조회
function searchDetail(e){
	let btn = $(this);
	console.log(btn.parent().parent().parent());
	btn.parent().parent().css('background-color', 'yellow');
	console.log(e);
	let book = {bookCode:e.bookCode}

	$.ajax({
		url: 'ajaxBookSearch.do',
		method: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: book,
		dataType: 'json',
		success: function(result){
			console.log(result)
			if(result == 'Fail'){
				alert('처리된 건수가 없습니다.')
			} else{
				console.log(result);
				mkDetail(result);
			}
		},
		error: function(error){console.log(error)}
	})

}
//호버끝
function searchDetailNo(){
	$('#content>div:eq(0)>span:eq(1)').text("");
	$('#content>div:eq(1)>span:eq(1)').text("");
	$('#content>div:eq(2)>span:eq(1)').text("");
	$('#content>div:eq(3)>span:eq(1)').text("");
	$('#content>div:eq(4)>span:eq(1)').text("");
}
//make
function mkDetail(result){
	console.log(result+"mkDetail??")
	console.log(result.bookCode);
	$('#content>div:eq(0)>span:eq(1)').text("");
	$('#content>div:eq(1)>span:eq(1)').text("");
	$('#content>div:eq(2)>span:eq(1)').text("");
	$('#content>div:eq(3)>span:eq(1)').text("");
	$('#content>div:eq(4)>span:eq(1)').text("");

	searchDetailNo()
}

//삭제
function deleteData(e){
	console.log(e.data);
	//삭제컨트롤
	//수정컨트롤.
	let btn = $(this);
	console.log(btn);
	let book = {bookCode:e.data.bookCode}
	$.ajax({
		url: 'ajaxBookDelete.do',
		method: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: book,
		success: function(result){
			console.log(result)
			if(result == 'Success'){
				btn.parentsUntil('#list').remove();
			} else if (result == 'Fail'){
				alert('처리된 건수가 없습니다.')
			}
		},
		error: function(error){console.log(error)}
	})
}
function modifyFrm(e){ //e벤트의 데이터 값으로 book이 들어옴
	console.log(e.data);
	localStorage.setItem('code', e.data.bookCode); //로컬스토리지에 값저장.. getItem
	localStorage.setItem('title', e.data.bookTitle); //로컬스토리지에 값저장.. getItem
	localStorage.setItem('author', e.data.bookAuthor); //로컬스토리지에 값저장.. getItem
	localStorage.setItem('press', e.data.bookPress); //로컬스토리지에 값저장.. getItem
	localStorage.setItem('price', e.data.bookPrice); //로컬스토리지에 값저장.. getItem

	let newTr = $('<tr />').append(
		$('<td />').text(e.data.bookCode),
		$('<td />').append($('<input />').val(e.data.bookTitle).on('change',function(){localStorage.setItem('title', $(this).val())})),
		$('<td />').append($('<input />').val(e.data.bookAuthor).on('change',function(){localStorage.setItem('author', $(this).val())})),
		$('<td />').append($('<input />').val(e.data.bookPress).on('change',function(){localStorage.setItem('press', $(this).val())})),
		$('<td />').append($('<input />').val(e.data.bookPrice).on('change',function(){localStorage.setItem('price', $(this).val())})),
		$('<td />').append($('<button />').text('변경').on('click', modifyData))
	);
	$(this).parentsUntil('#list').replaceWith(newTr);
}

function modifyData(e) {
	console.log(localStorage.getItem('code'));
	let code = localStorage.getItem('code');
	let title = localStorage.getItem('title');
	let author = localStorage.getItem('author');
	let press = localStorage.getItem('press');
	let price = localStorage.getItem('price');

	let book = {bookCode:code, bookTitle:title, bookAuthor:author, bookPress:press, bookPrice:price}
	let btn = $(this); //이벤트 $(this) => 버튼.
	//수정컨트롤.
	$.ajax({
		url: 'ajaxBookModify.do',
		method: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: book,
		success: function(result){
			console.log(result)
			if(result == 'Success'){
				let newTr = makeTr(book);
				btn.parentsUntil('#list').replaceWith(newTr);
			} else if (result == 'Fail'){
				alert('처리된 건수가 없습니다.')
			}
		},
		error: function(error){console.log(error)}
	})

	localStorage.removeItem('code');
	localStorage.removeItem('title');
	localStorage.removeItem('author');
	localStorage.removeItem('press');
	localStorage.removeItem('price');
}