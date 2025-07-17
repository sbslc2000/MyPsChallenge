package problems.boj.solved.no_15829_hashing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 문제 이름 : 해싱
 * 링크 : https://www.acmicpc.net/problem/15829
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-06-17
 * 관련 링크 :
 * 
 * 메모리 제한 : 512MB
 * 시간 제한 : 1초
 */
public class Main {

    static int L;
    static String read;
    static char[] arr;
    static int r = 31;
    static int M = 1234567891;
    static int H;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        L = Integer.parseInt(br.readLine());
        read = br.readLine();

        arr = read.toCharArray();

        int n = 1;
        for (int i = 0 ; i < L; i++) { // r^0, r^1, r^2
            int a = arr[i] - 'a' + 1;
            H += a * n;
            n *= r;
        }

        H %= M;

        System.out.println(H);
    }
}
