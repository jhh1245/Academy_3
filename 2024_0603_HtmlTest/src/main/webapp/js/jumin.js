/**
 * 
 */

 class Jumin{
	
	constructor(jumin_no){
		this.jumin_no = jumin_no;
	}
	
	//                  01234567890123  <- index  
	// this.jumin_no = "901222-1234568";
	
	/*
	       내국인     외국인
	       남  여     남  여
	 1900  1   2      5   6       
	 2000  3   4      7   8
	 1800  9   0
	*/
	
	//출생년도
	getYear(){
		let year   = parseInt(this.jumin_no.substr(0,2));
		let gender = parseInt(this.jumin_no.charAt(7)); 
		
		switch(gender)
		{
			case 1: 
			case 2: 
			case 5: 
			case 6: year+=1900; break;
			case 3: 
			case 4: 
			case 7: 
			case 8: year+=2000; break;
			default: year+=1800;
		}
		
		return year;
	}
	
	//나이
	getAge(){
		//자바스크립트에서 현재날짜구해서 처리
		let age = 2024 - this.getYear();
		return age;
	}
	
	//띠
	getTti(){
		let tti_index = this.getYear() % 12;
		switch(tti_index) {
		case 0: return "원숭이";
		case 1: return "닭";
		case 2: return "개";
		case 3: return "돼지";
		case 4: return "쥐";
		case 5: return "소";
		case 6: return "범";
		case 7: return "토끼";
		case 8: return "용";
		case 9: return "뱀";
		case 10: return "말";
		case 11: return "양";
		
		
		} return "다시입력";
	}
	
	//성별
	getGender(){
		
		let gender = parseInt(this.jumin_no.charAt(7)); 
		if (gender% 2 == 0) { 
			return "여자";
		} else {
			return "남자";
		}
	}
	
	//계절
	getSeason(){
		let season = parseInt(this.jumin_no.substr(2,2));
		switch(parseInt(season/3)){
		case 1:
			return "봄"; 
		case 2: 
			return "여름";
		case 3:
			return "가을";
		default :
			return "겨울";
		}
	}
	
	//지역
	getLocal(){
		let local = parseInt(this.jumin_no.substr(8,2));
		if(local >= 0 && local <= 8) {
			return "서울";
		} 
		if(local >= 9 && local <= 12) {
			return "부산";
		} 
		if(local >= 13 && local <= 15) {
			return "인천";
		} 
		if(local >= 16 && local <= 25) {
			return "경기도";
		}
		if(local >= 26 && local <= 34) {
			return "강원도";
		} 
		if(local >= 35 && local <= 39) {
			return "충척북도";
		} 
		if(local == 40) return "대전";
		if(local >= 41 && local <= 47) return "충청남도";
		if(local >= 48 && local <= 54) return "전라북도";
		
		if (local == 55 || local == 56) return "광주";
	}
	
	//간지
	getGanji(){
		let gan_list = "경신임계갑을병정무기";
		let ji_list = "신유술해자축인묘진사오미";
		
		let gan = gan_list.charAt(this.getYear() % 10);
		let ji = ji_list.charAt(this.getYear() % 12);
		
		return (gan+"" + ji + "년");
	}
	
	//주민번호 체크알고리즘
	
	//                  01234567890123  <- index  
	// this.jumin_no = "901222-1234568";
	//                  2345670892345  	
	isValid(){
          
        let sum = 0;
        sum += parseInt(this.jumin_no.charAt(0)) * 2 ;
        sum += parseInt(this.jumin_no.charAt(1)) * 3 ;
        sum += parseInt(this.jumin_no.charAt(2)) * 4 ;
        sum += parseInt(this.jumin_no.charAt(3)) * 5 ;
        sum += parseInt(this.jumin_no.charAt(4)) * 6 ;
        sum += parseInt(this.jumin_no.charAt(5)) * 7 ;
        
        sum += parseInt(this.jumin_no.charAt(7))  * 8 ;
        sum += parseInt(this.jumin_no.charAt(8))  * 9 ;
        sum += parseInt(this.jumin_no.charAt(9))  * 2 ;
        sum += parseInt(this.jumin_no.charAt(10)) * 3 ;
        sum += parseInt(this.jumin_no.charAt(11)) * 4 ;
        sum += parseInt(this.jumin_no.charAt(12)) * 5 ; 		
		
		let check_sum = (11-(sum%11))%10;
		console.log("check_sum", check_sum);
		
		let last_num = parseInt(this.jumin_no.charAt(13));
		
		return ( last_num == check_sum );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
 }