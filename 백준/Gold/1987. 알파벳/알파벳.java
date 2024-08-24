import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static int R, C;
    static char[][] board;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int maxLen = 0;

    public static void dfs(int x, int y, int len, Set<Character> visited) {
        // 현재 위치의 길이가 최대 길이보다 크면 갱신
        maxLen = Math.max(maxLen, len);

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            // 다음 위치가 보드 내에 있고, 아직 방문하지 않은 알파벳일 때
            if (nextX >= 0 && nextX < R && nextY >= 0 && nextY < C) {
                if (!visited.contains(board[nextX][nextY])) {
                    // 다음 위치의 알파벳을 방문 목록에 추가
                    visited.add(board[nextX][nextY]);
                    // 다음 위치로 DFS 재귀 호출
                    dfs(nextX, nextY, len + 1, visited);
                    // 재귀 호출이 끝나면 현재 위치의 알파벳을 방문 목록에서 제거
                    visited.remove(board[nextX][nextY]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        // 처음 위치의 알파벳을 방문 목록에 추가
        Set<Character> visited = new HashSet<>();
        visited.add(board[0][0]);

        // DFS 시작
        dfs(0, 0, 1, visited);

        // 최종 최대 길이 출력
        System.out.println(maxLen);
    }
}
