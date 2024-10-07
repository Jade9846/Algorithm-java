import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] coin ;
    static int[] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        coin = new int[n];
        dp = new int[m+1];
        for(int i = 0 ; i < n ; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }
        // dp 배열 초기화
        Arrays.fill(dp, Integer.MAX_VALUE-1);
        dp[0] = 0;

        for(int i =0; i <n ; i++){
            for(int j = coin[i] ; j <= m ; j++){
                dp[j] = Math.min(dp[j], dp[j- coin[i]]+1);
            }
        }

        System.out.println(dp[m] == Integer.MAX_VALUE-1 ? -1 : dp[m]);

    }
}