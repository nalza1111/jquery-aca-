/**
 * 
 */
 
 $.ajax({
	url: './cd_catalog.xml',
	success: function(doc){
		//console.log(doc);
		// table, thead, tbody => show의 하위요소.
		console.log(doc);
		
		
		$('#show').append(
			$('<table />').append($('<thead id="title" />'),$('<tbody id="list" />')));
		
		let t = $(doc).find('CATALOG>CD');
		console.log(t);
		
		$.each(t, function(idx, item){
			$('<td />').text($(item).children().eq(0).html());//제이쿼리객체로표기
			$('<td />').text($(item).children().eq(1).html());//제이쿼리객체로표기
			$('<td />').text($(item).children().eq(2).html());//제이쿼리객체로표기
			$('<td />').text($(item).children().eq(3).html());//제이쿼리객체로표기
			$('<td />').text($(item).children().eq(4).html());//제이쿼리객체로표기
			$('<td />').text($(item).children().eq(5).html());//제이쿼리객체로표기
			
			$('#list').appen(tr);
		})
		$(doc).find('CATALOG>CD:').eq(0).children();
		for(let tilte of titles){
			console.log(title.nodeName);
		}
	},
	error: function(error){
		console.log(error);
	}
})