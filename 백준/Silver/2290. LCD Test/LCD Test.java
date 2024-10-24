import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int s; // 크기
    private static String n; // 출력할 숫자
    private static char[][] matrix; // LCD 모양을 저장할 2차원 배열

    // 상단, 중단, 하단의 가로줄 표시
    private static void drawHorizontal(int row, int colStart) {
        for (int i = 1; i <= s; i++) {
            matrix[row][colStart + i] = '-';
        }
    }

    // 세로줄 표시 (왼쪽 또는 오른쪽)
    private static void drawVertical(int rowStart, int col) {
        for (int i = 1; i <= s; i++) {
            matrix[rowStart + i][col] = '|';
        }
    }

    // 특정 숫자의 LCD 모양을 그리기
    private static void drawNumber(char num, int startCol) {
        switch (num) {
            case '0':
                drawHorizontal(0, startCol); // 상단
                drawVertical(0, startCol); // 왼쪽 상단
                drawVertical(0, startCol + s + 1); // 오른쪽 상단
                drawVertical(s + 1, startCol); // 왼쪽 하단
                drawVertical(s + 1, startCol + s + 1); // 오른쪽 하단
                drawHorizontal(2 * s + 2, startCol); // 하단
                break;
            case '1':
                drawVertical(0, startCol + s + 1); // 오른쪽 상단
                drawVertical(s + 1, startCol + s + 1); // 오른쪽 하단
                break;
            case '2':
                drawHorizontal(0, startCol); // 상단
                drawVertical(0, startCol + s + 1); // 오른쪽 상단
                drawHorizontal(s + 1, startCol); // 중단
                drawVertical(s + 1, startCol); // 왼쪽 하단
                drawHorizontal(2 * s + 2, startCol); // 하단
                break;
            case '3':
                drawHorizontal(0, startCol); // 상단
                drawVertical(0, startCol + s + 1); // 오른쪽 상단
                drawHorizontal(s + 1, startCol); // 중단
                drawVertical(s + 1, startCol + s + 1); // 오른쪽 하단
                drawHorizontal(2 * s + 2, startCol); // 하단
                break;
            // 나머지 숫자도 비슷한 방식으로 추가
            case '4':
                drawVertical(0, startCol); // 왼쪽 상단
                drawVertical(0, startCol + s + 1); // 오른쪽 상단
                drawHorizontal(s + 1, startCol); // 중단
                drawVertical(s + 1, startCol + s + 1); // 오른쪽 하단
                break;
            case '5':
                drawHorizontal(0, startCol); // 상단
                drawVertical(0, startCol); // 왼쪽 상단
                drawHorizontal(s + 1, startCol); // 중단
                drawVertical(s + 1, startCol + s + 1); // 오른쪽 하단
                drawHorizontal(2 * s + 2, startCol); // 하단
                break;
            case '6':
                drawHorizontal(0, startCol); // 상단
                drawVertical(0, startCol); // 왼쪽 상단
                drawHorizontal(s + 1, startCol); // 중단
                drawVertical(s + 1, startCol); // 왼쪽 하단
                drawVertical(s + 1, startCol + s + 1); // 오른쪽 하단
                drawHorizontal(2 * s + 2, startCol); // 하단
                break;
            case '7':
                drawHorizontal(0, startCol); // 상단
                drawVertical(0, startCol + s + 1); // 오른쪽 상단
                drawVertical(s + 1, startCol + s + 1); // 오른쪽 하단
                break;
            case '8':
                drawHorizontal(0, startCol); // 상단
                drawVertical(0, startCol); // 왼쪽 상단
                drawVertical(0, startCol + s + 1); // 오른쪽 상단
                drawHorizontal(s + 1, startCol); // 중단
                drawVertical(s + 1, startCol); // 왼쪽 하단
                drawVertical(s + 1, startCol + s + 1); // 오른쪽 하단
                drawHorizontal(2 * s + 2, startCol); // 하단
                break;
            case '9':
                drawHorizontal(0, startCol); // 상단
                drawVertical(0, startCol); // 왼쪽 상단
                drawVertical(0, startCol + s + 1); // 오른쪽 상단
                drawHorizontal(s + 1, startCol); // 중단
                drawVertical(s + 1, startCol + s + 1); // 오른쪽 하단
                drawHorizontal(2 * s + 2, startCol); // 하단
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()); // 숫자 크기
        n = st.nextToken(); // 출력할 숫자

        int height = 2 * s + 3; // 전체 높이
        int width = (s + 2) * n.length() + (n.length() - 1); // 전체 너비
        matrix = new char[height][width];

        // 배열 초기화 (공백으로 채우기)
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = ' ';
            }
        }

        // 각 숫자를 그리기
        for (int i = 0; i < n.length(); i++) {
            drawNumber(n.charAt(i), i * (s + 3));
        }

        // 결과 출력
        for (char[] row : matrix) {
            System.out.println(new String(row));
        }
    }
}
