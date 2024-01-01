package problems.boj.solved.no_2439_star_2;

import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        StringBuilder sb;

        for(int i = 1; i <= N; i++) {
            sb = new StringBuilder();

            for(int j = 0; j < N - i; j++) {
                sb.append(" ");
            }
            for(int j = 0; j < i ; j++) {
                sb.append("*");
            }

            System.out.println(sb.toString());
        }
    }
}
