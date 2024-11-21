import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int G; // 게이트 수
    static int P; // 비행기 수
    static int[] parent; // 각 게이트의 루트 노드
    
    public static int find(int a) {
        // 현재 게이트의 루트 게이트 찾기
        if (a != parent[a]) {
            parent[a] = find(parent[a]); // 경로 압축
        }
        return parent[a];
    }

    public static void union(int a, int b) {
        // 두 게이트를 합치기 (더 작은 번호가 부모)
        a = find(a);
        b = find(b);
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        parent = new int[G + 1];
        for (int i = 1; i <= G; i++) {
            parent[i] = i; // 초기화: 각 게이트가 자기 자신을 부모로 가짐
        }

        int result = 0;
        for (int i = 0; i < P; i++) {
            int gate = Integer.parseInt(br.readLine());
            int root = find(gate); // 해당 게이트의 루트를 찾음
            
            if (root == 0) {
                // 루트가 0이면 도킹 불가 (더 이상 도킹할 게이트가 없음)
                break;
            }
            
            // 비행기를 도킹하고, 해당 게이트를 이전 게이트와 합침
            union(root, root - 1);
            result++;
        }

        System.out.println(result); // 최대 도킹 가능한 비행기 수 출력
    }
}
