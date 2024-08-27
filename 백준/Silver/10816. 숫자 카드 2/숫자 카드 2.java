import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] card;
	static int[] answer;


	// target의 처음 인덱스를 찾는다 
	static int searchFirstIdx(int start, int end , int target) {
		while(start < end) { // start 와 end 가 같아질때까지 반복
			int mid = (start+end)/2;
			if(card[mid] >= target) {
				end = mid;  // 타겟보다 크거나 같으면 end를 mid로 줄임
			} else {
				start = mid + 1;
			}
		}
		return start;
	}
	// target보다 큰 첫 번째 인덱스를 찾는다 
	static int searchLastIdx(int start, int end, int target) {
		while(start <end) {
			int mid = (start+end)/2;
			if(card[mid] > target) {
				end = mid;
			}else {
				start = mid+1;
			}
		}
		return start;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		card = new int[N];
		for(int i = 0 ; i < N ; i++) {
			card[i] =Integer.parseInt(st.nextToken());
		}
		Arrays.sort(card);
		M = Integer.parseInt(br.readLine());
		answer = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0 ;  i < M; i++) {
			int target = Integer.parseInt(st.nextToken());
			answer[i] =( searchLastIdx(0, N, target) - searchFirstIdx(0, N, target));
			
		}
		 // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
		
		
	}

}
