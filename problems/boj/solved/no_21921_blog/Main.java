package problems.boj.solved.no_21921_blog;

import java.io.*;

public class Main {

    static int X, N;
    static int[] visitors;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;

        splited = br.readLine().split(" ");

        N = Integer.parseInt(splited[0]);
        X = Integer.parseInt(splited[1]);

        splited = br.readLine().split(" ");

        visitors = new int[N];

        for (int i = 0; i < splited.length; i++) {
            visitors[i] = Integer.parseInt(splited[i]);
        }

        int wndSum = 0;
        int max = -1;
        int count = 0;

        for (int i = 0; i < X; i++) {
            wndSum += visitors[i];
        }

        max = Math.max(wndSum, max);
        count++;

        for (int i = X; i < N ; i++) {
            wndSum = wndSum -  visitors[i - X] + visitors[i];

            if (wndSum > max) {
                max = wndSum;
                count = 1;
            } else if (wndSum == max) {
                count++;
            } 
        }

        System.out.println(max == 0 ? "SAD" : max + "\n" + count + "\n");
    }
}
