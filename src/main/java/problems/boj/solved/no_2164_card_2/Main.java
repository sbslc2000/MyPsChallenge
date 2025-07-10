package problems.boj.solved.no_2164_card_2;

import java.util.*;

/**
 * 카드 2
 * https://www.acmicpc.net/problem/2164
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        List<Integer> l = new LinkedList<>();

        for (int i = 1 ; i <= N; i++) {
            l.add(i);
        }  


        boolean round = true;
        while(l.size() != 1) {

            if (round) {
                l.remove(0);
            } else {
                Integer t = l.remove(0);
                l.add(t);
            }

            round = !round;
        }

        System.out.println(l.get(0));
        sc.close();
    }
    
}
