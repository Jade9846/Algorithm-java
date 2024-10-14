import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[] dp_up;
    private static int[] dp_down;
    private static int[] numList;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp_up = new int[n];
        dp_down = new int[n];
        numList = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());;
        for(int i =  0 ; i < n; i++){
            numList[i] = Integer.parseInt(st.nextToken());
            dp_up[i] = 1;
            dp_down[i] = 1;
        }

//        int[] reverseArray = reverse(numList);

        for(int i = 1 ; i < n; i++){
            for(int j = 0; j <i; j++){
                if(numList[i] > numList[j]){
                    dp_up[i] = Math.max(dp_up[i], dp_up[j]+1);
                }
//                if(reverseArray[i] > reverseArray[j]){
//                    dp_down[i] = Math.max(dp_down[i], dp_down[j]+1);
//                }
            }
        }

        for(int i = n-2 ; i >= 0; i--){
            for(int j = i+1; j <n; j++){
                if(numList[i] > numList[j]){
                    dp_down[i] = Math.max(dp_down[i], dp_down[j]+1);
                }
            }
        }

//        dp_down = reverse(dp_down);

        int result = 0;
        for(int i = 0; i <n ; i++){
            result = Math.max(result, dp_up[i] + dp_down[i]);
        }
        br.close();
        System.out.println(result-1);

    }
//    public static int[] reverse(int[] array){
//        int[] result = new int[array.length];
//        for(int i= 0; i < array.length; i++){
//            result[i] = array[array.length - 1 - i];
//        }
//        return result;
//    }
}
