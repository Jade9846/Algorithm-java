import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] origin;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		origin = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken()) % 1000;
			}
		}
		
		int[][] result = pow(origin, B);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(result[i][j]+" ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	static int[][] pow(int [][] A, long exponential) {
		if(exponential == 1L) {
			return A;
		}
		
		int[][] ret = pow(A, exponential/2);
		
		ret = multiply(ret, ret);
		
		if(exponential % 2 == 1L) {
			ret = multiply(ret, origin);
		}
		return ret;
	}
	static int[][] multiply(int[][] o1, int[][] o2) {
		int[][] ret= new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					ret[i][j] += o1[i][k] * o2[k][j];
					ret[i][j] %= 1000;
				}
			}
		}
		return ret;
	}
}