import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static PriorityQueue<Integer> cards;
    static int total = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cards =  new PriorityQueue<>();
        for(int i = 0 ; i < N ; i++){
            cards.add(Integer.parseInt(br.readLine()));
        }

        // 카드 묶음이 1개 이상일 때까지 반복
        while (cards.size() > 1) {

            int first = cards.poll();
            int second = cards.poll();

            // 두 묶음을 합친 후, 다시 리스트에 추가
            int merged = first + second;
            total += merged;
            cards.add(merged);
        }

    // 결과 출력
        System.out.println(total);
    }
}
