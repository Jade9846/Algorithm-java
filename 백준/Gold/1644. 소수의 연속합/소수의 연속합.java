import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1644 소수의 연속합
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> primeNumbers = new ArrayList<Integer>();
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime,true);
        isPrime[0] = isPrime[1] = false;

        /* 소수 구하기 */
        for(int i =2 ; i * i <= N ; i++){
            if(isPrime[i]){ // 해당수가 소수-> 해당수를 제외한 배수들 false
                for(int j = i*i; j<= N; j += i){
                    isPrime[j] = false;
                }
            }
        }

        for(int i =1; i <= N; i++){
            if(isPrime[i]){
                primeNumbers.add(i);
            }
        }

        /* 연속합으로 주어진 정수 구할수 있는지 판별 */
        int start = 0, end = 0, sum = 0, cnt = 0;
        while(true) {
            if(sum >= N ) sum -= primeNumbers.get(start++);
            else if(end == primeNumbers.size()) break;
            else sum += primeNumbers.get(end++);
            if(N==sum) cnt++;
        }

        System.out.println(cnt);

    }
}
