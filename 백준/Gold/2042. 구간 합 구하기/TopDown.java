import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TopDown {
    static int N, M, K;
    static long arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int k = (int) Math.ceil(Math.log(N) / Math.log(2) ) ;
        int size = (int) Math.pow(2, k);
        arr = new long[size * 2];
        Arrays.fill(arr, 0);
        for(int i= size ; i < size+N ;i ++){
            arr[i] = Long.parseLong(br.readLine());
        }


        initArray( size, arr.length -1 , 1);

//        System.out.println();
//        for(int i = 0 ; i < arr.length ; i++){
//            System.out.print(arr[i] + " ");
//        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());

            if(a == 1){
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());
                int idx = size + b -1;
                long dif = c - arr[idx]  ;
                update(size, arr.length - 1, 1, idx, dif);

            }else if(a ==2){
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int leftIdx = size + b-1;
                int rightIdx = size + c-1;

                System.out.println(sum(size, arr.length-1, 1, leftIdx, rightIdx));
            }


        }

        br.close();
    }

    static long initArray(int start, int end, int node){
        if(start == end) {
            return arr[node] = arr[start];
        }
        int mid = (start + end) / 2;

        return arr[node] =  initArray(start, mid, node*2) + initArray(mid+1, end, node*2+1);

    }

    static void update(int start, int end, int node, int idx , long dif){
        if (idx< start || idx>end){ return;}

        arr[node] += dif;
        if(start == end){ return; }

        int mid = (start + end) / 2;
        update(start, mid, node*2, idx, dif);
        update(mid+1, end, node*2+1, idx, dif);


    }

    // start: 시작 인덱스, end: 끝 인덱스
    // left, right: 구간 합을 구하고자 하는 범위
    public static long sum(int start, int end, int node, int left, int right) {
        // 범위 밖에 있는 경우
        if (left > end || right < start) {
            return 0;
        }

        // 범위 안에 있는 경우
        if (left <= start && end <= right) {
            return arr[node];
        }

        // 그렇지 않다면, 두 부분으로 나누어 합을 구하기
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

}
