package problems.boj.solved.no_14719_rain_water;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 : 빗물
 * 링크 : https://www.acmicpc.net/problem/14719
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-09-06
 * 관련 링크 :
 * 
 */
public class Main {

    static class Pair {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    static int H;
    static int W;
    static Pair[] blocks;
    static Pair[] sortedBlocks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;
        splited =  br.readLine().split(" ");

        H = Integer.parseInt(splited[0]);
        W = Integer.parseInt(splited[1]);

        blocks = new Pair[W];

        splited = br.readLine().split(" ");
        for (int i = 0; i < W; i++) {
            blocks[i] = new Pair(i, Integer.parseInt(splited[i]));
        }

        sortedBlocks = blocks.clone();

        Arrays.sort(sortedBlocks, (a, b) -> b.b - a.b);

        int rainSum = 0;

        for (int i = 1; i < W - 1; i++) {
            rainSum += Math.max(
                Math.min(
                    findLeftMax(i),
                    findRightMax(i)
                ) - blocks[i].b,
                0
            );
        }

        System.out.println(rainSum);
    }

    private static int findLeftMax(int idx) {
        for(int i = 0; i < W; i++) {
            Pair p = sortedBlocks[i];
            if (p.a < idx) {
                return p.b;
            }
        }

        return 0;
    }

    private static int findRightMax(int idx) {
        for(int i = 0; i < W; i++) {
            Pair p = sortedBlocks[i];
            if (p.a > idx) {
                return p.b;
            }
        }

        return 0;
    }
    
}
