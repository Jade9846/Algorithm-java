import java.io.*;
import java.util.*;

public class Main {
    private static int[][] graph;
    private static int INF =Integer.MAX_VALUE /2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph  = new int[n+1][n+1];

        //초기화
        for (int i = 1; i < n+1; i++) {
            Arrays.fill(graph[i], INF); // 각 행을 INF로 채움
        }

        for(int i= 1 ; i < n+1 ; i++){
            graph[i][i] = 0;
        }

        for(int i= 0 ; i < m ;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
//            시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
            graph[start][end] = Math.min(weight,graph[start][end]);
        }


        for(int k = 1 ; k < n+1 ; k++){
            for(int start = 1 ; start < n+1 ; start++){
                for(int end = 1 ; end < n+1 ; end++){
                    graph[start][end] = Math.min(graph[start][end], graph[start][k] + graph[k][end]);
                }
            }
        }

        //만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph[i].length; j++) {
                sb.append(graph[i][j] == INF? 0 : graph[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }
}
