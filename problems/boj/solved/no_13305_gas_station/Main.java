package problems.boj.solved.no_13305_gas_station;

import java.io.*;

/**
 * 주유소
 * https://www.acmicpc.net/workbook/create
 */
public class Main {

    static int N;
    static long[] dists;
    static long[] prices;
    

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String splited[];

        int N = Integer.parseInt(br.readLine());

        /*
         * dist[n] => distance from city n to city n+1
         */
        dists = new long[N - 1];
        prices = new long[N];


        splited = br.readLine().split(" ");
        for(int i = 0; i < splited.length; i++) {
            dists[i] = Integer.parseInt(splited[i]);
        }

        splited = br.readLine().split(" ");
        for (int i = 0; i < splited.length; i++) {
            prices[i] = Integer.parseInt(splited[i]);
        }

        long currentPrice = prices[0];
        long total = 0;
        int p = 0;

        while(p <= N - 2) {
            total += currentPrice * dists[p];

            if (prices[p + 1] < currentPrice) {
                currentPrice = prices[p + 1];
            }

            p++;
        }

        System.out.println(total);
    }
    
}



