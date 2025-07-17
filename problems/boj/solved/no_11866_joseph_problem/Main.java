package problems.boj.solved.no_11866_joseph_problem;

import java.util.*;

/**
 * 문제 이름 : 요세푸스 문제 0
 * 링크 : https://www.acmicpc.net/problem/11866
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-15
 * 관련 링크 :
 * 
 * 시간 제한 : 2초
 * 메모리 제한 : 512MB
 */
public class Main {

    static int N;
    static int K;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            deque.add(i);
        }

        StringBuilder sb = new StringBuilder("");
        sb.append("<");

        while(!deque.isEmpty()) {

            for(int i = 0; i < K - 1; i++) {
                deque.addLast(deque.removeFirst());
            }
            sb.append(deque.removeFirst()+", ");            
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(">");

        System.out.println(sb.toString());
    }

}
