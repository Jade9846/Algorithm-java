import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, 1, -1, 0};

	    
	static void dfs(int x, int y) {
		visited[x][y] = true;
		char crt_char = map[x][y];
		for(int i = 0; i < 4; i++) {
			int next_x = x+ dx[i];
			int next_y = y+ dy[i];
			if(next_x < 0 || next_y < 0|| next_x >= N || next_y>=N) {
				continue;
			}
			if(!visited[next_x][next_y] && map[next_x][next_y] == crt_char) {
				dfs(next_x, next_y);
			}
		}
		
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];
		map= new char[N][N];
		for(int i = 0; i < N; i++) {
			 String line = br.readLine();
			for(int j = 0 ; j < N; j++) {
				map[i][j] = line.charAt(j);
			}
		}
			
		// 적록색약 아닌 사람
		int result1 = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(!visited[i][j] ){
					dfs(i, j);
					result1++;
				}
			}
		}
			
		// 적록색약인 사람
		int result2= 0;
		for(int i = 0; i< N ; i++) {
			for(int j = 0 ; j < N; j++) {
				if(map[i][j] == 'G'){
						map[i][j]= 'R';
				}
			
			}
		}
		visited = new boolean[N][N];
		for(int i = 0; i< N ; i++) {
			for(int j = 0 ; j < N; j++) {
				if(!visited[i][j]){
					dfs(i,j);
					result2++;
				}
			}
		}
		System.out.println(result1+" "+result2);
	}

}
