package problems.boj.solved.no_1629_multiplication;

import java.util.*;
import java.lang.Math;

public class Main {
    
    static int A;
    static int B;
    static int C;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();

        System.out.println((int)(Math.pow(A, B) % C));
    }
}
