import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L, C;
	static char[] charList;
	static boolean[] check;
	
	//최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성
	public static void dfs(int idx, int depth) {
		if(depth == L) {
			String answer = "";
			int mo = 0;
			int ja = 0;
			for(int i = 0 ; i < C; i++) {
				if(check[i]) {
					char c = charList[i];
					answer += c ;
					if(c == 'a' ||  c == 'e' || c=='i' || c=='o' || c =='u') {
						mo++;
					}else {
						ja++;
					}
				}
				
			}
			if(mo >= 1 && ja >= 2) {
				System.out.println(answer);
				return;
			}
		}
		for(int i = idx; i< C; i++) {
			if(check[i] == false) {
				check[i] = true;
				
				dfs(i+1, depth+1);
				check[i] = false;
			}
		}

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
	
		charList = new char[C];
		check = new boolean[C];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < C; i++) {
			charList[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(charList);
		dfs(0, 0);
	}
}
