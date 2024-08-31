package problems.boj.solved.no_12919_AandB2;
import java.io.*;
import java.util.*;

public class Main {

    static String S;
    static String T;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<String> q = new LinkedList<>();

        S = br.readLine();
        T = br.readLine();

        int sLength = S.length();

        q.add(T);

        while(!q.isEmpty()) {
            String s = q.poll();
            // System.out.println(s);

            if (s.length() == sLength) {
                if (s.equals(S)) {
                    System.out.println(1);
                    return;
                }
                continue;
            }

            if (s.charAt(0) == 'B') {
                // System.out.println("B is first");
                String substr = s.substring(1, s.length());
                q.add(new StringBuilder(substr).reverse().toString());
            }

            if (s.charAt(s.length() - 1) == 'A') {
                // System.out.println("A is end");
                q.add(s.substring(0, s.length() - 1));
            }
        }

        System.out.println(0);
    }

}