import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static long  N ;
	static long[] lan;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		K = Integer.parseInt(st.nextToken());
		N = Long.parseLong(st.nextToken());
		lan = new long [ K];
		
		for(int i = 0 ; i < K; i ++) {
			lan[i] = Long.parseLong(br.readLine());
		}
		
		Arrays.sort(lan);


		long start = 1;
		long end = lan[lan.length - 1];
		while(start <=end) {
			int cnt = 0;
			long mid = (start+end)/2;
			for(long l : lan) {
				cnt += l/mid;
			}
			if(cnt >= N) {
				start = mid +1;
			}else {
				end = mid-1;
			}
		}
		
		System.out.println(end);
		
		
		
	}

}
