import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static int[][] parentInfo; //각 노드의 부모노드, 깊이 저장
    static List<ArrayList<Integer>>tree;
    static boolean [] visited; // 방문 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < T ; i++){
            int N = Integer.parseInt(br.readLine());
            parentInfo = new int[N+1][2];
            tree = new ArrayList<>();
            for(int j =0 ; j < N+1; j++){
                tree.add(new ArrayList<>());
            }
            visited = new boolean[N+1];
            StringTokenizer st;
            int parent, child;
            for(int j = 0 ; j < N-1 ; j++){
                st = new StringTokenizer(br.readLine());
                parent = Integer.parseInt(st.nextToken());
                child = Integer.parseInt(st.nextToken());
                parentInfo[child][0] = parent; //부모 저장
                tree.get(parent).add(child);
            }
            for(int j = 1 ; j <= N ; j++){
                if(parentInfo[j][0] == 0){
                    dfs(j, 0);
                }
            }
            st = new StringTokenizer(br.readLine());
            System.out.println(search(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    public static void dfs(int start, int depth){
        visited[start] = true;
        for(int child : tree.get(start)){
            if(!visited[child]){
                dfs(child, depth+1);
                parentInfo[child][1] = depth+1;
            }

        }
    }

    public static int search(int q1, int q2){
        while(true){
            if(q1 == q2){
                break;
            }
            if(parentInfo[q1][1] == parentInfo[q2][1]){
                q1 = parentInfo[q1][0];
                q2 = parentInfo[q2][0];
            }else if(parentInfo[q1][1] > parentInfo[q2][1]){
                q1 = parentInfo[q1][0];
            }else{
                q2 = parentInfo[q2][0];
            }
        }
        return q1;
    }
}
