import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] box;
	static int [] dx = {-1, 1, 0, 0} ;
	static int [] dy = {0, 0, -1, 1} ;
	static Queue<Point> q = new LinkedList<>();
	
	public static int bfs() {
		
		while(!q.isEmpty()) {
			Point nowPoint = q.poll();
			int nowX = nowPoint.x;
			int nowY = nowPoint.y;

			for(int i = 0 ; i < 4; i++) {
				int nextX = nowX + dx[i] ;
				int nextY = nowY + dy[i] ; 
				if(nextX < 0 || nextY< 0 || nextX>=N  || nextY >= M) {
					continue;
				}
				if(box[nextX][nextY] == 0) {
					box[nextX][nextY] = box[nowX][nowY]+ 1;
					q.add(new Point(nextX, nextY));
				}
			}		
		}
		int cnt = Integer.MIN_VALUE;
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j++) {
				if(box[i][j] == 0) {
					return -1;
				}
				cnt = Math.max(cnt, box[i][j]);
			}
		}
		
		return cnt-1;
		
	
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0 ; j < M ; j++) {
				box[i][j] = Integer.parseInt( st.nextToken());
				if(box[i][j] == 1){
                    q.offer(new Point(i,j));
                }
			}
		}
		System.out.println(bfs());
		
	}
	public static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
