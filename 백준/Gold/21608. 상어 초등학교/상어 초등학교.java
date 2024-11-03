import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, students; // N, 학생수(N^2)
    static int[][] likes, position; // 좋아하는 학생, 학생의 자리
    static int[] dx = new int[]{-1,1,0,0};
    static int[] dy = new int[]{0,0,-1,1};

    // 좋아하는 학생이 인접한 개수에 따른 만족도 점수를 반환
    public static int calculateSatisfactionScore(int likeCount) {
        if (likeCount == 1) return 1;
        else if (likeCount == 2) return 10;
        else if (likeCount == 3) return 100;
        else if (likeCount == 4) return 1000;
        return 0;
    }

    public static boolean isAdjacent(int r1, int c1, int r2, int c2) {
        return Math.abs(r1-r2)+ Math.abs(c1-c2) == 1 ? true :false;
    }

    // 좋아하는 학생이 인접한 개수
    public static int cntAdjacent(int x, int y, int turn){
        int cnt = 0;
        for(int i = 0 ; i < 4 ; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            for(int j = 1 ; j < 5; j++){
                cnt = position[nx][ny] == likes[turn][j]  ? ++cnt: cnt;
            }
        }
        return cnt;
    }


    // 빈 칸의 개수
    public static int cntEmpty(int x, int y) {
        int cnt = 0;
        for(int i = 0 ; i < 4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx < 0 || nx >= N || ny < 0 || ny >= N ) continue;
            cnt = position[nx][ny] == -1 ? ++cnt: cnt;
        }
        return cnt;
    }

    public static void arrangeSeat() {
        for(int i = 0 ; i < students ; i++){
            int bestX = -1, bestY = -1, maxLikeAdj = -1, maxEmptyAdj = -1;

            for(int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (position[x][y] != -1) continue; //빈 칸 아닐 때 건너뛰기

                    int likeAdj = cntAdjacent(x, y, i); // 좋아하는 학생 인접 수
                    int emptyAdj = cntEmpty(x, y); // 빈칸 인접 수

                    // 더 많은 좋아하는 학생이 인접한 칸으로
                    if (likeAdj > maxLikeAdj ||
                            (likeAdj == maxLikeAdj && emptyAdj > maxEmptyAdj) ||
                            (likeAdj == maxLikeAdj && emptyAdj == maxEmptyAdj && (bestX == -1 || x < bestX || (x == bestX && y < bestY)))) {
                        bestX = x;
                        bestY = y;
                        maxLikeAdj = likeAdj;
                        maxEmptyAdj = emptyAdj;
                    }
                }
            }
            // 선택된 위치에 학생 배치
            position[bestX][bestY] = likes[i][0];
//            total += calculateSatisfactionScore(maxLikeAdj); // 인접한 좋아하는 학생 수에 따른 만족도 점수 추가 (x) -> 나중에 숫자 채워질때 만족도 더해질 수 있음

        }

    }

    public static int calculateSatisfaction() {
        int total = 0;

        for(int i = 0; i < students; i++) { // 학생 수 만큼 반복
            int student = likes[i][0]; // 현재 학생
            int x = -1, y = -1;

            // 현재 학생이 앉은 위치 찾기
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (position[row][col] == student) {
                        x = row;
                        y = col;
                        break;
                    }
                }
                if (x != -1) break; // 학생의 자리를 찾으면 루프 종료
            }

            // 해당 학생의 인접한 학생 수 계산
            int likeCount = cntAdjacent(x, y, i); // 현재 학생의 인접한 좋아하는 학생 수
            total += calculateSatisfactionScore(likeCount); // 점수 계산하여 총점에 추가
        }

        return total;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        students = (int) Math.pow(N,2); // 학생수

        likes = new int[students][5]; // [학생 고유번호][좋아하는 학생 번호]
        position = new int[N][N];

        for(int i = 0; i < N; i++) {
            Arrays.fill(position[i], -1);
        }

        StringTokenizer st;
        for(int i = 0 ; i < students ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++) {
                likes[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        arrangeSeat();

        System.out.println(calculateSatisfaction());


    }
}
