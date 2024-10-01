import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
    static int[][] map = new int[9][9];
    static ArrayList <Point> blank = new ArrayList<>();
    static boolean solved = false;

    // 가로 , 세로, 3 x 3 영역 안에 중복된 숫자 있는지 확인
    public static boolean valid(int x, int y, int num){
        for(int i = 0 ; i < 9 ; i++){
            //중복된 숫자 있음
            if(map[i][y] == num || map[x][i] == num || map[(x/3) * 3 + (i /3)][ (y/3)* 3+ (i %3)] == num ){
                return false;
            }
        }
        return true;
    }
    public static void sudoku(int depth){
        if(depth == blank.size()){
            for(int i = 0 ; i < 9 ; i++){
                for(int j = 0 ; j < 9 ; j++){
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            solved = true;
            return;
        }
        if(solved == false){
            Point nowPoint = blank.get(depth);
            int x = nowPoint.x;
            int y = nowPoint.y;
            for(int target =1 ; target <= 9 ; target++){
                if(valid(x, y, target)){
                    map[x][y] = target;
                    sudoku(depth + 1);
                    map[x][y] = 0;
                }
            }
        }


    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0){
                    blank.add(new Point(i, j));
                }
            }
        }
        sudoku(0);
    }
    static class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
