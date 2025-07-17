package problems.boj.solved.no_1205_find_rank;
import java.io.*;
import java.util.*;

/**
 * 문제 이름 : 등수 구하기
 * 링크 : https://www.acmicpc.net/problem/1205
 * 알고리즘 분류 : ?
 * 작성 일시 : 11-27
 * 관련 링크 : 
 */
public class Main {
    
    static int N;
    static int newScore;
    static int P;
    static int[] scores;
    public static void main(String[] args) throws Exception {
        //var
        String[] splited;
        int i;

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        splited = br.readLine().split(" ");

        N = Integer.parseInt(splited[0]);
        newScore = Integer.parseInt(splited[1]);
        P = Integer.parseInt(splited[2]);

        scores = new int[N];

        if(N != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            i = 0;
            while(st.hasMoreTokens()) {
                scores[i++] = Integer.parseInt(st.nextToken());
            }
        }
       
        //logic
        int rank = 1;
        if(N == P && scores[N-1] >= newScore) {
            rank = -1;
        } else {
            for(i = 0; i < N; i++) {
                if(scores[i] > newScore) {
                    rank++;
                }
            }
        }

        System.out.println(rank);
    }
}
