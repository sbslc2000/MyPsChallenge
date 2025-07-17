package problems.boj.solved.no_1238_party;

import java.util.*;
import java.io.*;

/**
 * 문제 이름 : 파티
 * 링크 : https://www.acmicpc.net/problem/1238
 * 알고리즘 분류 : 
 * 작성 일시 : 2024-09-17
 * 관련 링크 : 
 */
public class Main {

    static int N, M, X;
    static List<List<int[]>> graph = new ArrayList<>();

    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;
        splited = br.readLine().split(" ");        

        N = Integer.parseInt(splited[0]);
        M = Integer.parseInt(splited[1]);
        X = Integer.parseInt(splited[2]) - 1;

        visited = new boolean[N];
        dist = new int[N];

        for(int i = 0; i < N; i++) {
            graph.add(new LinkedList<>());
        }

        for(int i = 0; i < M; i++) {
           splited = br.readLine().split(" ");
           int u = Integer.parseInt(splited[0]) - 1;
           int v = Integer.parseInt(splited[1]) - 1;
           int w = Integer.parseInt(splited[2]);
           graph.get(u).add(new int[]{v, w});
        }

        dijkstra(X);

        int[] d1 = Arrays.copyOf(dist, dist.length);

        graph = flipGraph();

        dijkstra(X);

        int[] d2 = Arrays.copyOf(dist, dist.length);

        int max = -1;
        for (int i = 0; i < N; i++) {
            int _s = d1[i] + d2[i];
            max = Math.max(_s, max);
        }

        System.out.println(max);
    }

    private static void init() {
        Arrays.fill(visited, false);
        Arrays.fill(dist, Integer.MAX_VALUE);
    }

    private static void dijkstra(int from) {
        init();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        dist[from] = 0;
        pq.add(new int[]{from, 0});

        while(!pq.isEmpty()) {
            int[] polled = pq.poll();
            int v = polled[0];
            int w = polled[1];

            if (visited[v]) {
                continue;
            }

            visited[v] = true;


            for(int[] node: graph.get(v)) {
                // System.out.printf("here with %d, %d\n", node[0], node[1]);
                if (dist[node[0]] > w + node[1]) {
                    dist[node[0]] = w + node[1];
                    pq.add(new int[]{ node[0], dist[node[0]]});
                } 
            }
        }
    }

    private static List<List<int[]>> flipGraph() {
        List<List<int[]>> newGraph = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            newGraph.add(new LinkedList<>());
        }

        for(int i = 0; i < N; i++) {
            List<int[]> nodes = graph.get(i);    
            for (int[] node : nodes) {
                newGraph.get(node[0]).add(new int[]{i, node[1]});
            }
        }

        return newGraph;
    }
}
