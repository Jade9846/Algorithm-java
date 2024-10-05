import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static int N;
    static char[][] stars;

    public static void rec(int x , int y, int size ){
        if(size==1){
            stars[x][y]='*';
            return;
        }
        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3 ; j++){
                if(!(i== 1 && j==1)){
                   rec(x+i*(size/3) , y+j*(size/3), size/3);
                }

            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        stars = new char[N][N];
        // 배열을 공백 문자로 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                stars[i][j] = ' ';
            }
        }
        rec(0,0, N);

        for(int i = 0 ; i < N ; i++){
            for(int j = 0; j < N ; j++){
                sb.append(stars[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}
