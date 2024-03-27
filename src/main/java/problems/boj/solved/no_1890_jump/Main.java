package problems.boj.solved.no_1890_jump;

import java.util.*;
import java.io.*;

public class Main {
    
    static int[][] MAP = new int[100][100];
    static long[][] cache = new long[100][100];
    static int N;

    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            MAP[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        //logic
        for(int r = N - 1 ; r >= 0; r--) {
            for(int c = N - 1 ; c >= 0; c--) {

                if(r == N - 1 && c == N - 1) {
                    cache[r][c] = 1;
                    continue;
                }

                int jumpSize = MAP[r][c];
                
                if(isInRange(r + jumpSize)) {
                    cache[r][c] += cache[r + jumpSize][c];
                }

                if(isInRange(c + jumpSize)) {
                    cache[r][c] += cache[r][c + jumpSize];
                }

            }
        }

        //output
        System.out.println(cache[0][0]);

    }

    private static boolean isInRange(int n) {
        return n >= 0 && n < N;
    }

}
