/**
 * 
 */

class Person{
	constructor(name, age, tel){ // 생성자 
		this.name = name;
		this.age = age;
		this.tel = tel;
	}
	
	set age(nai){ // setter
		// private name : _name (언더바) 
		this._age = nai;		
	}
	
	get age(){
		return this._age;
	}
	
	toJSON(){
		return `{"name":"${this.name}", "age":"${this.age}", "tel":"${this.tel}"}`;
	}
}