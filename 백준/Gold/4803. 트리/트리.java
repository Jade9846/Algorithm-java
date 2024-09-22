import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;
	static int m,n;
	
	public static int find(int a) {
		if (a!= parent[a]) {
			parent[a] = find(parent[a]);
		}
		return parent[a];
	}
	
	public   static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        // 사이클이 발생한 경우 트리에서 제거
        if (rootA == rootB) {
            parent[rootA] = 0;
        } else if (rootA > rootB) {
            parent[rootA] = rootB;
        } else {
            parent[rootB] = rootA;
        }
    }
    // 트리 개수를 확인하고 출력하는 함수
    static void findTree(int caseNum, int node) {
        boolean[] isTree = new boolean[node + 1];
        int treeCount = 0;

        for (int i = 1; i <= node; i++) {
            int root = find(i);
            if (root != 0 && !isTree[root]) {
                isTree[root] = true;
                treeCount++;
            }
        }

        if (treeCount == 0) {
            System.out.printf("Case %d: No trees.\n", caseNum);
        } else if (treeCount == 1) {
            System.out.printf("Case %d: There is one tree.\n", caseNum);
        } else {
            System.out.printf("Case %d: A forest of %d trees.\n", caseNum, treeCount);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caseNum = 1;

        while (true) {
            int node = sc.nextInt();
            int edge = sc.nextInt();

            if (node == 0 && edge == 0) {
                break;
            }

            parent = new int[node + 1];
            for (int i = 1; i <= node; i++) {
                parent[i] = i; // 각 노드를 자신의 부모로 초기화
            }

            for (int i = 0; i < edge; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                union(a, b);
            }

            findTree(caseNum, node);
            caseNum++;
        }

        sc.close();
    }

}
