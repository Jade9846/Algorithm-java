import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class  Main {
    static int N, M, K;
    static long arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int k = (int) Math.ceil(Math.log(N) / Math.log(2) ) ;
        int size = (int) Math.pow(2, k);
        arr = new long[size * 2];
        Arrays.fill(arr, 0);
        for(int i= size ; i < size+N ;i ++){
            arr[i] = Long.parseLong(br.readLine());
        }

        // 트리 배열 초기화
        for(int i = size-1;  i > 0  ; i--){
            arr[i] = arr[2 * i] + arr[2*i+1];
        }


//        System.out.println();
//        for(int i = 0 ; i < arr.length ; i++){
//            System.out.print(arr[i] + " ");
//        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());

            if(a == 1){
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());
                int idx = size + b -1;
                update(size, b, c);

            }else if(a ==2){
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                System.out.println(sum(size, b, c));
            }
        }

        br.close();
    }

 // bottom-up 방식 업데이트
    static void update(int size, int idx, long num) {
        int pos = size + idx - 1; // 리프 노드 위치
        arr[pos] = num; // 값 업데이트

        // 상위 노드로 전파
        while (pos > 1) {
            pos /= 2;
            arr[pos] = arr[pos * 2] + arr[pos * 2 + 1];
        }


    }

    // left, right: 구간 합을 구하고자 하는 범위
    static long sum(int size, int left, int right) {
        int l = size + left - 1; // 시작 위치 (리프 노드 인덱스)
        int r = size + right - 1; // 끝 위치 (리프 노드 인덱스)
        long result = 0;

        while (l <= r) {
            if (l % 2 == 1) { // 왼쪽 경계가 홀수일 때
                result += arr[l];
                l++;
            }
            if (r % 2 == 0) { // 오른쪽 경계가 짝수일 때
                result += arr[r];
                r--;
            }
            l /= 2;
            r /= 2;
        }
        return result;
    }
}
