package problems.boj.solved.no_19941_hamburger;

import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static boolean[] hamburgers;

    static boolean isHamburger(int idx) {
        return hamburgers[idx];
    }

    static boolean isPerson(int idx) {
        return !hamburgers[idx];
    }

    static boolean isInRange(int idx) {
        return idx >= 0 && idx < N;
    }

    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;

        splited = br.readLine().split(" ");

        N = Integer.parseInt(splited[0]);
        K = Integer.parseInt(splited[1]);

        hamburgers = new boolean[N];

        String input = br.readLine();

        for (int i = 0; i < input.length(); i++) {
            hamburgers[i] = input.charAt(i) == 'H';
        }

        //logic
        int count = 0;
        int h = 0;
        int p = 0;

        while (p < N ) {
            if (isPerson(p)) {
                if (h < p - K) {
                    h = p - K;
                }

                while(isInRange(h) && h <= p + K) {
                    if (isHamburger(h++)) {
                        count++;
                        break;
                    }
                }
            } 

            p++;
        }
        System.out.println(count);
    }
}
