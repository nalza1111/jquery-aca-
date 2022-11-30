/**
 * emp.js
 */

function CF_toStringByFormatting(source){
	var date = new Date(source);
	const year = date.getFullYear();
	const month = CF_leftPad(date.getMonth() + 1);
	const day = CF_leftPad(date.getDate());
	return [year, month, day].join('-');
}
function CF_leftPad(value){
	if (Number(value) >= 10) {
		return value;
	}
	return "0" + value;
}
let emp = {
	ini: function(){
		//초기화
		$('#form1').find('input[type=text]').val('');
		$('#form1').find('input[type=date]').val('');
		$('.form-select').val("").prop('selected',true);
	},
	update: function(){
	console.log($('#form1').serialize()); //키 밸류형식으로 만들기	
	$.ajax({
			url: '../empUpdate.do',
			method: 'post',
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			data: $('#form1').serialize(),
			dataType: 'json',
			success: function(result){
				console.log(result);
				if(result!=null){
					$(empId).each(function(inx, item){
						if($(item).text()==result.employeeId){
							$(item).parent().children().eq(1).text(result.firstName+' '+result.lastName);
							$(item).parent().children().eq(2).text(result.jobTitle);
						}
					})
				}
			},
			error: function(error){
				console.log(error);
				alert('빈칸있음');
			}
		})
		emp.ini();
	},
	insert: function(){
	console.log($('#form1').serialize()); //키 밸류형식으로 만들기	
	$.ajax({
			url: '../empInsert.do',
			method: 'post',
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			data: $('#form1').serialize(),
			dataType: 'json',
			success: function(result){
				//사원번호
				console.log(result);
				if(result!=null){
					emp.makerow(result);
				}
			},
			error: function(error){
				console.log(error);
				alert('빈칸있음');
			}
		})
		emp.ini();
	},
	makeList: function(result) {
		//목록출력
		console.log(result);
		$.each(result, function(inx, item) {
			emp.makerow(item);
		})
	},
	//한줄
	makerow: function(item){
		$('tbody').append($('<tr />').append(
			$('<td class="text-center" id="empId"/>').text(item.employeeId),
			$('<td class="text-center"/>').text(item.firstName + ' ' + item.lastName),
			$('<td class="text-center"/>').text(item.jobTitle),
			$('<td class="text-center"/>').append(($('<button class="btnUpd"/>').text('조회').on('click', emp.searchEmp)),
				($('<button class="btnDel"/>').text('삭제').on('click', emp.delEmp)))
				)
		 	)
	},
	sel: function(){
		$.ajax({
			url: '../jobSelect.do',
			method: 'post',
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			dataType: 'json',
			success: function(result) {
				console.log(result);
				$.each(result, function(inx, item) {
					$('.form-select').append(
										$('<option value="'+item.JOB_ID+'" />').text(item.JOB_TITLE)
				 					)
				})
			},
			error: function(error) {
				console.log(error);
			}
		})
	},
	delEmp: function(){
		let empId = $(this).parentsUntil('tbody')[1].children[0].innerText;
		let emp2 = {empId:empId};
		let tf = 0;
		$.ajax({
			url: '../empDelete.do',
			method: 'post',
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			data: emp2,
			async: false,
			success: function(result) {
				tf=result;
				console.log('삭제'+tf);
			},
			error: function(error) {
				console.log(error);
			}
		})
		if(tf==1){
				$(this).parentsUntil('tbody')[1].remove();
			}
		/*fetch('../empDelete.do?empId='+empId)
		.then(response=>console.log(response))
		.catch(error=>console.log(error));
		console.log('2');*/
	},
	searchEmp: function(){
		let empId = $(this).parentsUntil('tbody')[1].children[0].innerText;
		let emp2 = {empId:empId};
		let tf = 0;
		$.ajax({
			url: '../empSelect.do',
			method: 'post',
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			data: emp2,
			dataType: 'json',
			async: false,
			success: function(result) {
				console.log(result);
				$('#form1 div:eq(0)').find('input').val(result.employeeId);
				$('#form1 div:eq(1)').find('input')[0].value = result.firstName;
				$('#form1 div:eq(1)').find('input')[1].value = result.lastName;
				$('#form1 div:eq(2)').find('input').val(result.email);
				$('#form1 div:eq(3)').find('input').val(result.hireDate);
				$('.form-select').val(result.jobId).prop('selected',true);
			},
			error: function(error) {
				console.log(error);
			}
		})
	},
	init: function() {
		// 사원목록 출력.
		let make = this.makeList;
		$.ajax({
			url: '../empList.do',
			method: 'post',
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			dataType: 'json',
			success: function(result) {
				make(result);
			},
			error: function(error) {
				console.log(error);
			}
		})
	}
}

emp.init();
emp.sel();