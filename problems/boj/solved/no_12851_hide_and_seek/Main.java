package problems.boj.solved.no_12851_hide_and_seek;

import java.util.*;
import java.io.*;

public class Main {

    static class Pair {

        int pos;
        int min;
        
        public static Pair of(int pos, int min) {
            Pair p = new Pair();
            p.pos = pos;
            p.min = min;
            return p;
        }
    }

    static int N;
    static int K;
    static boolean[] visited = new boolean[100001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        Queue<Pair> q = new LinkedList<>();

        q.add(Pair.of(N, 0));

        boolean findFlag = false;
        int min = 0, ways = 0;
        Pair tmp;
        while(!q.isEmpty()) {
            tmp = q.poll();
            visited[tmp.pos] = true;

            if(tmp.pos == K) {

                if(findFlag && tmp.min != min) {
                    continue;
                }

                findFlag = true;
                min = tmp.min;
                ways++;
            }

            if(!findFlag) {
                

                int nextMin = tmp.min + 1;

                if(isTeleportable(tmp.pos + 1)) {
                    q.add(Pair.of(tmp.pos + 1, nextMin));
                }

                if(isTeleportable(tmp.pos - 1)) {
                    q.add(Pair.of(tmp.pos - 1, nextMin));
                }

                if(isTeleportable(tmp.pos * 2)) {
                    q.add(Pair.of(tmp.pos * 2, nextMin));
                }
            }
        }

        System.out.println(min);
        System.out.println(ways);
    }

    private static boolean isTeleportable(int n) {
        return isInRange(n) && !visited[n];
    }

    private static boolean isInRange(int n) {
        return n >= 0 && n <= 100000;
    }
    
}
