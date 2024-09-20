import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	static int[] time;
	static int[] parent;
	static Queue<Integer> q;
	
	public static void bfs() {

		q.offer(N);
		time[N] =1 ; 
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if(now == K) return;
			
			for(int i = 0 ; i < 3 ; i++) {
				int next;
				
				if(i == 0 ) next =  now+1;
				else if(i ==1) next =  now -1;
				else next = now *2;
				
				if(next < 0 || next > 100000) continue;
				
				if(time[next] == 0) {
					q.add(next);
					parent[next] = now;
					time[next] = time[now]+1;
					
				}
			}
		}
		
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		q = new LinkedList<>();
		time = new int[100001];
		parent = new int[100001];
		
		bfs();
		
		// 첫번째 줄 시간 출력
		 System.out.println(time[K]-1);
		
		 // 두번째 줄 경로 출력
		   Stack<Integer> stack = new Stack<>();
           stack.push(K);
           int index = K;
           while (index!= N ){
               stack.push(parent[index]);
               index = parent[index];
           }

           while (!stack.isEmpty()){
               sb.append(stack.pop()).append(" ");
           }
           System.out.println(sb);

	}

}
