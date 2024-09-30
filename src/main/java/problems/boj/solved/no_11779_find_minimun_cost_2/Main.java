package problems.boj.solved.no_11779_find_minimun_cost_2;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int A, B;

    static List<List<int[]>> graph = new ArrayList<>();

    static boolean[] visited;
    static int[] dist;
    static int[] prev;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            graph.add(new LinkedList<>());
        }

        visited = new boolean[N];
        dist = new int[N];
        prev = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, Integer.MAX_VALUE);


        for (int i = 0; i < M; i++) {
            splited = br.readLine().split(" ");
            int u = Integer.parseInt(splited[0]) - 1;
            int v = Integer.parseInt(splited[1]) - 1;
            int w = Integer.parseInt(splited[2]);

            graph.get(u).add(new int[]{v, w});
        }

        splited = br.readLine().split(" ");

        A = Integer.parseInt(splited[0]) - 1;
        B = Integer.parseInt(splited[1]) - 1;

        dijkstra(A);

        System.out.println(dist[B]);

        int count = 0;
        int p = B;
        Stack<Integer> s = new Stack<>();
        while (true) {
            s.push(p + 1);
            if(p == A) {
                break;
            } 
            p = prev[p];
            count++;
        }

        System.out.println(count);

        while(!s.isEmpty()) {
            System.out.printf("%d ", s.pop());
        }
    }

    private static void dijkstra(int sp) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        dist[sp] = 0;
        prev[sp] = 0;
        pq.add(new int[]{sp, 0});

        while(!pq.isEmpty()) {
            int[] polled = pq.poll();
            int v = polled[0];
            int w = polled[1];

            if (visited[v]) {
                continue;
            }

            visited[v] = true;

            for (int[] node : graph.get(v)) {
                if (dist[node[0]] > w + node[1]) {
                    dist[node[0]] = w + node[1];
                    prev[node[0]] = v;
                    pq.add(new int[]{ node[0], dist[node[0]]});
                }
            }
        }
    }
    
}
