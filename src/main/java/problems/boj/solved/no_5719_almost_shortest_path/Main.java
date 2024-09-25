package problems.boj.solved.no_5719_almost_shortest_path;

import java.util.*;
import java.io.*;
/**
 * 문제 이름 : 거의 최단 경로
 * 링크 : https://www.acmicpc.net/problem/5719
 * 알고리즘 분류 : 
 * 작성 일시 : 2024-09-19
 * 관련 링크 : 
 */
public class Main {

    static int N, M;
    static int S, E;
    static int[][] graph = new int[500][500];
    static boolean[] visited;
    static int[] prev;
    static int[] dist;
    static int shortest;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;
        while(true) {
            splited =  br.readLine().split(" ");
            N = Integer.parseInt(splited[0]);
            M = Integer.parseInt(splited[1]);

            if (N == 0 && M == 0) {
                break;
            }

            init();

            splited = br.readLine().split(" ");
            S = Integer.parseInt(splited[0]);
            E = Integer.parseInt(splited[1]);

            for (int i = 0; i < M; i++) {
                splited = br.readLine().split(" ");

                int u = Integer.parseInt(splited[0]);
                int v = Integer.parseInt(splited[1]);
                int w = Integer.parseInt(splited[2]);

                graph[u][v] = w;
            }

            printGraph();
        
            dijkstra();
            shortest = dist[E];
            // System.out.printf("Shortest: %d\n", shortest);

            if (shortest == Integer.MAX_VALUE) {
                System.out.println(-1);
                continue;
            }

            while (true) {
                removeShortestEdges();
                printGraph();
                dijkstra();
                // System.out.printf("New Shortest: %d\n", dist[E]);
                if (shortest != dist[E]) {
                    break;
                }
            }
            
            int res = dist[E] == Integer.MAX_VALUE ? -1 : dist[E];
            System.out.println(res);
        }
    }

    private static void dijkstra() {

        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        prev = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        visited = new boolean[N];

        // System.out.println("dijkstra() called");
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{ S, 0 });
        dist[S] = 0;
        prev[S] = 0;

        while(!pq.isEmpty()) {
            int[] polled = pq.poll();

            int v = polled[0];
            int w = polled[1];

            if (visited[v]) {
                continue;
            }

            visited[v] = true;

            for (int i = 0; i < N; i++) {
                if (graph[v][i] == Integer.MAX_VALUE) {
                    continue;
                }

                if (dist[i] > w + graph[v][i]) {
                    dist[i] = w + graph[v][i];
                    prev[i] = v;
                    pq.add(new int[]{ i, dist[i]});
                }
            }
        }
    }

    private static void removeShortestEdges() {
        // System.out.println("removeShortestEdges called");
        int _p1 = E;
        int _p2 = prev[E];

        while (_p2 != Integer.MAX_VALUE && _p1 != _p2) {
            graph[_p2][_p1] = Integer.MAX_VALUE;
            _p1 = _p2;
            _p2 = prev[_p2];
        }

        printGraph();
    }

    private static void init() {
        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        prev = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    private static void printGraph() {
        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < N; j++) {
        //         System.out.print(graph[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }
}
