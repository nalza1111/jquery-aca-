/*
* Ajax get, post, patch, delete 구현
*/
const ajax = {
	get(url) {
		return fetch(url);
	},
	post(url,payload){
		return fetch(url,{
			method: 'POST',
			headers: {'content-Type':'application/x-www-form-urlencoded'},
			body: payload
		});
	},
	patch(url,payload){
		return fetch(url,{
			method:'PATCH',
			headers: {'content-Type':'application/json'},
			body: JSON.stringify(payload)
		});		
	},
	delete(url){
		return fetch(url,{method: 'DELETE'})
	}	
};

 /*
 *	LocalDateTime 변환
 *	LocalDateTime을 yyyy-mm-dd hh:mm:ss형식으로 보여주기
 */
function timestamp(date) {  
	let fomatData = new Date(date);
	console.log(fomatData.getTimezoneOffset());
	fomatData = new Date(fomatData.getTime() - (fomatData.getTimezoneOffset() * 60000));
	return fomatData.toISOString().replace('T', ' ').slice(0, 19);
}
/*
*  LocalDateTime을 yyyy-mm-dd 형식으로 보여주기
*/
function timedate(date) {  
	let fomatData = new Date(date);
	fomatData = new Date(fomatData.getTime() - (fomatData.getTimezoneOffset() * 60000));
	return fomatData.toISOString().slice(0, 10);
}
