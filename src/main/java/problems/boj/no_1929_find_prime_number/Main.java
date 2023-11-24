package problems.boj.no_1929_find_prime_number;


import java.util.*;

/**
 * 문제 이름 : 소수 구하기
 * 링크 : https://www.acmicpc.net/problem/1929
 * 알고리즘 분류 : ?
 * 작성 일시 : 2023-11-24
 * 관련 링크 : 
 * 
 * 메모리 제한 : 256 MB
 * 시간 제한 : 2초
 * 
 */


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        int N = sc.nextInt();
        sc.close();


        
        for(int i = M; i <= N; i++) {
            if(i == 1) {
                continue;
            }

            if(i == 2) {
                System.out.println(2);
                continue;
            }
            
            if(i % 2 == 0) {
                continue;
            }

            boolean isPrime = true;
            for(int j = 3; j <= Math.sqrt(i); j++) {
                if(i % j == 0) {
                    isPrime = false;
                }
            }

            if(isPrime) 
                System.out.println(i);
        }
    }
}
