import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int result = Integer.MIN_VALUE;
    private static boolean[] visited;
    private static ArrayList<ArrayList<int[] >>tree;
    private static int farthestNode;

    public static void dfs(int now, int dist){
        if(dist > result){
            result = dist;
            farthestNode = now;
        }
        visited[now] = true;
        for( int i = 0; i < tree.get(now).size(); i++){
            int nextNode = tree.get(now).get(i)[0];
            if(!visited[nextNode]){
                dfs(nextNode, dist+tree.get(now).get(i)[1]);
                visited[nextNode] = true;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int v = Integer.parseInt(br.readLine());
        visited = new boolean[v+1];
        tree = new ArrayList<>();

        for(int i = 0 ; i <= v; i++){
            tree.add(new ArrayList<>());
        }

        for(int i = 1 ; i <= v; i ++){
            st = new StringTokenizer(br.readLine(), " ");
            int n1 = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()){
                int n2 = Integer.parseInt(st.nextToken());
                if(n2 == -1){
                    break;
                }
                int w = Integer.parseInt(st.nextToken());
                tree.get(n1).add(new int[]{n2, w});
            }
        }

        //1번 노드에서 가장 먼 노드 찾음
        dfs(1, 0);

        visited = new boolean[v+1];

        // 앞에서 찾은 가장 먼 노드에서 다시 제일 먼 노드 찾음
        dfs(farthestNode, 0);

        System.out.println(result);
    }

}
