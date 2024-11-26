import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N; // 추의 개수
    static int[] weight; // 추의 무게
    static boolean[][] memo; // 메모이제이션 배열
    static int M; // 구슬들의 개수

    public static void sol(int cnt, int result){

        if(memo[cnt][result]) return;
        memo[cnt][result] = true;
        if(cnt == N) return;
        sol(cnt + 1, result);
        sol(cnt + 1, result+weight[cnt]);
        sol(cnt + 1, Math.abs(result-weight[cnt]));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weight = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 추의 무게 입력 받기
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }


        M = Integer.parseInt(br.readLine());

        // 메모이제이션 배열 초기화
        memo = new boolean[N+1][40001];
        sol(0,0);
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 각 구슬에 대해 답을 구하기
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (memo[N][num]) sb.append("Y").append(" ");
            else sb.append("N").append(" ");
        }

        // 마지막 공백 제거 후 출력
        System.out.println(sb.toString().trim());
    }
}
