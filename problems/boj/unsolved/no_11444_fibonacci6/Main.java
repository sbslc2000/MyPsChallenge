package problems.boj.unsolved.no_11444_fibonacci6;

import java.util.*;

public class Main {

    private static final int MODER = 1_000_000_007;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        if(n == 1) {
            System.out.println(1);
            return;
        }

        int a = 0, b = 1;
        int tmp;

        for(long i = 2; i <= n; i++) {
            tmp = b;
            b = a + b;
            a = tmp;
        }

        System.out.println(b);
    }
}
