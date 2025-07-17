package problems.boj.solved.no_9012_bracket;

import java.io.*;
import java.util.stream.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            System.out.println(isVPS(br.readLine()) ? "YES" : "NO");
        }
    }

    private static boolean isVPS(String line) {
        int v = 0;

        for(int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == ')') {
                v--;
                if(v < 0) {
                    return false;
                }

            } else if (line.charAt(i) == '(') {
                v++;
            }

        }

        if(v != 0) {
            return false;
        }

        return true;
    }
}
