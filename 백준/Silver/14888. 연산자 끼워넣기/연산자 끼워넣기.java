import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] numList;
	static int[] operator = new int[4]; // +, - ,x , /
	static int minSum = Integer.MAX_VALUE;
	static int maxSum = Integer.MIN_VALUE;
	
	public static void dfs(int idx, int sum) {
		if(idx == N) {
			minSum = Math.min(sum, minSum);
			maxSum = Math.max(sum, maxSum);
			return;
		}
		
		for(int i  = 0 ; i < 4 ; i++) {
			if(operator[i] > 0) {
				operator[i] -=1;
				
				switch(i) { // 연산자 
					case 0: dfs(idx+1, sum + numList[idx] ); break; // 덧셈
					case 1: dfs(idx+1, sum - numList[idx] ); break; // 뺄셈
					case 2: dfs(idx+1, sum * numList[idx] ); break; // 곱셈
					case 3: dfs(idx+1, sum / numList[idx] ); break; // 나눗셈
				}
				operator[i] += 1;
			}
			
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		numList = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			numList[i] = Integer.parseInt(st.nextToken());
		}
	
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 4 ; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		dfs(1, numList[0]);
	
		
		System.out.println(maxSum); 
		System.out.println(minSum);
	
	}
}
