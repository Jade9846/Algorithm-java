import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, result; // 컴퓨터 수, 연결할 수 있는 선의 수, 가중치합(결과)
    static int[][] edges;
    static int[] parent;

    public static int find(int a){
        if(parent[a]!= a){
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }

    public static void union(int a, int b){
      if(a < b){
          parent[b] = a;
      }else{
          parent[a] = b;
      }
    }
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        edges = new int[M][3];
        parent = new int[N+1];

        for(int i=0;i<N+1;i++){
            parent[i] = i;
        }
        StringTokenizer st;
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0]= Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        //가중치 기준(edges[i][2]) 를 기준으로 오름차순 정렬
        Arrays.sort(edges, (o1,o2) -> o1[2]-o2[2]);

        int cnt = 0;
        for(int i = 0; i < M ; i++){
            if(cnt > N) continue;
            int u = find(edges[i][0]);
            int v = find(edges[i][1]);
            if(u != v){ // 사이클 없을 때만 union
                union(u, v);
                result+=edges[i][2];
                cnt++;
            }
        }

        System.out.println(result);
    }
}
