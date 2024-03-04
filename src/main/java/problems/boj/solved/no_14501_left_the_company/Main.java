package problems.boj.solved.no_14501_left_the_company;

import java.util.*;
import java.io.*;

/**
 * 문제 이름 : 퇴사
 * 링크 : https://www.acmicpc.net/problem/14501
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-03-04
 * 관련 링크 :
 */
public class Main {

    static int[] maxPrices;
    static int N;

    static int[] T;
    static int[] P;


    public static void main(String[] args) throws Exception {
        // var
        String[] splited;
        int i;

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        maxPrices = new int[N + 1];
        T = new int[N + 1];
        P = new int[N + 1];

        for(i = 1; i <= N; i++) {
            splited = br.readLine().split(" ");
            T[i] = Integer.parseInt(splited[0]);
            P[i] = Integer.parseInt(splited[1]);
        }

        br.close();

        //logic
        maxPrices[N] = (T[N] == 1) ? P[N] : 0;

        int select;
        int nonSelect;
        for(i = N - 1; i >= 1; i--) {

            if(i + T[i] == N + 1) {
                select = P[i];
            } else if(i + T[i] < N + 1) {
                select = P[i] + maxPrices[i + T[i]];
            } else {
                select = Integer.MIN_VALUE;
            }

            nonSelect = maxPrices[i+1];

            maxPrices[i] = (select < nonSelect) ? nonSelect : select;
        }

        System.out.println(maxPrices[1]);
    }
}