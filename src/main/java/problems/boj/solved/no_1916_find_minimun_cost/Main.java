package problems.boj.solved.no_1916_find_minimun_cost;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 : 최소비용 구하기
 * 링크 : https://www.acmicpc.net/problem/1916
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-09-10
 * 관련 링크 : 
 * 
 * 메모리 제한 : 128MB
 * 시간 제한 : 0.5초
 *
 */
public class Main {

    static int N, M;
    static int S, D;
    static List<List<int[]>> graph;

    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            graph.add(new LinkedList<>());
        }

        visited = new boolean[N];
        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        int u,v,w;
        for (int i = 0; i < M; i++) {
            splited = br.readLine().split(" ");

            u = Integer.parseInt(splited[0]) - 1;
            v = Integer.parseInt(splited[1]) - 1;
            w = Integer.parseInt(splited[2]);

            graph.get(u).add(new int[]{v, w});
        }

        splited = br.readLine().split(" ");

        S = Integer.parseInt(splited[0]) - 1;
        D = Integer.parseInt(splited[1]) - 1;

        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        dist[S] = 0;
        pq.add(new int[]{ S, 0 });

        while(!pq.isEmpty()) {
            int[] polled = pq.poll();

            v = polled[0];
            w = polled[1];

            if (visited[v]) {
                continue;
            }

            visited[v] = true;

            //relaxation
            for (int[] nodes : graph.get(v)) {
                if (dist[nodes[0]] > dist[v] + nodes[1]) {
                    dist[nodes[0]] = dist[v] + nodes[1];
                    pq.add(new int[]{ nodes[0], dist[nodes[0]]});
                }
            }
        }

        System.out.println(dist[D]);
        
    }
}
