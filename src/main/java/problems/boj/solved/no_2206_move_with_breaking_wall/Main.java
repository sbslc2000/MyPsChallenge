package problems.boj.solved.no_2206_move_with_breaking_wall;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] map; // false : 1 : cannot go

    static int[][] dir = {
        { 1, 0},
        { -1, 0},
        {0 , 1},
        {0, -1},
    };

    static boolean[][] visited;
    static boolean[][] brokenVisited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;
        splited = br.readLine().split(" ");

        N = Integer.parseInt(splited[0]);
        M = Integer.parseInt(splited[1]);

        map = new int[N][M];
        visited = new boolean[N][M];
        brokenVisited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        // for (int i = 0; i < N ; i++) {
        //     for(int j = 0; j < M; j++) {
        //         System.out.print(map[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        int min = Integer.MAX_VALUE;

        min = Math.min(min, getMinDistance());

        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < M; j++) {
        //         if (map[i][j] == 1) {
        //             map[i][j] = 0;
        //             min = Math.min(min, getMinDistance());
        //             map[i][j] = 1;
        //         }
        //     }
        // }

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }

    }

    private static int getMinDistance() {

        Queue<Integer> q = new LinkedList<>();

        visited[0][0] = true;

        q.add(0);
        q.add(0);
        q.add(1);
        q.add(0);

        while(!q.isEmpty()) {
            int n = q.poll();
            int m = q.poll();
            int d = q.poll();
            int b = q.poll();

            // System.out.printf("n: %d, m: %d, d: %d b:%d\n",n, m, d, b);

            if (n == N - 1 && m == M - 1) {
                return d;
            }

            boolean[][] v = b == 0 ? visited : brokenVisited;

            for(int[] _d : dir) {

                int newN = n + _d[0];
                int newM = m + _d[1];
                // System.out.printf("newN : %d, newM: %d\n",newN, newM);

                if (isInRange(newN, newM) && !v[newN][newM]) {

                    if(isMovable(newN, newM)) {
                        // System.out.printf("visited %d %d\n", newN, newM);
                        
                        v[newN][newM] = true;
                        q.add(newN);
                        q.add(newM);
                        q.add(d + 1);
                        q.add(b);
                    } else if (b == 0) {
                        v[newN][newM] = true;
                        q.add(newN);
                        q.add(newM);
                        q.add(d + 1);
                        q.add(b + 1);
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    private static boolean isMovable(int n, int m) {
        boolean isMovable = map[n][m] == 0;
        // System.out.println("isMovable: "+ isMovable);
        return isMovable;
    }
    
    private static boolean isInRange(int n, int m) {
        boolean isInRange = n >= 0 && n < N && m >= 0 && m < M;
        // System.out.println("isInRange: " + isInRange);
        return isInRange;
    }
}
