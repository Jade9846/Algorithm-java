import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static List<Node> pos;
    private static double[][] edges;
    private static int[] parent;
    private static double sumWeight = 0.0;

    static class Node{
        Double x;
        Double y;
        public Node( Double x, Double y) {
            this.x = x;
            this.y = y;
        }
    }

    static int find(int a){
        if(a!=parent[a]){
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }

    static boolean union(int a, int b){
        a= find(a);
        b= find(b);
        if(a==b){
            return false;
        }
        else if(a <b){
            parent[b] = a;
        }
        else{
            parent[a] = b;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        edges = new double[n * (n-1) /2 ][3];
        pos = new ArrayList<Node>();
        parent = new int[n];
        for(int i=0 ; i< n; i++){
            parent[i] = i;
        }
        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            pos.add(new Node( x, y));
        }

        int index = 0; // edges 배열 인덱스를 관리하는 변수
        for (int i = 0; i < edges.length; i++) {
            for (int j = i + 1; j < n; j++) {
                edges[index][0] = i;
                edges[index][1] = j;
                edges[index][2] = Math.sqrt(Math.pow(pos.get(i).x - pos.get(j).x, 2) + Math.pow(pos.get(i).y - pos.get(j).y, 2));
                index++; // 인덱스를 증가시켜서 다음 위치에 저장
            }
        }

        // 가중치(edges[i][2]) 기준으로 오름차순 정렬
        Arrays.sort(edges, (o1, o2) -> Double.compare(o1[2], o2[2]));


        for(int i = 0; i < edges.length ; i++){
            if(union((int) edges[i][0], (int) edges[i][1])){
                sumWeight +=edges[i][2];
            }
        }
        br.close();

        // 소수점 둘째 자리까지 결과 출력
        System.out.printf("%.2f\n", sumWeight);
    }
}
