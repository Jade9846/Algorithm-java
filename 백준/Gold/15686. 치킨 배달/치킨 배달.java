import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    
    static int N, M;
    static int[][] map;
    static List<Point> chicken;
    static List<Point> house;
    static int answer;
    static boolean[] visited;
    
    public static int calc(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
    
    public static void dfs(int start, int depth) {
        if (depth == M) {
            int dist = 0;
            for (int i = 0; i < house.size(); i++) {
                int temp = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (visited[j]) {
                        int currentDist = calc(house.get(i).x, house.get(i).y, chicken.get(j).x, chicken.get(j).y);
                        temp = Math.min(temp, currentDist);
                    }
                }
                dist += temp;
            }
            answer = Math.min(answer, dist);
            return;
        }
        
        for (int i = start; i < chicken.size(); i++) {
            visited[i] = true;
            dfs(i + 1, depth + 1);
            visited[i] = false;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        chicken = new ArrayList<Point>();
        house = new ArrayList<Point>();
        
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    chicken.add(new Point(i, j));
                } else if (map[i][j] == 1) {
                    house.add(new Point(i, j));
                }
            }
        }
        
        visited = new boolean[chicken.size()];
        answer = Integer.MAX_VALUE;
        dfs(0, 0);
        System.out.println(answer);
    }
}

class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
