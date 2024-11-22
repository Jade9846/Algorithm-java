import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] position, inorder, postorder;

    static void printPreorder(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) return;

        int root = postorder[postEnd]; // 루트의 값
        int rootIndex = position[root]; // 루트의 중위 순회 인덱스
        int left = rootIndex - inStart; // 왼쪽 트리 노드 개수
        int right = inEnd - rootIndex; // 오른쪽 트리 노드 개수

        // 루트 출력
        System.out.print(root + " ");
        // 왼쪽 서브트리 순회
        printPreorder(inStart, rootIndex - 1, postStart, postStart + left - 1);
        // 오른쪽 서브트리 순회
        printPreorder(rootIndex + 1, inEnd, postEnd - right, postEnd - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        position = new int[N + 1];
        inorder = new int[N + 1];
        postorder = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            position[inorder[i]] = i; // 중위 순회 값의 위치 저장
        }

        // 전위 순회 출력
        printPreorder(1, N, 1, N);
    }
}
