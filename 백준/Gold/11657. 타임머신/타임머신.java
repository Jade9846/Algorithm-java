import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[][] edges;
    private static long [] time;
    private static int INF = Integer.MAX_VALUE/2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 도시 개수
        int m = Integer.parseInt(st.nextToken()); // 버스 노선 개수
        edges = new int[m][3];
        time = new long[n+1];

        Arrays.fill(time, INF );
        time[1] = 0;

        for(int i = 0 ; i < m; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken()); // start
            edges[i][1] = Integer.parseInt(st.nextToken()); // end
            edges[i][2] = Integer.parseInt(st.nextToken()); // weight
        }

        /* 벨만 포드 알고리즘 */
        boolean hasNegativeCycle = false;
        for(int edge = 1 ; edge <= n ; edge++){ // 사용한 에지 개수 (노드 개수만큼 반복)
            for(int i = 0 ; i < m ; i++){
                if(time[edges[i][0]] == INF )  continue;

                if(time[edges[i][1]] > time[edges[i][0]] + edges[i][2]){
                   time[edges[i][1]] = time[edges[i][0]] + edges[i][2];

                   // 사용한 에지 개수 == 노드 개수
                   if(edge == n){
                       hasNegativeCycle = true;
                   }
                }
            }
        }
        // 음수 사이클 존재 -> -1 출력
        if(hasNegativeCycle) System.out.println(-1);
        else{
            for(int i=2 ; i <= n ; i++){ // 1번 제외하고 출력
                if(time[i] == INF){ // 해당 도시로 갈 경로가 없을 때
                    System.out.println(-1);
                }else{
                    System.out.println(time[i]);
                }
            }
        }


    }
}
