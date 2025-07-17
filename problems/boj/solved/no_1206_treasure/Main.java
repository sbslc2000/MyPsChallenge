package problems.boj.solved.no_1206_treasure;

import java.util.*;
import java.io.*;
import java.util.stream.*;

/**
 * 문제 이름 : 보물
 * 링크 : https://www.acmicpc.net/problem/1026
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-03-13
 * 관련 링크 : 
 * 
 * 
 */
public class Main {

    static List<Integer> A;
    static List<Integer> B; 
    static int N; 

    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = Arrays.stream(br.readLine().split(" "))
        .map(Integer::parseInt).collect(Collectors.toList());

        B = Arrays.stream(br.readLine().split(" "))
            .map(Integer::parseInt).collect(Collectors.toList());

        br.close();

        //logic

        Collections.sort(A);
        Collections.sort(B, Collections.reverseOrder());


        int sumproduct = 0;
        for(int i = 0; i < N; i++) {
            sumproduct += A.get(i) * B.get(i);
        }

        System.out.println(sumproduct);
    }
    
}
