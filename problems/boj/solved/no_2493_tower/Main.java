package problems.boj.solved.no_2493_tower;

import java.util.*;
import java.io.*;

/**
 * 문제 이름 : 탑
 * 링크 : https://www.acmicpc.net/problem/2493
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-09-05
 * 관련 링크 :
 * 
 * 시간 제한 : 1.5초
 * 메모리 제한 : 128M
 * 
 * n에 출력되어야 하는 값은
 * n보다 작은 인덱스 중 arr[n]보다 크며
 * 가장 n이 큰 경우
 * 
 * --> 이중 정렬해서 풀까?
 * 1. n보다 큰 인덱스는 스캔할 필요 없음.
 * 2. arr[n] 보다 작은 값은 스캔할 필요 없음.
 * DB에서 이중 인덱스 관리법으로 해결할 수 있을 듯
 *  
 * 
 */
public class Main {

    static int N;
    static int[] BASE = new int[]{0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<int[]> stack = new Stack<>();

        int idx = 0;
        while(st.hasMoreTokens()) {
            idx++;
            int length = Integer.parseInt(st.nextToken());

            int peeked[];
            while(true) {
                if (stack.isEmpty()) {
                    // System.out.printf("%d ", 0);
                    stack.add(new int[]{idx, length});
                    bw.write('0');
                    bw.write(' ');
                    break;
                }

                peeked = stack.peek();

                if(peeked[1] < length) {
                    stack.pop();
                } else {
                    // System.out.printf("%d ", peeked[0]);
                    bw.write(String.valueOf(peeked[0]));
                    bw.write(' ');
                    stack.push(new int[]{idx, length});
                    break;
                }
            }

          

        }    
        
        bw.flush();
    }
}
