import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int T,N;
    static int[] parent;
    static int[] visited;
    static Map<String, Integer> nameMap;

    public static int find(int child) {
        if (child != parent[child]) {
            parent[child] = find(parent[child]); // 경로 압축
        }
        return parent[child];
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return;
        }

        // b의 부모를 a로 설정하고, a의 집합 크기를 갱신한다.
        parent[b] = a;
        visited[a] += visited[b];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        nameMap=new HashMap<>();
        T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            N = Integer.parseInt(br.readLine());

            // 예상 배열 크기 설정: 최대 N*2 크기로 설정 가능
            parent = new int[N * 2]; // 배열의 크기를 친구 관계 수의 2배로 설정
            visited = new int[N * 2]; // visited 배열도 동일한 크기로 설정

            for (int j = 0; j < N * 2; j++) {
                parent[j] = j;
                visited[j] = 1; // 각 집합의 크기를 1로 초기화
            }

            int nameIdx = 0;
            for(int j=0;j<N;j++){
                st = new StringTokenizer(br.readLine());
                String name1= st.nextToken();
                String name2= st.nextToken();
                if(!nameMap.containsKey(name1)){
                    nameMap.put(name1,nameIdx++);
                }
                if(!nameMap.containsKey(name2)){
                    nameMap.put(name2,nameIdx++);
                }

                union(nameMap.get(name1), nameMap.get(name2));

                // 합쳐진 네트워크의 크기를 출력
                System.out.println(visited[find(nameMap.get(name1))]);
            }

        }
    }
}
