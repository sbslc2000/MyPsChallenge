package problems.boj.solved.no_1461_library;

import java.util.*;
import java.io.*;

/**
 * 문제 이름 : 도서관
 * 링크 : https://www.acmicpc.net/problem/1461
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-20
 * 관련 링크 : 
 * 
 * 메모리 제한 : 128 MB
 * 시간 제한 : 2초
 * 
 *  - 어차피 N 위치에 책을 놓으려면 N칸 만큼을 이동해야하고, 그 이동 간에 있는 책을 놓는 것은 비용에 포함되지 않는다.
 * - 가장 멀리있는 책을 놓는 것은 마지막에 해야하는데, 이는 돌아오는 비용을 전체 비용에 포함시키지 않기 위함이다.
 * - 부호가 다른 책들을 한번에 옮길 필요는 없는데, 어차피 옮기기 위해 0을 거치게 되므로 고려하는데에 불필요하다.
 */
public class Main {

    static int N;
    static int M;
    
    public static void main(String[] args) throws Exception {
        //var
        String[] splited;
        
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        splited = br.readLine().split(" ");
        N = toInt(splited[0]);
        M = toInt(splited[1]);

        // b - a => 내림차순 정렬
        Queue<Integer> nq = new PriorityQueue<>((a, b) -> b - a); 
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        splited = br.readLine().split(" ");
        for(int i = 0; i < splited.length; i++) {
            int t = toInt(splited[i]);
            if(t > 0) {
                pq.add(t);
            } else {
                nq.add(-t);
            }
        }

        //logic
        int p = pollAndReturnFirst(pq);
        int n = pollAndReturnFirst(nq);

        int count = (p < n) ? p * 2 + n : p + n * 2;

        while(!pq.isEmpty()) {
            count += 2 * pollAndReturnFirst(pq);
        }

        while(!nq.isEmpty()) {
            count += 2 * pollAndReturnFirst(nq);
        }

        System.out.println(count);
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }

    /**
     * M개의 요소를 제거한 뒤 첫번째 요소를 반환한다.
     * 요소가 M개가 되지 않는 경우 남아있는 모든 요소를 제거한다.
     * 요소가 아예 없는 경우 0을 반환하도록 한다.
     */
    private static int pollAndReturnFirst(Queue<Integer> q) {
        int first = 0;

        if(q.isEmpty()) {
            return 0;
        } else {
            first = q.poll();
        }

        for(int i = 0; i < M - 1; i++) {
            if(q.isEmpty()) {
                break;
            } else {
                q.poll();
            }
        }

        return first;
    }
}
