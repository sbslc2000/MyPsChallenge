package problems.boj.solved.no_13549_hide_and_seek_3;

import java.util.*;

/**
 * 문제 이름 : 숨바꼭질 3
 * 링크 : https://www.acmicpc.net/problem/13549
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-05-07
 * 관련 링크 : 
 */
public class Main {

    static class Case {
        int pos;
        int time;

        public Case(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }

        public Case next() {
            return new Case(pos + 1, time + 1);
        }

        public Case prev() {
            return new Case(pos - 1, time + 1);
        }

        public Case doub() {
            return new Case(pos * 2, time);
        }
    }
    
    static int N;
    static int K;
    static int[] visited;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        K = sc.nextInt();

        visited = new int[200000];
        for(int i = 0; i < 200000; i++) {
            visited[i] = Integer.MAX_VALUE;
        }

        Queue<Case> q = new PriorityQueue<>((a, b) -> a.time - b.time);

    
        q.add(new Case(N, 0));

        while(!q.isEmpty()) {
            Case c = q.poll();

            if(c.pos > 200000) {
                continue;
            }

            if (c.pos == K) {
                min = Math.min(min, c.time); 
                continue;
            }

            if(isInRange(c.pos - 1) && visited[c.pos - 1] > c.time + 1) {
                visited[c.pos - 1] = c.time + 1;
                q.add(c.prev());
            }

            if(isInRange(c.pos + 1) && visited[c.pos + 1] > c.time + 1) {
                visited[c.pos + 1] = c.time + 1;
                q.add(c.next());
            }

            if(isInRange(c.pos * 2) && visited[c.pos * 2] > c.time) {
                visited[c.pos * 2] = c.time;
                q.add(c.doub());
            }
        }

        System.out.println(min);
    }

    private static boolean isInRange(int p) {
        return p >= 0 && p < 200000;
    }
}
