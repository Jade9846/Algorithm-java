import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M;
    static int[][] map ;
    static int[][][] check;
    static int wall = 0;
    static Queue<Point> q = new LinkedList<>();

    // 상하좌우로 움직일 때
    public static void bfs(){
        while(! q.isEmpty()){
            Point p = q.poll();
            int x = p.x;
            int y = p.y;
            int wall = p.wall;
            if(x == N-1 && y == M-1){
                System.out.println(check[x][y][wall]);
                return;
            }

            for(int i= 0 ; i < 4 ; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                // 벽을 깨는 상황 ( 앞에서 벽 깬 적 없음)
                if(wall == 0 && check[nx][ny][wall]==0 && map[nx][ny] ==1){
                    q.add(new Point(nx, ny, 1));
                    check[nx][ny][1] = check[x][y][0] + 1;
                }
                // 벽을 안깨는 상황
                if(map[nx][ny] == 0 && check[nx][ny][wall] == 0){
                    q.add(new Point(nx, ny, wall));
                    check[nx][ny][wall] = check[x][y][wall] + 1;
                }
            }
        }
        System.out.println(-1);
    }

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        check = new int[N][M][2];

        for(int i = 0 ; i < N ; i++ ){
            String[] numberInput = br.readLine().split("");
            for(int j = 0;j < M; j++){
                map[i][j] = Integer.parseInt(numberInput[j]);
            }
        }
        check[0][0][0] = 1;
        q.add(new Point(0, 0 , wall));
        bfs();


    }
    public static class Point{
        int x, y, wall;
        public Point(int x, int y, int wall){
            this.x = x;
            this.y = y;
            this.wall = wall;
        }
    }
}
