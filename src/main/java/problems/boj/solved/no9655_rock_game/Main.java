package problems.boj.solved.no9655_rock_game;

import java.util.*;

/**
 * 문제 이름 : 돌 게임
 * 링크 : https://www.acmicpc.net/problem/9655
 * 알고리즘 분류 : ?
 * 작성 일시 : 2023-11-19
 * 관련 링크 :
 * 
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();
        System.out.println(N == 2 || N % 2 == 1 ? "SK" : "CY");

    }
}