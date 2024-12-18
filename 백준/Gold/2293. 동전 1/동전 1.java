import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] coin;
    static int[] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        coin = new int[n+1];
        dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coin);

        // 동전의 종류마다 갱신
        for(int i = 1; i <= n ; i++){
            for(int j = coin[i] ; j <= k ; j++){
                dp[j] += dp[j-coin[i]];
            }
        }

        System.out.println(dp[k]);
    }
}