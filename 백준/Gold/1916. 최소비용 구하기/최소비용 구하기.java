import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1916 최소 비용 구하기
public class Main {
    static int N; //도시의 개수
    static int M; // 버스의 개수
    static List<ArrayList<Node>> graph = new ArrayList<>();
    static int[] dist;
    static int start, end; //시작지점, 종료지점

    public static class Node implements Comparable<Node> {
        int data;
        int weight;
        public Node(int next, int weight) {
            this.data = next;
            this.weight = weight;
        }
        // 우선순위를 정하기 위해 weight(거리) 기준으로 비교
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }


    public static void dijkstra(int start, int end){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        dist[start] = 0;

        while(!queue.isEmpty()){
            Node cur =  queue.poll();
            int currNode = cur.data;
            int currWeight = cur.weight;

            if(currWeight > dist[currNode]) continue; //이미 최소거리이므로 무시

            // 인접한 노드 먼저 탐색
            for(Node nextNode : graph.get(currNode)){
                int weight = nextNode.weight + dist[currNode];
                if ( weight < dist[nextNode.data]){
                    dist[nextNode.data] = weight;
                    queue.add(new Node(nextNode.data, weight));
                }

            }


        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i = 0 ; i < N+1 ; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add( new Node(v,w));
        }

        st = new StringTokenizer(br.readLine());
        start =  Integer.parseInt(st.nextToken());
        end =  Integer.parseInt(st.nextToken());

        if(start == end){
            System.out.println(0);
        }else{
            dijkstra(start, end);
            System.out.println(dist[end]);
        }

    }
}
