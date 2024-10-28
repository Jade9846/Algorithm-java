import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int cntPoint; // 점의 개수
    static int cntTurn; // 진행된 차례의 수
    static int[] parent;
    static int result = 0;

    public static int find(int a){
        if(a != parent[a]){
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }

    public static boolean union(int p, int q) {
        p = find(p);
        q = find(q);
        ++result;
        if (p == q) {
            //사이클 존재
            System.out.println(result);
            return true;
        }
        if (p < q){
            parent[q] = p;
        }else{
            parent[p] = q;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        cntPoint = Integer.parseInt(st.nextToken());
        cntTurn = Integer.parseInt(st.nextToken());
        parent = new int[cntPoint]; // 0 부터 cntPoint-1까지 고유한 번호가 있다

        for(int i = 0; i < cntPoint; i++ ){
            parent[i] = i;
        }


        for(int i = 0; i < cntTurn ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(union(a,b)){
                System.exit(0);
            }
        }
        if(result >= cntTurn ){
            System.out.println(0);
        }




    }

}
