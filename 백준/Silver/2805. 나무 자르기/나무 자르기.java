import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, result;
	static int[] height;
	static void search(int start, int end) {
		if (start > end) {
			return;
	    }
		long sum = 0;
		int mid = (start + end) /2;
		for(int i = 0; i < height.length ; i++) {
			if(height[i] > mid) {
				sum += (height[i] -mid);
			}
		}
		if(sum >= M ) {
			 result = mid; // 필요한 나무를 충족할 수 있는 경우에만 result를 업데이트
			search(mid+1 , end);
		}else {
			search(start, mid-1);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		height = new int [N];
		int maxVal = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine(),  " ");
		for(int i = 0 ; i < N; i ++) {
			height[i] = Integer.parseInt(st.nextToken());
			if(maxVal < height[i]) {
				maxVal = height[i];
			}
		}
		search(0, maxVal );
		System.out.println(result);
		
		
	}

}
