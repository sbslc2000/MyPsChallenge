package problems.boj.solved.no_5014_start_link;

import java.util.*;

/**
 * 문제 이름 : 스타트링크
 * 링크 : https://www.acmicpc.net/problem/5014
 * 알고리즘 분류 : 
 * 작성 일시 : 2024-04-22
 * 관련 링크 : 
 * 
 * 
 */
public class Main {

    static int F, S, G, U, D;
    static boolean[] visited = new boolean[10000000];
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        F = sc.nextInt();
        S = sc.nextInt();
        G = sc.nextInt();
        U = sc.nextInt();
        D = sc.nextInt();

        Queue<Integer> q = new LinkedList<>();

        q.add(S);
        q.add(0);

        while(!q.isEmpty()) {
            int h = q.poll();
            int c = q.poll();

            if(h == G) {
                System.out.println(c);
                return;
            }

            int up = h + U;
            int down = h - D;

            if(isInRange(up) && !visited[up]) {
                visited[up] = true;
                q.add(up);
                q.add(c+ 1);
            }

            if(isInRange(down) && !visited[down]) {
                visited[down] = true;
                q.add(down);
                q.add(c + 1);
            }
        }

        System.out.println("use the stairs");

    }

    private static boolean isInRange(int h) {
        return h > 0 && h <= F;
    }
    
}
