package problems.boj.NO23971_JOAC_4;

import java.util.Scanner;

/**
 * 문제 이름 : ZOAC 4
 * 링크 : https://www.acmicpc.net/problem/23971
 * 알고리즘 분류 : ?
 * 작성 일시 : 2023-11-14
 * 관련 링크 :
 * 1. 백준 자바로 풀 때 유의사항 https://m.blog.naver.com/chltmddus23/221696297647
 */
public class Main {
    public static void main(String[] args) {
        // 5 4 1 1
        int H, W, N, M;

        Scanner scanner = new Scanner(System.in);
        H = scanner.nextInt();
        W = scanner.nextInt();
        N = scanner.nextInt();
        M = scanner.nextInt();

        scanner.close();

        int r,c;
        r = H % (N + 1) > 0 ? H / (N + 1) + 1 : H / (N + 1);
        c = W % (M + 1) > 0 ? W / (M + 1) + 1 : W / (M + 1);

        System.out.println(r*c);
    }
}
