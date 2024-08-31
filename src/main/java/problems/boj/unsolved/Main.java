package problems.boj.unsolved;

import java.util.*;

public class Main {

    static int V, E;
    static List<List<Integer>> graph;
    static boolean[] isVisited;
    static int infectedCount = 0; // 감염된 컴퓨터의 개수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();

        graph = new ArrayList<>(); 

        for(int i = 0; i <= V; i++) {
            graph.add(i, new LinkedList<>());
        }

        isVisited = new boolean[V+1];

        E = sc.nextInt();

        for(int i = 0; i < E; i++) {
            //1 2
            int v1 = sc.nextInt(); 
            int v2 = sc.nextInt();

            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        infectByBfs(1);

        System.out.println(infectedCount - 1);
    }
    // 이 메서드가 호출되는 시점에서, v는 방문하지 않은 상태다!
    // private static void infect(int v) {
    //     isVisited[v] = true;
    //     infectedCount++;
    //     for(int i = 1; i <= V; i++) {
    //         if(!isVisited[i] && graph[v][i]) {
    //             infect(i);
    //         }
    //     }
    // }

    private static void infectByBfs(int sp) {
        //integer : 정점 번호
        Queue<Integer> queue = new LinkedList<>();

        isVisited[1] = true;
        infectedCount++;
        queue.add(1);

        while(!queue.isEmpty()) {
            int v = queue.poll();

       
            for(Integer i : graph.get(v)) {
                if(!isVisited[i]) {
                    isVisited[i] = true;
                    infectedCount++;
                    queue.add(i);
                }
            }
        }
    } 
}