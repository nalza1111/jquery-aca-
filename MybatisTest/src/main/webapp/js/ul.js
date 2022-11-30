/**
 * ul.js
 */
 
 export default class Ul{
	constructor(data){
		this.data = data;
		this.makeUl();
		return this.ul;
	}
	makeUl(){
		this.ul = document.createElement('ul');
		this.makeLi();
	}
	makeLi(){
		for(let item of this.data){
			let li = document.createElement('li')
			li.innerText=item;
			this.ul.append(li);
		}
	}
}