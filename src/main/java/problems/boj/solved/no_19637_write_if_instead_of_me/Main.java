package problems.boj.solved.no_19637_write_if_instead_of_me;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 : IF문 좀 대신 써줘
 * 링크 : https://www.acmicpc.net/problem/19637
 * 알고리즘 분류 : ?
 * 작성 일시 : 11-25
 * 관련 링크 : 
 */
public class Main {

    static String[] titles;
    static int[] upperBounds;
    static int[] powers;

    static int N;
    static int M;
    public static void main(String[] args) throws Exception {
        //var
        int i;
        String temp;
        String[] splited;

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        splited = br.readLine().split(" ");

        N = Integer.parseInt(splited[0]);
        M = Integer.parseInt(splited[1]);

        titles = new String[N];
        upperBounds = new int[N];

        for(i = 0; i < N; i++) {
            splited = br.readLine().split(" ");
            titles[i] = splited[0];
            upperBounds[i] = Integer.parseInt(splited[1]);
        }


        powers = new int[M];
        for(i = 0; i < M; i++) {
            powers[i] = Integer.parseInt(br.readLine());
        }

        br.close();

        //logic
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(i = 0; i < M; i++) {
            bw.write(findTitle(powers[i])+"\n");
        }

        bw.flush();
        bw.close();
    }

    static String findTitle(int power) {
        // System.out.println("power: "+ power);
        int left = 0, right = N;
        int p;

        while(left < right) {
            p = (left + right) / 2;
            // System.out.printf("%d %d %d\n", left, right, p);
         
            if(power <= upperBounds[p]) {
                right = p;
            } else {
                left = p+1;
            }
        }

        // System.out.printf("%d %d\n", left, right);
        if(left >= N)
            left--;
        return titles[left];
    }
}
