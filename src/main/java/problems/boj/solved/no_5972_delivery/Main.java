package problems.boj.solved.no_5972_delivery;

import java.util.*;
import java.io.*;
/**
 * 문제 이름 : 택배 배송
 * 링크 : https://www.acmicpc.net/problem/5972
 * 알고리즘 분류 : 
 * 작성 일시 : 2024-09-08
 * 관련 링크 : 
 */
public class Main {

    static int N, M;

    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static List<List<Node>> map;
    static int[] dist;
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;

        splited = br.readLine().split(" ");

        N = Integer.parseInt(splited[0]);
        M = Integer.parseInt(splited[1]);

        map = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            map.add(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            splited = br.readLine().split(" ");
            int from = Integer.parseInt(splited[0]) - 1;
            int to = Integer.parseInt(splited[1]) - 1;
            int w = Integer.parseInt(splited[2]);

            map.get(from).add(new Node(to, w));
            map.get(to).add(new Node(from, w));
        }

        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);



        visited = new boolean[N];
        dist[0] = 0;

        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{0, 0});

        while(!pq.isEmpty()) {
            int[] min = pq.poll();
            int minIdx = min[0];
            
            if (visited[minIdx]) {
                continue;
            }

            visited[minIdx] = true;

            //relaxation
            List<Node> nodes = map.get(minIdx);
            for(Node n : nodes) {
                if (
                    !visited[n.to] &&
                    dist[n.to] > dist[minIdx] + n.weight
                ) {
                    dist[n.to] = dist[minIdx] + n.weight;
                    pq.add(new int[]{n.to, dist[n.to]});
                }
            }
        }
    
        System.out.println(dist[N-1]);
    }
}
