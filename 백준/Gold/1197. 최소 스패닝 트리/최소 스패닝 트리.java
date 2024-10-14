import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    private static int v;
    private static int e;
    private static int[] parents;
    private static int[][] edges;
    private static int sumWeight;

    public static int find(int a){
        if(a!=parents[a]){
            parents[a] = find(parents[a]);
        }
        return parents[a];
    }
    public static boolean union(int a, int b){
        a = find(a);
        b = find(b);
        //find 로 대표 노드 다를때만 union 한다
        if(a == b){
            return true;
        }else if(b<a){
            parents[a] = b;
        }else{
            parents[b] = a;
        }
        return false;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        edges = new int[e][3];
        parents = new int[v+1];
        for(int i =1 ; i <= v; i++){
            parents[i] = i;
        }
        for(int i = 0 ; i <e ; i++ ){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        // 가중치(edges[i][2]) 기준으로 오름차순 정렬
        Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);

        for(int i = 0 ; i < e; i++){
            //union 연산
            if (!union(edges[i][0], edges[i][1])) {
                sumWeight +=  edges[i][2];
            }

        }


        br.close();
        System.out.println(sumWeight);

    }
}
