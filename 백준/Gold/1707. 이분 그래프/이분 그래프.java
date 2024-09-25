import java.util.*;

public class Main {

	static ArrayList<ArrayList<Integer>> graph ;
	static Queue<Integer> q;
	static int [] visited;
	static String result;
	
	
	  public static boolean bfs(int start) {
	        Queue<Integer> queue = new LinkedList<>();
	        queue.offer(start);
	        visited[start] = 1;  // 첫 노드를 1로 색칠

	        while (!queue.isEmpty()) {
	            int node = queue.poll();

	            for (int neighbor : graph.get(node)) {
	                if (visited[neighbor] == 0) {  // 아직 방문하지 않은 경우
	                    visited[neighbor] = -visited[node];  // 인접 노드는 다른 색으로
	                    queue.offer(neighbor);
	                } else if (visited[neighbor] == visited[node]) {
	                    return false;  // 같은 색이면 이분 그래프가 아님
	                }
	            }
	        }

	        return true;
	    }
	
	  public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        int K = scanner.nextInt();  // 테스트 케이스 수

	        for (int t = 0; t < K; t++) {
	            int V = scanner.nextInt();  // 정점 수
	            int E = scanner.nextInt();  // 간선 수

	            graph = new ArrayList<>();
	            for (int i = 0; i <= V; i++) {
	                graph.add(new ArrayList<>());
	            }

	            visited = new int[V + 1];  // 방문 여부 및 색깔 저장

	            for (int i = 0; i < E; i++) {
	                int u = scanner.nextInt();
	                int v = scanner.nextInt();
	                graph.get(u).add(v);
	                graph.get(v).add(u);
	            }
	            result = "YES";
	            // 모든 노드에 대해 방문 여부 확인
	            for (int i = 1; i <= V; i++) {
	                if (visited[i] == 0) {  // 방문하지 않은 노드에 대해 BFS 실행
	                    if (!bfs(i)) {
	                        break;
	                    }
	                }
	            }
	            for(int i= 1 ; i< V;i++) {
	            	for(int next : graph.get(i)) {
	            		if(visited[next] == visited[i]) {
	            			result="NO";
	            			break;
	            		}
	            	}
	            	if(result == "NO") {
	            		break;
	            	}
	            }
	            System.out.println(result);
	        }
	  }
}
