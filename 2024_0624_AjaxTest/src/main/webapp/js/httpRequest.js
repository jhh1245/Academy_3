var xhr = null;

function createRequest(){
	if(xhr!=null) return; // null이 아니면 (이미 실행한적 있으면 하지 말아라)
	if(window.ActiveXObject)
		xhr = new ActiveXObject("Microsoft.XMLHTTP");
	else
		xhr = new XMLHttpRequest();
}


function sendRequest(url, param, callBack, method){
	createRequest(); // xhr가져오는 과정 
	
	var httpMethod = 
	(method!='POST' && method!='post')?'GET':'POST'; // post가 아니라면 GET
	
	var httpParam = 
	(param==null || param == '')?null:param; // 비어있으면 null이고, 아니면 param이다. 
	
	var httpURL = url; 
	
	//요청 방식이 get방식이고, 전달할 파라미터 값이 있다면
	//url경로를 제작 해야 한다.(.../test.jsp?ch=123)
	if(httpMethod == 'GET' && httpParam != null)
		httpURL = httpURL+"?"+httpParam;
	
	xhr.open(httpMethod, httpURL, true);
	xhr.setRequestHeader("Content-Type",
	  "application/x-www-form-urlencoded"); // 요청헤더에 인코딩 타입 전달 
	xhr.onreadystatechange = callBack;
	
	xhr.send(httpMethod == 'POST'?httpParam:null); // POST인가 아니면 null이다. 3번과정 
}













