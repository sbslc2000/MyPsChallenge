package problems.boj.solved.no_22233_gahee_keyword;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 : 가희와 키워드
 * 링크 : https://www.acmicpc.net/problem/22233
 * 알고리즘 분류 : ?
 * 작성 일시 : 11-30
 * 관련 링크 : 
 * 
 * 시간 제한: 1.5초
 * 메모리 제한: 512MB
 * 
 * 쉬워보이지만 N과 M의 입력값이 2*10^5승으로,
 * 단순히 풀었을 때 최악의 경우
 * 리스트를 2*10^5승짜리 리스트를 2*10^5번 순회해야하므로 4*10^10 번의 연산이 발생
 * N^2 의 문제를 더 줄일 방법은?
 * 어떻게 키워드를 빠르게 찾을 것인가?
 * 정렬 후 이분탐색 -> n log n 수준으로 내릴 수 있음
 * n의 시간복잡도를 가지게 할 수 있나?
 * 
 * 풀이: 이진탐색 안하고
 * O(1) 로 탐색할 수 있는 Set을 사용했다.
 */

public class Main {

    static int N;
    static int M;
    public static void main(String[] args) throws Exception {
        //var
        String[] splited;
        int i,j;
        Set<String> keywords = new HashSet<>();

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        splited = br.readLine().split(" ");

        N = Integer.parseInt(splited[0]);
        M = Integer.parseInt(splited[1]);

        for(i = 0; i < N; i++) {
            keywords.add(br.readLine());
        }

        //logic
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(i = 0; i < M; i++) {
            splited = br.readLine().split(",");
            for(String s: splited) {
                keywords.remove(s);
            }
            bw.write(keywords.size()+"\n");
        }

        bw.flush();
    }
}
