package problems.boj.solved.no_3184_sheep;

import java.util.*;
import java.io.*;

/**
 * 문제 이름 : 양
 * 링크 : https://www.acmicpc.net/problem/3184
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-18
 * 관련 링크 :
 * 시간 제한 : 1초
 * 메모리 제한 : 128M
 */
public class Main {

    static int R;
    static int C;

    static char[][] map;
    static boolean[][] visited;

    enum Direction {
        UP(-1, 0),
        DOWN(1, 0),
        RIGHT(0, 1),
        LEFT(0, -1);

        int dr;
        int dc;

        Direction(int dr, int dc) {
            this.dr = dr;
            this.dc = dc;
        }
    }

    private static class AreaCount {
        int sheep = 0;
        int wolves = 0;


    }

    public static void main(String[] args) throws Exception {
        //var
        String input;
        String[] splited;

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        splited = br.readLine().split(" ");

        R = Integer.parseInt(splited[0]);
        C = Integer.parseInt(splited[1]);

        map = new char[R][C];
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            input = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        //logic
        int sheep = 0;
        int wolves = 0;

        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if(!visited[r][c]) {
                    AreaCount area = new AreaCount();
                    searchArea(r, c, area);

                    if(area.sheep > area.wolves) {
                        sheep += area.sheep;
                    } else {
                        wolves += area.wolves;
                    }
                }
            }
        }
        
        //output
        System.out.printf("%d %d\n", sheep, wolves);
    }

    private static void searchArea(int r, int c, AreaCount area) {
        visited[r][c] = true;

        if(isSheep(r, c)) {
            area.sheep++;
        } 

        if(isWolf(r, c)) {
            area.wolves++;
        }

        for(Direction d : Direction.values()) {
            int newR = r + d.dr;
            int newC = c + d.dc;

            if(isInRange(newR, newC) && !visited[newR][newC] && !isFence(newR, newC)) {
                searchArea(newR, newC, area);
            }
        }
    }

    private static boolean isSheep(int r, int c) {
        return map[r][c] == 'o';
    }

    private static boolean isWolf(int r, int c) {
        return map[r][c] == 'v';
    }

    private static boolean isFence(int r, int c) {
        return map[r][c] == '#';
    }

    private static boolean isInRange(int r, int c) {
        return r < R && r >= 0 && c < C && c >= 0;
    }
    
}
