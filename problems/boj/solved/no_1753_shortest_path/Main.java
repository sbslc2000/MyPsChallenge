package problems.boj.solved.no_1753_shortest_path;

import java.util.*;
import java.io.*;


/**
 * 문제 이름 : 최단경로
 * 링크 : https://www.acmicpc.net/problem/1753
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-21
 * 관련 링크 : 
 * 
 * 메모리 제한 : 256 MB
 * 시간 제한 : 1초
 * 
 * 방향이 있는 그래프에서의 최단 경로이며, 10이하의 자연수 
 * -> 음수가 없으므로 Dijkstra 알고리즘으로 풀릴 수 있어 보인다.
 */
public class Main {

    static class Node {
        int to;
        int w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    static Map<Integer, List<Node>> graph;

    static int V;
    static int E;
    static int K;

    static boolean[] visited;
    static int[] distance;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;

        splited = br.readLine().split(" ");

        V = Integer.parseInt(splited[0]);
        E = Integer.parseInt(splited[1]);

        K = Integer.parseInt(br.readLine()) - 1;

        graph = new HashMap<>(); 
        for(int i = 0; i < E; i++) {
            graph.put(i, new LinkedList<>());
        }
        visited = new boolean[V];
        distance = new int[V];

        Arrays.fill(distance, Integer.MAX_VALUE);

        int u, v, w;
        for (int i = 0; i < E; i++) {
            splited = br.readLine().split(" ");
            u = Integer.parseInt(splited[0]) - 1;
            v = Integer.parseInt(splited[1]) - 1;
            w = Integer.parseInt(splited[2]);
            List<Node> l = graph.get(u);
            l.add(new Node(v, w));
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); 

        distance[K] = 0;
        pq.add(new int[]{K, 0});

        while(!pq.isEmpty()) {
            int[] polled = pq.poll();
            int _v = polled[0];
            int _w = polled[1];

            if (visited[_v]) {
                continue;
            }

            visited[_v] = true;

            List<Node> _l = graph.get(_v);

            for (Node n : _l) {
                if (distance[n.to] > n.w + _w) {
                    distance[n.to] = n.w + _w; 
                    pq.add(new int[]{n.to, distance[n.to]});
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0; i < distance.length; i++) {
            String s = distance[i] == Integer.MAX_VALUE ? 
                "INF" : String.valueOf(distance[i]);
            bw.write(s);
            bw.write('\n');

            // System.out.println(distance[i]);
        }

        bw.flush();
    
    }
}
