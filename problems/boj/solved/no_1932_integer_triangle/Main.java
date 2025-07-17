package problems.boj.solved.no_1932_integer_triangle;

import java.io.*;

public class Main {

    static int N;
    static int[][] triangle;
    static int[][] cache;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;
        N = Integer.parseInt(br.readLine()); 
        triangle = new int[N][];
        cache = new int[N][];
        for (int i = 0; i < N; i++) {
            splited = br.readLine().split(" ");
            triangle[i] = new int[i + 1];
            cache[i] = new int[i + 1];
            for(int j = 0; j < i + 1; j++) {
                triangle[i][j] = Integer.parseInt(splited[j]);
            }
        }

        System.out.println(findMaxSum(0,0));
    }

    private static int findMaxSum(int r, int c) {
        if(!isInRange(r, c)) {
            return 0;
        }

        if (cache[r][c] != 0) {
            return cache[r][c];
        }

        int res = Math.max(findMaxSum(r + 1, c),findMaxSum(r + 1, c + 1)) + triangle[r][c];
        cache[r][c] = res;
        return res;
    }
    
    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < r+1;
    }
}
