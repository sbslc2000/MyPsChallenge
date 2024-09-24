package problems.boj.solved.no_16953_AtoB;

import java.util.*;
/**
 * 문제 이름 : A -> B
 * 링크 : https://www.acmicpc.net/problem/16953
 * 알고리즘 분류 : ?
 * 작성 일시 : 2014-09-24
 * 관련 링크 : 
 */
public class Main {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();

        int count = 1;
        while(true) {
            if (B < A) {
                System.out.println(-1);
                break;
            } else if (B == A) {
                System.out.println(count);
                break;
            } else {
                if (B % 10 == 1) {
                    B = B / 10;
                } else if (B % 2 == 0) { 
                    B = B / 2;
                } else {
                    System.out.println(-1);
                    break;
                }
            }

            count++;
        }
   } 
}
