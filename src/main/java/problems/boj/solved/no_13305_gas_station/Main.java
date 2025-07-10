package problems.boj.solved.no_13305_gas_station;

import java.io.*;
import java.util.*;

/**
 * 주유소
 * https://www.acmicpc.net/workbook/create
 */
public class Main {

    static int N;
    static long[] dists;
    static long[] prices;

    static long[] caches;
    

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String splited[];

        int N = Integer.parseInt(br.readLine());

        /*
         * dist[n] => distance from city n to city n+1
         */
        dists = new long[N - 1];
        prices = new long[N];

        caches = new long[N];

        splited = br.readLine().split(" ");
        for(int i = 0; i < splited.length; i++) {
            dists[i] = Integer.parseInt(splited[i]);
        }

        splited = br.readLine().split(" ");
        for (int i = 0; i < splited.length; i++) {
            prices[i] = Integer.parseInt(splited[i]);
        }

        caches[N - 2] = dists[N - 2] * prices[N - 2];
        long totalDist = dists[N - 2];

        for (int i = N - 3; i >= 0; i--) {
            totalDist += dists[i];

            long a = totalDist * prices[i];
            long b = prices[i] * dists[i] + caches[i + 1];

            caches[i] = Math.min(a, b);
        }

        System.out.println(caches[0]);
    }
    
}



