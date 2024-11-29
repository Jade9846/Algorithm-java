import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static int n; // 대나무 숲 크기(1 <= n <= 500)
    static int[][] map; // 대나무 숲
    static int [][] visited;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    public static int dfs(int x, int y){
        if(visited[x][y] != 0) return visited[x][y];

        visited[x][y] = 1;
        for(int i = 0 ; i < 4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny <0 || nx >= n || ny >= n) continue;
            if( map[nx][ny] > map[x][y]){
                visited[x][y] = Math.max( visited[x][y], dfs(nx, ny) + 1);
            }
        }
        return visited[x][y];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new int[n][n];

        StringTokenizer st;
        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(visited[i], 0);
            for(int j = 0 ; j < n ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        for(int i= 0 ; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = dfs(i, j);
                if (result < temp) result = temp;
            }
        }
        System.out.println(result);
    }
}
