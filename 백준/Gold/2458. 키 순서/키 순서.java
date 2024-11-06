import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, result;
    static boolean[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new boolean[N+1][N+1];

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = true;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] |= (dist[i][k] && dist[k][j]);
                }
            }
        }
        result = 0;
        for(int i = 1 ;i< N +1 ; i++){
            int cnt = 0;
            for(int j = 1; j < N+1 ; j++){
                if(dist[i][j] ||dist[j][i] ){
                    cnt++;
                }
            }
            if(cnt == N-1){
                result++;
            }
        }
        System.out.println(result);


    }

}
