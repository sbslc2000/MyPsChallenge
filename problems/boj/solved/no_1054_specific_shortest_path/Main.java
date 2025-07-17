package problems.boj.solved.no_1054_specific_shortest_path;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 : 특정한 최단거리
 * 링크 : https://www.acmicpc.net/problem/1054
 * 알고리즘 분류 :
 * 작성 일시 : 2024-09-11
 * 관련 링크 :
 */
public class Main {

    static int MAX = Integer.MAX_VALUE;

    static int N, E;

    static List<List<int []>> graph = new ArrayList<>(); 
    static boolean[] visited;
    static int[] dist;
    static boolean notFoundRoute = false;

    static int V1, V2;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;

        splited = br.readLine().split(" ");

        N = Integer.parseInt(splited[0]);
        E = Integer.parseInt(splited[1]);

        for(int i = 0; i < N; i++) {
            graph.add(i, new LinkedList<>());
        }

        visited = new boolean[N];
        dist = new int[N];

        for(int i = 0; i < E; i++) {
            splited = br.readLine().split(" ");

            int u = Integer.parseInt(splited[0]) - 1;
            int v = Integer.parseInt(splited[1]) - 1;
            int w = Integer.parseInt(splited[2]);

            graph.get(u).add(new int[]{ v, w });
            graph.get(v).add(new int[]{ u, w });
        }

        splited = br.readLine().split(" ");

        V1 = Integer.parseInt(splited[0]) - 1;
        V2 = Integer.parseInt(splited[1]) - 1;

        int v1FirstSum = findShortestPathWithRequiredNode(V1, V2);
        int v2FirstSum = findShortestPathWithRequiredNode(V2, V1);
     
        int minVal = Math.min(v1FirstSum, v2FirstSum);

        if(minVal == MAX) {
            System.out.println(-1);
        } else {
            System.out.println(minVal);
        }
    }

    private static int findShortestPathWithRequiredNode(int v1, int v2) {
        int d1 = findShortestPath(0, v1);
        int d2 = findShortestPath(v1, v2);
        int d3 = findShortestPath(v2, N - 1);

        if(d1 == MAX || d2 == MAX || d3 == MAX) {
            return MAX;
        } else {
            return d1 + d2 + d3;
        }

    }

    private static int findShortestPath(int from, int to) {
        init();

        dist[from] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] -  b[1]);
        pq.add(new int[]{from, 0});

        while(!pq.isEmpty()) {
            int[] polled = pq.poll();
            int _to = polled[0];
            int _w = polled[1];

            if (visited[_to]) {
                continue;
            }

            visited[_to] = true;

            for (int[] n : graph.get(_to)) {
                int nTo = n[0];
                int nW = n[1];

                if (dist[nTo] > nW + _w) {
                    dist[nTo] = nW + _w;
                    pq.add(new int[]{nTo, dist[nTo]});
                }
            }
        }

        return dist[to];
    }

    private static void init() {
        Arrays.fill(visited, false);
        Arrays.fill(dist, Integer.MAX_VALUE);
    }
}
