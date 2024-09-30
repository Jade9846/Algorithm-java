import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] solution ;
    static int sum = Integer.MAX_VALUE;
    static int[] result = new int[2];

    public static void search(int start, int end){
        if(start >= end){
            return;
        }

        int temp = solution[start] + solution[end];
        if(Math.abs(temp) < sum){
            sum = Math.abs(temp);
            result[0] = solution[start];
            result[1] = solution[end];
        }
        if(temp == 0){
            return;
        }
        if(temp < 0){
            search(start+1, end);
        }else{
            search(start, end-1);
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        solution = new int[N];
        for(int i = 0 ; i < N ; i++){
            solution[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(solution);

        search(0, N-1);

        System.out.println(result[0] + " " + result[1]);
    }
}
