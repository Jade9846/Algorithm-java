import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    private static int N; // 학생수
    private static int M; // 키 비교 횟수
    private static ArrayList<ArrayList<Integer>> student;
    private static int[] inDegree ;
    private static ArrayList<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = new ArrayList<>();
        inDegree = new int[N+1];
        student = new ArrayList<>();
        for(int i = 0 ; i < N+1; i++){
            student.add(new ArrayList<>());
        }
        for(int i = 0; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            student.get(start).add(end);
            inDegree[end]++;
        }

        while(result.size() <N){
            for(int i= 1 ; i < N+1 ; i++){
                if(inDegree[i] == 0){
                    result.add(i);
                    inDegree[i] = -1; // 이미 처리된 노드로 표시

                    for(int end : student.get(i)){
                        inDegree[end]--;
                    }
                }
            }
        }

        br.close();
        System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));

    }
}
