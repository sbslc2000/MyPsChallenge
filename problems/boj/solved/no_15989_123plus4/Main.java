package problems.boj.solved.no_15989_123plus4;
import java.util.*;
import java.io.*;

public class Main {

    static int T;
    static int[] cases;

    static int[][] arr = new int[10001][2];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        cases = new int[T];

        for (int i = 0; i < T; i++) {
            cases[i] = Integer.parseInt(br.readLine());
        } 

        arr[0][0] = 0;
        arr[0][1] = 0;
        arr[1][0] = 1;
        arr[1][1] = 0;
        arr[2][0] = 2;
        arr[2][1] = 2;
        arr[3][0] = 3;
        arr[3][1] = 2;

        for(int i = 4; i <= 10000; i++) {
            arr[i][0] = arr[i - 3][0] + arr[i - 2][1] + 1;
            arr[i][1] = arr[i][0] - arr[i-3][0];
        }

        for (int i = 0; i < T; i++) {
            System.out.println(arr[cases[i]][0]);
        }


    }
}
