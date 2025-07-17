package problems.boj.solved.no_1261_algospot;

import java.util.*;
import java.io.*;

/**
 * 문제 이름 : 알고스팟
 * 링크 : https://www.acmicpc.net/problem/1261
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-09-12
 * 관련 링크 : 
 * 
 * 메모리 제한 : 128 MB
 * 시간 제한 : 1초
 */
public class Main {

    static int R, C;
    static int[][] map;

    static int[][] dir = {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1}
    };

    static boolean[][] visited;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;
        splited = br.readLine().split(" ");
        C = Integer.parseInt(splited[0]);
        R = Integer.parseInt(splited[1]);
        map = new int[R][C];
        visited = new boolean[R][C];
        dist = new int[R][C];

        for (int i = 0; i < R; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < R; i++) {
            String s = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j) == '1' ? 1 : 0;
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        dist[0][0] = 0;
        pq.add(new int[]{0, 0, 0});

        while(!pq.isEmpty()) {
            int[] polled = pq.poll();
            int r = polled[0];
            int c = polled[1];
            int w = polled[2];
            
            if(visited[r][c]) {
                continue;
            } 

            visited[r][c] = true;

            for(int[] d : dir) {
                int _r = r + d[0];
                int _c = c + d[1];
                if (isInRange(_r, _c) && !visited[_r][_c]) {
                    if (dist[_r][_c] > w + map[_r][_c]) {
                        dist[_r][_c] = w + map[_r][_c];
                        pq.add(new int[]{_r, _c, dist[_r][_c]});
                    }
                }
            }
        }

        System.out.println(dist[R-1][C-1]);
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
