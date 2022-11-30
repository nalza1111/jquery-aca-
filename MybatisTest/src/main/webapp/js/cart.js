function number_format(amount) {
	return new Intl.NumberFormat('ko-KR', {
		style: 'currency',
		currency: 'KRW'
	}).format(amount);
}

// 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function () {
	if (this == 0) return 0;
	let regex = /(^[+-]?\d+)(\d{3})/;
	let nstr = (this + '');
	while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
	return nstr;
};

let basket = {
	cartCount: 0, //상품갯수.
	cartTotal: 0, //합계금액.
	delCheckedItem: function () {
		// 선택삭제기능.
		$('input:checkbox[name="buy"]').prop('checked',true).each(function(inx, item){
			console.log(item);
			console.log($(item).parentsUntil('.temBody'));
				$(item).parentsUntil('.tem').remove();
		})
		basket.reCalc();
		basket.updateUI();
	},
	delAllItem: function () {
		// 장바구니비우기.
		$('input:checkbox[name="buy"]').each(function(inx,item){
			console.log(item);
			console.log($(item).parentsUntil('.temBody'));
				$(item).parentsUntil('.tem').remove();
		})
		basket.reCalc();
		basket.updateUI();
	},
	reCalc: function () {
		// 전체금액
		basket.cartCount= 0; //상품갯수 초기화
		basket.cartTotal= 0;
		$('.p_price').each(function(inx,item){
			if(inx==0){return}
			console.log($(item).val());
			//각수량
			//console.log($($('.p_num')[inx]).val());
			basket.cartTotal = (basket.cartTotal-0) + ($(item).val()-0)*($($('.p_num')[inx]).val()-0);
			//console.log(basket.cartTotal);
		})
		$('#sum_p_price').text('합계금액: '+number_format(basket.cartTotal) +'원');
	},
	updateUI: function () {
		// 상품전체갯수 변경.
		$('.p_price').each(function(inx,item){
			if(inx==0){return}
			basket.cartCount = (basket.cartCount-0) + ($($('.p_num')[inx]).val()-0);
		})
		$('#sum_p_num').text('상품갯수: '+basket.cartCount);
	},
	changePNum: function (t,th) {
		// 상품수량 변경기능.
		//현재수량
		let thh = $(th).parent().parent().children()[0];
		//총합(돈)
		let to = $(th).parentsUntil('.row').find('.sum').text();
		to = to.substr(1).replace(',','');
		console.log(to);
		//단일가격
		let ea = $(th).parentsUntil('.tem').find('#p_price1').val();
		
		if(t == 'p'&&$(thh).val()<10){
			let value = ($(thh).val()-0)+(1-0);
			console.log(value);
			console.log($(thh).val(value));
			to = (to-0)+(ea-0);
			console.log(to);
			console.log($(th).parentsUntil('.tem').find('.sum').text())
			$(th).parentsUntil('.tem').find('.sum').text(number_format(to));
		} if(t == 'm'&&$(thh).val()>=1) {
			let value = ($(thh).val()-0)-(1-0);
			console.log(value);
			console.log($(thh).val(value));
			to = to-ea;
			console.log($(th).parentsUntil('.tem').find('.sum').text())
			$(th).parentsUntil('.tem').find('.sum').text(number_format(to));
			if(value==0){
				console.log($(th).parentsUntil('.temBody'));
				$(th).parentsUntil('.tem').remove()
			}
		}
		//개별 합계넣기
		basket.reCalc();
		basket.updateUI();
	},
	delItem: function (th) {
		// 삭제버튼.
		console.log($(th).parentsUntil('.temBody'));
		$(th).parentsUntil('.tem').remove()
		
		basket.reCalc();
		basket.updateUI();
	},
	makeList: function(result){
		//목록출력
		console.log(result);
		let temTop = $('.temTop');
		$.each(result, function(prop, item){
			let tem = $('.temBody>.tem').clone();
			//제품명
			tem.find('div.pname span').text(item.PRODUCT_NM);
			//가격
			let p = number_format(item.PRICE)
			//console.log(p);
			//console.log(tem.find('.basketprice').get()[0].childNodes[2]);
			//tem.find('.basketprice').text(30000)
			//tem.find('.basketprice').prepend($('<input />').attr('type','hidden').val(20000))
			tem.find('.basketprice').contents()[2].replaceWith(p);
			//히든가격
			tem.find('#p_price1').val(item.PRICE);
			//수량
			tem.find('#p_num1').val(item.QTY);
			temTop.append(tem);
			//총금액
			tem.find('.sum').text(number_format(item.QTY*item.PRICE));
		})
			//합계금액구하기
	},
	init: function() {
		// 상품목록 출력.
		let make =this.makeList;
		$.ajax({
		url: '../ajaxCartList.do',
		method: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		dataType: 'json',
		success: function(result){
				make(result);
				
				basket.reCalc();
				basket.updateUI();
			},
		error: function(error){
				console.log(error);
			}
		})
		
	}
}

basket.init();
