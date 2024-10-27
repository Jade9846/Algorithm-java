import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] visited;
    static ArrayList<ArrayList<int[] >> tree = new ArrayList<>();

    public static void dfs(int now){
        for(int[] nextNode : tree.get(now)){
            if(visited[nextNode[0]] == -1){
                visited[nextNode[0]] = visited[now] + nextNode[1];
                dfs(nextNode[0]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N  = Integer.parseInt(br.readLine());
        visited = new int[N+1];
        Arrays.fill(visited, -1);
        for(int i = 0 ; i < N+1 ; i++){
            tree.add(new ArrayList<>());
        }
        StringTokenizer st;
        for(int i = 0 ; i < N-1 ;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            tree.get(a).add(new int[]{b,w});
            tree.get(b).add(new int[]{a,w});
        }

        visited[1] = 0;
        dfs(1);
//        for(int i = 1 ; i < N+1 ; i++){
//            System.out.print(visited[i] + " ");
//        }
//        System.out.println();


        int maxWeight = 0;
        int idx = 0;
        for(int i = 1; i < N+1; i++){
            if(maxWeight < visited[i]){
                maxWeight = visited[i];
                idx = i;
            }
        }

        Arrays.fill(visited, -1);
        visited[idx] = 0;
        dfs(idx);
//        for(int i = 1 ; i < N+1 ; i++){
//                System.out.print(visited[i] + " ");
//        }

        for(int i = 1; i < N+1 ; i++){
            maxWeight = Math.max(maxWeight, visited[i]);
        }
       System.out.println(maxWeight);
    }


}
