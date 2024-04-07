package problems.boj.solved.no_2468_safe_area;

import java.util.*;
import java.io.*;
import java.util.stream.*;

/**
 * 문제 이름 : 안전영역
 * 링크 : https://www.acmicpc.net/problem/2468
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-08
 * 관련 링크 :
 * 
 * 시간 제한 : 1초
 * 메모리 제한 : 128M
 */
public class Main {
    
    static int N;
    static byte[][] map = new byte[100][100];
    static boolean[][] visited = new boolean[100][100];

    static int maxCount = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //read N
        N = Integer.parseInt(br.readLine());

        //set map
        for(int i = 0; i < N; i++) {
            String[] splited = br.readLine().split(" ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Byte.parseByte(splited[j]);
            }
        }

        //logic
        for(int rain = 0 ; rain <= 100; rain++) {
            maxCount = Math.max(calculateSafeArea(rain), maxCount);
            initVisitArr();
        }

        System.out.println(maxCount);
    }

    private static int calculateSafeArea(int rain) {
        int count = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] > rain && !visited[i][j]) {
                    count++;
                    visitSafeArea(i, j, rain);
                }
            }
        }

        return count;
    }

    private static void visitSafeArea(int r, int c, int rain) {
        if(!isInRange(r,c) || visited[r][c] || map[r][c] <= rain) return;

        visited[r][c] = true;

        visitSafeArea(r + 1, c, rain);
        visitSafeArea(r, c + 1, rain);
        visitSafeArea(r - 1, c, rain);
        visitSafeArea(r , c - 1, rain);
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static void initVisitArr() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                visited[i][j] = false;
            }
        }
    }
}
