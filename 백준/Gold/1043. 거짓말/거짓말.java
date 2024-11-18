import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M ;
    static List<Integer> truthList; // 진실을 아는 사람
    static List<Integer>[] partyGroup;
    static boolean[] check;
    static int[] parent;

    public static int find(int a){
        if(a!= parent[a]){
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }
    public static void union(int u, int v){
        u = find(u);
        v = find(v);
        if(u < v){
            parent[v] = u;
        }else{
            parent[u] = v;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        partyGroup = new List[M+1];
        truthList = new ArrayList<>();
        check = new boolean[M+1];
        parent = new int[N+1];

        st = new StringTokenizer(br.readLine());
        int truthListCnt = Integer.parseInt(st.nextToken()); // 진실을 아는 사람 수
        if(truthListCnt == 0){
            System.out.println(M);
            return;
        }
        for(int i = 0 ; i < truthListCnt ; i++){
            truthList.add(Integer.parseInt(st.nextToken()));
        }
        for(int i = 0 ; i < N+1 ; i++){
            parent[i] = i;
        }

        for (int i = 0; i < M+1; i++) {
            partyGroup[i] = new ArrayList<>();
        }

        for(int i = 1; i < M+1; i++){
            st = new StringTokenizer(br.readLine());
            int partyCnt = Integer.parseInt(st.nextToken());
            for(int j = 0 ; j < partyCnt ; j++){
                int u = Integer.parseInt(st.nextToken());
                partyGroup[i].add(u);
            }
        }


        for(int i = 1; i < M+1; i++){
            if (!partyGroup[i].isEmpty()) {
                int firstNode = partyGroup[i].get(0);
                for(int j = 0; j < partyGroup[i].size(); j++) {
                    union(firstNode, partyGroup[i].get(j));
                }
            }
        }
        // 진실을 아는 사람들을 같은 집합으로 묶음
        for (int i = 0; i < truthList.size() - 1; i++) {
            union(truthList.get(i), truthList.get(i + 1));
        }
        // 진실을 아는 사람들의 부모 노드 집합
        Set<Integer> truthParents = new HashSet<>();
        for (int truth : truthList) {
            truthParents.add(find(truth));
        }

        for(int i = 1; i < M+1 ; i++){
            for(int u : partyGroup[i]){
                if (truthParents.contains(find(u))) {
                    check[i] = true;
                    break;
                }
            }
        }

        int cnt = 0;
        for(int i = 1 ; i < M+1 ; i++){
            if(check[i]){
                cnt++;
            }
        }

        System.out.println(M - cnt);
    }
}
