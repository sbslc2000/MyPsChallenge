package problems.boj.solved.no_2869_snail_want_to_go_up;

import java.util.*;


public class Main {

    static int V;
    static int A;
    static int B;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        V = sc.nextInt();
        System.out.println((int)Math.ceil((double)(V - B) / (A - B)));
    }
    
}
