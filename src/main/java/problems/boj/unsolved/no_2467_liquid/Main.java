package problems.boj.unsolved.no_2467_liquid;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 : 용액
 * 링크 : https://www.acmicpc.net/problem/2467
 * 알고리즘 분류 : ?
 * 작성 일시 : 2023-11-25
 * 관련 링크 :
 * 
 * 시간 제한 : 1초
 * 메모리 제한 : 128M
 */

public class Main {

    static int N;
    static int min = Integer.MAX_VALUE;
    static int minLeft;
    static int minRight;
    public static void main(String[] args) throws Exception {
        //var
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> Math.abs(a) - Math.abs(b));

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()) {
            queue.add(Integer.parseInt(st.nextToken()));
        }

        //logic
        int left = queue.poll();
        int right;
        while(!queue.isEmpty()) {
            right = queue.poll();
            if(Math.abs(left + right) < min) {
                minLeft = left;
                minRight = right;
                min = Math.abs(left+right);
            }

            left = right;
        }

        //output

        System.out.println(minLeft < minRight ? 
            minLeft + " " + minRight : 
            minRight + " " + minLeft );
    }
}
