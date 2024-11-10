import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, E, requiredNode1, requiredNode2  ; // 정점의 개수 N, 간선의 개수 E, 거쳐야하는 노드 requiredNode1, requiredNode2
    static List<Node>[] graph;
    static int[] dist;
    static int INF = 200000 * 800 + 1;

    static class Node{
        int data;
        int weight;
        public Node(int data, int weight){
            this.data = data;
            this.weight = weight;
        }
    }
    public static void dijkstra(int start){
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.weight,b.weight));
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            for(Node next : graph[cur.data]){
                if(dist[cur.data] + next.weight < dist[next.data]){
                    dist[next.data] = dist[cur.data] + next.weight;
                    pq.add(new Node(next.data,dist[next.data]));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];

        for(int i = 0 ; i < N+1 ; i++){
            graph[i] = new ArrayList<>();
        }
        dist = new int[N+1];
        for(int i = 0 ; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v,w));
            graph[v].add(new Node(u,w));
        }
        st = new StringTokenizer(br.readLine());
        requiredNode1 = Integer.parseInt(st.nextToken());
        requiredNode2 = Integer.parseInt(st.nextToken());

        Arrays.fill(dist, INF);
        dijkstra(1);
        int startToN1 = dist[requiredNode1];
        int startToN2 = dist[requiredNode2];

        Arrays.fill(dist, INF);
        dijkstra(requiredNode1);
        int n1ToN2 = dist[requiredNode2];
        int n1ToEnd = dist[N];

        Arrays.fill(dist, INF);
        dijkstra(requiredNode2);
        int n2ToEnd = dist[N];

        int result = Math.min(startToN1 + n1ToN2 + n2ToEnd, startToN2 + n1ToN2 + n1ToEnd);
        result = result >= INF ? -1 : result;
        System.out.println(result);

    }
}
