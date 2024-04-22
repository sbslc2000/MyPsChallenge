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



    private static class DijkstraGraph {

        public static class Edge {
            int from;
            int to;
            int weight;

            public Edge(int from, int to, int weight) {
                this.from = from;
                this.to = to;
                this.weight = weight;
            }

            @Override
            public String toString() {
                return String.format("[%d, %d, %d]", from, to, weight);
            }
        }

        int vertexSize;
        Map<Integer, List<Edge>> vertexes = new HashMap<>();

        public DijkstraGraph(int size) {
            this.vertexSize = size;
        }


        public void add(int from, int to, int weight) {
            Edge newEdge = new Edge(from, to, weight);
            List<Edge> edges = vertexes.getOrDefault(from, new LinkedList<>());
            edges.add(newEdge);
            vertexes.put(from, edges);
        }

        public int[] shortestPath(int start) {
            int[] result = new int[vertexSize + 1];
            boolean[] visited = new boolean[vertexSize + 1];
            Arrays.fill(result, Integer.MAX_VALUE);

            Queue<Edge> q = new PriorityQueue<>((a, b) -> b.from - a.from);
            visited[start] = true;
            result[start] = 0;
            relaxation(result, start);
            
            vertexes.getOrDefault(start, Collections.emptyList()).forEach((edge) -> {
                q.add(edge);
            });

            while(!q.isEmpty()) {
                Edge edge = q.poll();
                // System.out.println(edge);
                if(visited[edge.to]) {
                    continue;
                }

                visited[edge.to] = true;
                // System.out.printf("Visit %d\n", edge.to);
                relaxation(result, edge.to);

                vertexes.getOrDefault(edge.to, Collections.emptyList()).forEach((e) -> {
                    q.add(e);
                });
            }

            return result;
        }

        private void relaxation(int[] arr, int visit) {
            List<Edge> edges = vertexes.getOrDefault(visit, Collections.emptyList());

            for(Edge edge : edges) {
                arr[edge.to] = Math.min(arr[edge.to], arr[visit] + edge.weight);
            }
        }
    }

    private static int V;
    private static int E;
    private static int K;

    public static void main(String[] args) throws Exception {
        //var
        int[] splited;

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        splited = read(br);

        V = splited[0];
        E = splited[1];

        K = read(br)[0];

        DijkstraGraph g = new DijkstraGraph(V);

        for(int i = 0; i < E; i++) {
            splited = read(br);
            g.add(splited[0], splited[1], splited[2]);
        }

        //logic
        int[] result = g.shortestPath(K);

        for(int i = 1; i < result.length; i++) {
            if(result[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
                continue;
            }
            System.out.println(result[i]);
        }

    }

    private static int[] read(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

    }
    
}
