import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int V, E;
    private static ArrayList<ArrayList<Edge>> graph; // 간선 정보를 저장할 리스트
    private static int[] dist; // 최단 거리 배열

    // Edge 클래스: 목적지와 가중치를 저장
    static class Edge {
        int to, weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{start, 0});
        dist[start] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int nowNode = current[0];
            int nowWeight = current[1];

            // 현재 노드의 최단 거리가 큐에서 꺼낸 경로보다 짧으면 무시
            if (dist[nowNode] < nowWeight) continue;

            // 현재 노드와 연결된 모든 간선을 탐색
            for (Edge next : graph.get(nowNode)) {
                int nextNode = next.to;
                int nextWeight = next.weight;

                if (dist[nextNode] > dist[nowNode] + nextWeight) {
                    dist[nextNode] = dist[nowNode] + nextWeight;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
    }


public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    V = Integer.parseInt(st.nextToken()); // 정점의 개수
    E = Integer.parseInt(st.nextToken()); // 간선의 개수
    int start = Integer.parseInt(br.readLine()); // 시작 정점

    // 최단 거리 배열을 INF로 초기화
    dist = new int[V + 1];
    Arrays.fill(dist, INF);

    // 그래프 초기화
    graph = new ArrayList<>();
    for (int i = 0; i <= V; i++) {
        graph.add(new ArrayList<>());
    }

    // 간선 입력
    for (int i = 0; i < E; i++) {
        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());
        graph.get(u).add(new Edge(v, weight)); // u에서 v로 가는 가중치가 weight인 간선 추가
    }

    // 다익스트라 알고리즘 실행
    dijkstra(start);

    // 결과 출력
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= V; i++) {
        sb.append(dist[i] == INF ? "INF\n" : dist[i] + "\n");
    }

    System.out.print(sb.toString());
    br.close();
    }
}


