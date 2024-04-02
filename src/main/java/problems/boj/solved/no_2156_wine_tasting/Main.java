package problems.boj.solved.no_2156_wine_tasting;

import java.io.*;

/**
 * 문제 이름 : 포도주 시식
 * 링크 : https://www.acmicpc.net/problem/2156
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-01
 * 관련 링크 : 
 */
public class Main {

    static int[] cache = new int[10001];
    static int[] wines = new int[10001];
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 1; i <= N ; i++) {
            wines[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(getMaxWineAmount(0));
    }

    /**
     * idx가 주어졌을 때,
     * idx는 항상 시음할 수 있으며,
     * idx를 시음한다는 가정하에
     * idx 이후의 포도주를 시음하는 경우의 수 중
     * 가장 큰 값을 반환하라
     */
    private static int getMaxWineAmount(int idx) {
        if(!isInRange(idx)) {
            return 0;
        }

        if(cache[idx] != 0) {
            return cache[idx];
        }
        
        if(idx == N) {
            cache[idx] = wines[N];
            return cache[idx];
        }

        // if(idx == N - 1) {
        //     cache[idx] = wines[N - 1];
        //     return cache[idx];
        // }

        int a = wines[idx] + wines[idx + 1] + getMaxWineAmount(idx+3);
        int b = wines[idx] + getMaxWineAmount(idx + 2);
        int c = wines[idx] + getMaxWineAmount(idx + 3);
        int d = wines[idx] + wines[idx + 1] + getMaxWineAmount(idx + 4);


        return cache[idx] = Math.max(a, Math.max(b,Math.max(c,d)));
    }


    private static boolean isInRange(int n) {
        return n <= N;
    }
}
