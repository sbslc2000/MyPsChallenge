package problems.boj.solved.no_2644_calculate_relatives;

import java.util.*;
import java.io.*;

/*  
* 문제 이름 : 촌수 계산
* 링크 : https://www.acmicpc.net/problem/2644
* 알고리즘 분류 : ?
* 작성 일시 : 2024-04-19
* 관련 링크 :
* 시간 제한 : 1초
* 메모리 제한 : 128M
*/  
public class Main {
    

    static int[][] map;
    static boolean[][] visited;

    static int N;

    static int A;
    static int B;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];

        String[] splited = br.readLine().split(" ");
        A = Integer.parseInt(splited[0]);
        B = Integer.parseInt(splited[1]);

        int m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++) {
            splited = br.readLine().split(" ");
            int x = Integer.parseInt(splited[0]);
            int y = Integer.parseInt(splited[1]);

            map[x][y] = 1;
            map[y][x] = 1;
        }

        //logic
        int result = find();

        //output
        System.out.println(result);
    }

    private static int find() {
        Queue<Integer> q = new LinkedList<>(); 
        q.add(A);
        q.add(0);

        int p = 0;
        int n = 0;
        int result = -1;
        while(!q.isEmpty()) {
            p = q.poll();
            n = q.poll();

            if(p == B) {
                result = n;
                break;
            }

            for(int i = 1; i <= N; i++) {
                if(!visited[p][i] && map[p][i] == 1) {
                    visited[p][i] = true;
                    q.add(i);
                    q.add(n+1);
                }

                if(!visited[i][p] && map[i][p] == 1) {
                    visited[i][p] = true;
                    q.add(i);
                    q.add(n+1);
                }
            }

        }

        return result;
    }
}
