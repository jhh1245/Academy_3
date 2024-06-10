
public class Main22 {
	public static String solution(String[] participant, String[] completion) {
        String answer = "";
        
        // 완주 못한 선수는 단 1명 
        // 주의할점 : 동명이인이 있을 수 있다. 
        for(int i = 0; i < participant.length; i++) {
        	for(int k = 0; k < completion.length; k++) {
        		
        		if( participant[i].equals(completion[k])) {
        			completion[k] = " ";
        			break;
        		} else if ((k == completion.length-1) && !(participant[i].equals(completion[k]))){
        			answer = participant[i];
        			System.out.println(answer);
        		} 
        		
        	}
        }
        
        
        
        
        return answer;
   
    }
	
	public static void main(String[] args) {
		String[]participant = {"mislav", "stanko", "mislav", "ana"};
		String[]completion = {"stanko", "ana", "mislav"};
		solution(participant,completion);
	}

}