import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, C;
	static int[] house;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		house = new int[N];
		for(int i = 0 ; i < N ; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house);

		// 최대 길이 찾기 
		int start = 0;
		int end = house[N-1] - house[0] ;
		while(start <= end) {
			int cnt = 1; // house 집
			int now = house[0]; // 첫 번째 집부터 시작
			int mid = (start+end)/2;
			
			for(int i = 1; i < N; i++) {
				if(house[i] - now >= mid ) {
					cnt++;
					now = house[i] ; // 현재 보는 인덱스를 설치한 집으로 이동
				}
			}
			
			if(cnt >= C) { // 공유기간 거리 최소 0개 이상
				start = mid +1;
			}else {
				end = mid -1;
			}
		}
		System.out.println(end);
	}
}
