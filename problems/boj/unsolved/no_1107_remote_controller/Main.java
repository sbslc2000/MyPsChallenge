package problems.boj.unsolved.no_1107_remote_controller;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static boolean[] isBroken = new boolean[10];
    static int channel = 100;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for(int i = 0; i < M; i++) {
            isBroken[sc.nextInt()] = true;
        }

        int nearChannel = findNearChannel();
        System.out.printf("Near Channel : %d\n", nearChannel);

        int a = Math.abs(100 - N);
        int b = Math.abs(nearChannel - N) + calcLength(nearChannel);
        int result = Math.min(a,b);

        System.out.println(result);
    }

    static int calcLength(int n) {
        int count = 1;
        while((n /= 10) != 0) count++;
        return count;
    }

    static int findNearChannel() {
        int tmp = N;
        int upper, lower;
        while(true) {
            if(hasValidInput(tmp) || tmp == 500001) {
                upper = tmp;
                break;
            }

            tmp++;
        }

        System.out.printf("Upper : %d\n", upper);

        tmp = N;
        while(true) {
            if(hasValidInput(tmp) || tmp == -1) {
                lower = tmp;
                break;
            }

            tmp--;
        }

        

        System.out.printf("Lower : %d\n", lower);

        if(upper == 500001 && lower == -1) {
            return 100;
        } else if (upper == 500001) {
            return lower;
        } else if (lower == -1) {
            return upper;
        }

        if(Math.abs(upper - N) < Math.abs(lower - N)) {
            return upper;
        } else {
            return lower;
        }
    }

    static boolean hasValidInput(int n) {
        while(true) {
            // System.out.println(n);
            if(n == 0) {
                return true;
            }

            if(isBroken[n % 10]) {
                return false;
            }

            n /= 10;
        }
    }
}
