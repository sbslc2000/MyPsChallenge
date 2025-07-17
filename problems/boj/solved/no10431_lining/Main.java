package problems.boj.solved.no10431_lining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

/**
 * 문제 이름 : 줄세우기
 * 링크 : https://www.acmicpc.net/problem/10431
 * 알고리즘 분류 : 구현, 시뮬레이션
 * 작성 일시 : 2023-11-20
 * 관련 링크 :
 * 
 * 메모리 제한 : 256MB
 * 시간 제한 : 1초
 * 
 * 시간 제한이 넉넉해서, Insertion Sort를 하며 SWAP 횟수를 세면 될 것 같다.
 * LinkedList를 사용하면 SWAP에 필요한 시간을 줄일 수 있을 것 같다.
 */
public class Main {

    static int N;
    static int[][] arr;
    static int[] res;

    public static void main(String[] args) throws IOException {
        int i,j;

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][20];
        res = new int[N];

       
        for(i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken(); // 테스트 케이스 번호 버림

            j = 0;
            while(st.hasMoreTokens()) {
                arr[i][j++] = Integer.parseInt(st.nextToken());
            }
        }
      
        br.close();

        //logic
        for(int n = 0; n < N; n++) {
            for(i = 0; i < 20; i++) {
                for(j = i; j > 0; j--) {
                    if(arr[n][j] < arr[n][j-1]) {
                        //swap without temp
                        arr[n][j] = arr[n][j] + arr[n][j-1];
                        arr[n][j-1] = arr[n][j] - arr[n][j-1];
                        arr[n][j] = arr[n][j] - arr[n][j-1];

                        res[n]++;
                    } else {
                        break;
                    }
                }
            }
        }

        //output
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(i = 0; i < N; i++) {
            bw.write((i+1)+" "+res[i]+"\n");
        }

        bw.flush();
        bw.close();
    }
}
