package problems.boj.solved.no_9465_sticker;

import java.util.*;
import java.io.*;

/**
 * 문제 이름 : 스티커
 * 링크 : https://www.acmicpc.net/problem/9465
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-22
 * 관련 링크 : 
 */
public class Main {

    static int T;
    static int N;
    static int[][] cache;
    static int[][] stickers;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            stickers = new int[2][N];
            cache = new int[3][N];

            stickers[0] = readIntegerArray(br);
            stickers[1] = readIntegerArray(br);

            cache[0][0] = stickers[0][0];
            cache[1][0] = stickers[1][0];
            cache[2][0] = 0;

            for(int j = 1; j < N; j++) {
                //위에거 선택
                cache[0][j] = Math.max(cache[1][j-1], cache[2][j-1]) + stickers[0][j];
                //아래거 선택
                cache[1][j] = Math.max(cache[0][j-1], cache[2][j-1]) + stickers[1][j];
                //노 선택
                cache[2][j] = Math.max(cache[0][j-1], Math.max(cache[1][j-1], cache[2][j-1]));
            }

            System.out.println(Math.max(cache[0][N-1], Math.max(cache[1][N-1], cache[2][N-1])));
        }

        
    }

    private static int[] readIntegerArray(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }


    
}
