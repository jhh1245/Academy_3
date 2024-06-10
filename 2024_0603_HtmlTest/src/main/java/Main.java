import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); 
        
		int sum=1, a=0, b=1;
        if(N>= 2 && N <= 90){
            for(int i = 2; i < N; i++) {
                a = b;
                b = sum;
                sum = a + b;	
            }
            System.out.println(sum);
        }
	}
}