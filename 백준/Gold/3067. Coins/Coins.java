import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N, M ;
    static int[] coins;
    static int[] solution; // dp
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < T ; i++){
            N = Integer.parseInt(br.readLine());
            coins = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                coins[j] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());
            solution = new int[M+1];
            solution[0] = 1;
            for(int k = 0 ; k < N ; k++){
                for(int coin = coins[k]; coin <= M ; coin++ ){
                    solution[coin] += solution[coin-coins[k]];
                }
            }
            System.out.println(solution[M]);
        }

    }
}
