package problems.boj.solved.no5073_triangle_edge;

import java.util.Scanner;

/**
 * 문제 이름 : 삼각형과 세 변
 * 링크 : https://www.acmicpc.net/problem/5073
 * 알고리즘 분류 : ?
 * 작성 일시 : 2023-11-15
 * 관련 링크 :
 */
public class Main {

    private static final String EQUI = "Equilateral";
    private static final String ISO = "Isosceles";
    private static final String SCAL = "Scalene";
    private static final String INVALID = "Invalid";

    public static void main(String[] args) {
        int a, b, c;
        Scanner sc = new Scanner(System.in);

        while (true) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();

            if (a == 0)
                break;

            if (a < b) {
                a = a + b;
                b = a - b;
                a = a - b;
            }

            if (b < c) {
                b = b + c;
                c = b - c;
                b = b - c;
            }

            if (a < b) {
                b = a - b;
                a = a - b;
            }

            if (a >= b + c) {
                System.out.println(INVALID);
            } else if (a == b && b == c) {
                System.out.println(EQUI);
            } else if (a == b || b == c) {
                System.out.println(ISO);
            } else {
                System.out.println(SCAL);
            }
        }
        sc.close();
    }
}