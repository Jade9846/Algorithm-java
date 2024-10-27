import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int k;
    static int[] weight;
    static int result = 0;

    public static int dfs(int idx, int depth){
        if (depth == k) {
            result+= weight[idx];
            return weight[idx];
        }
        int left = dfs(2 * idx + 1, depth + 1);
        int right = dfs(2 * idx + 2, depth + 1);

        result +=  Math.abs(left - right) + weight[idx];
        return Math.max(left, right) + weight[idx];

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int size = (int) (Math.pow(2, k+1)-2); // 에지의 개수
        weight = new int[size+1];
        weight[0] = 0 ;
        for(int i = 1 ; i <= size ; i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0 );

        System.out.println(result);

    }

}
