import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String str = "";
//		str = br.readLine();
//		
//		str = str.trim();
//		
//		//System.out.println(str);
//		
//		String[] arr = str.split(" ");
//		
//		System.out.println(arr.length);		
		
 
//		// st 에 공백을 기준으로 나눈 토큰들을 st 에 저장한다
//		StringTokenizer st = new StringTokenizer(str," ");
//		
//		// countTokens() 는 토큰의 개수를 반환한다
//		System.out.println(st.countTokens());	
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		Integer num[] = new Integer[str.length()];
		
		for(int i = 0 ; i < str.length(); i++) {
			num[i] = Character.getNumericValue(str.charAt(i));
			//System.out.print(num[i]);
		}
		
		Arrays.sort(num, Collections.reverseOrder());
		for(int i = 0 ; i < str.length(); i++) {
			System.out.print(num[i]);
		}
		
	}
}