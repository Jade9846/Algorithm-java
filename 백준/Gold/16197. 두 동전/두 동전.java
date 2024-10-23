import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static char[][] graph;
    private static boolean[][][][] visited;  // 방문 여부 배열

    public static int bfs(int x1, int y1, int x2, int y2) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][M][N][M];

        q.add(new int[]{x1, y1, x2, y2, 0});
        visited[x1][y1][x2][y2] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int cnt = now[4];
            if (cnt >= 10) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                int cx = now[2] + dx[i];
                int cy = now[3] + dy[i];

                if (!(0 <= nx && nx < N && 0 <= ny && ny < M) && !(0 <= cx && cx < N && 0 <= cy && cy < M)) {
                    continue;
                } else if (!(0 <= nx && nx < N && 0 <= ny && ny < M) && (0 <= cx && cx < N && 0 <= cy && cy < M)) {
                    return cnt + 1;
                } else if ((0 <= nx && nx < N && 0 <= ny && ny < M) && !(0 <= cx && cx < N && 0 <= cy && cy < M)) {
                    return cnt + 1;
                } else {
                    if (graph[nx][ny] == '#') {
                        nx = now[0];
                        ny = now[1];
                    }
                    if (graph[cx][cy] == '#') {
                        cx = now[2];
                        cy = now[3];
                    }
                    if (!visited[nx][ny][cx][cy]) {
                        visited[nx][ny][cx][cy] = true;
                        q.add(new int[]{nx, ny, cx, cy ,cnt+1});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new char[N][M];
        ArrayList<Integer> startPoint = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                graph[i][j] = line.charAt(j);
                if (graph[i][j] == 'o') {
                    startPoint.add(i);
                    startPoint.add(j);
                }
            }
        }

        System.out.println(bfs(startPoint.get(0), startPoint.get(1), startPoint.get(2), startPoint.get(3)));
    }
}

