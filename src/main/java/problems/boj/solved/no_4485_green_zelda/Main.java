package problems.boj.solved.no_4485_green_zelda;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 : 녹색 옷 입은 애가 젤다지?
 * 링크 : https://www.acmicpc.net/problem/4485
 * 알고리즘 분류 : ?
 * 작성 일시 : 11-29
 * 관련 링크 : 
 * 
 * 
 * dp? 안된다. 최적 부분구조 안나옴
 * 그래프 최소비용? dijkstra 된다.
 * 모든 수는 양수이므로 dijkstra 가 적절해보인다.
 */
class Main {

    static int N;
    static int[][] graph;
    static int cost[][];
  
    static class Direction {
        int r;
        int c;

        private Direction(int r, int c) {
            this.r = r;
            this.c = c;
        }

        static Direction LEFT = new Direction(-1,0);
        static Direction UP = new Direction(0,-1);
        static Direction RIGHT = new Direction(0,1);
        static Direction DOWN = new Direction(1,0);

        static List<Direction> list = List.of(LEFT, UP, RIGHT, DOWN);
    }

    static class Pair implements Comparable<Pair> {
        int r;
        int c;
        int value;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
            this.value = cost[r][c];
        }

        @Override
        public int compareTo(Pair p) {
            return this.value - p.value;
        }
    }

    public static void main(String[] args) throws Exception {
        //var
        int i,j;
        StringTokenizer st;
        boolean[][] visited;
        int problem = 0;

        PriorityQueue<Pair> queue;
        Pair p;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while(true) {
            problem++;
            //input
            N = Integer.parseInt(br.readLine());

            if(N == 0) break;

            graph = new int[N][N];

            for(i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                j = 0;
                while(st.hasMoreTokens()) {
                    graph[i][j++] = Integer.parseInt(st.nextToken());
                }
            }

            //logic
            //Dijkstra 어떻게 하더라
            //Dijkstra는 Greedy가 최적해를 보장하는 몇 안되는 예
            // 시작 지점에서 갈 수 있는 가장 최소 비용으로 이동한 뒤, relaxation

            cost = new int[N][N];
            for(i = 0; i < N ; i++) {
                Arrays.setAll(cost[i], num -> Integer.MAX_VALUE);
            }

            visited = new boolean[N][N];

            queue = new PriorityQueue<>();
            cost[0][0] = graph[0][0];

            queue.add(new Pair(0,0));

            while((p = queue.poll()) != null) { //extract min
                // System.out.printf("Pair : %d %d %d\n",p.r, p.c, p.value);

                if(visited[p.r][p.c]) {
                    continue;
                }

                visited[p.r][p.c] = true; 

                for(Direction direction: Direction.list) {
                    int r = p.r + direction.r;
                    int c = p.c + direction.c;
                    // System.out.printf("new r c : %d %d\n", r, c);

                    //check if r and c is in range
                    if(r < 0 || r >= N || c < 0 || c >= N) {
                        continue;
                    } 

                    
                    // System.out.printf("visit = "+visited[r][c]+" , cost(%d) + graph(%d) < cost(%d)\n",cost[p.r][p.c], graph[r][c], cost[r][c]);
                    if(!visited[r][c] && cost[p.r][p.c] + graph[r][c] < cost[r][c]) {
                        cost[r][c] = cost[p.r][p.c] + graph[r][c];
                        queue.add(new Pair(r,c));
                    }
                }
            }

            System.out.printf("Problem %d: %d\n", problem ,cost[N-1][N-1]);
        }
    }
}
