import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] paper;
    static int white = 0;
    static int blue = 0 ;

    public static void rec(int x, int y, int size){
        int color = paper[x][y];
        boolean sameColor = true;

        // 현재 영역의 모든 칸이 같은 색인지 확인
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (paper[i][j] != color) {
                    sameColor = false;
                    break;
                }
            }
            if (!sameColor) break;
        }

        // 모든 칸이 동일한 색일 경우
        if (sameColor) {
            if (color == 0) {
                white++;
            } else {
                blue++;
            }
            return;
        }

        // 색이 같지 않은 경우 4개의 영역으로 나눔
        int nextSize = size / 2;
        rec(x, y, nextSize);
        rec(x, y + nextSize, nextSize);
        rec(x + nextSize, y, nextSize);
        rec(x + nextSize, y + nextSize, nextSize);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        StringTokenizer st ;
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        rec(0, 0, N);
        System.out.println(white);
        System.out.println(blue);

    }
}