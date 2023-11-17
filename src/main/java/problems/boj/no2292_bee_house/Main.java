package problems.boj.no2292_bee_house;

import java.util.Scanner;

/**
 * 문제 이름 : 벌집
 * 링크 : https://www.acmicpc.net/problem/2292
 * 알고리즘 분류 : ?
 * 작성 일시 : 2023-11-16
 * 관련 링크 :
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        int i = 1;
        int loc = 1;
        while (true) {

            if (N <= loc) {
                System.out.println(i);
                break;
            }

            loc += 6 * i++;
        }
    }
}