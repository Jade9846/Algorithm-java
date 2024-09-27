import java.util.Scanner;

public class Main {
    static int n, m;
    static int[] ref;
    static int find(int node) {
        if (node != ref[node]) {
            ref[node] = find(ref[node]);
        }
        return ref[node];
    }
    
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) {
            ref[a] = b;
        } else if (a < b) {
            ref[b] = a;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        n = scanner.nextInt(); 
        m = scanner.nextInt(); 


        ref = new int[n + 1];
        int[][] connect = new int[n + 1][n + 1];


        for (int i = 1; i <= n; i++) {
            ref[i] = i;
            for (int j = 1; j <= n; j++) {
                connect[i][j] = scanner.nextInt();
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (connect[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        int[] path = new int[m];
        for (int i = 0; i < m; i++) {
            path[i] = scanner.nextInt();
        }

       
        int start = find(path[0]);
        for (int i = 1; i < m; i++) {
            if (start != find(path[i])) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }
}