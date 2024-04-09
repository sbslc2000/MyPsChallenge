package problems.boj.solved.no_17144_good_bye_small_dust;

import java.util.*;
import java.io.*;

public class Main {

    static int R;
    static int C;
    static int T;

    static enum Direction {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1);


        int dr;
        int dc;

        Direction(int dr, int dc) {
            this.dr = dr;
            this.dc = dc;
        }
    }

    static int[] directions = { -1, 0, 1, 0, 0, -1, 0, 1};

    static int[][] map = new int[1000][1000];
    static int airConditionerR = -1;
    static int airConditionerC = -1;
    
    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;
        splited = br.readLine().split(" ");

        R = Integer.parseInt(splited[0]);
        C = Integer.parseInt(splited[1]);
        T = Integer.parseInt(splited[2]);

        for(int i = 0; i < R; i++) {
            splited = br.readLine().split(" ");
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(splited[j]);

                if(map[i][j] == -1 && airConditionerR == -1) {
                    airConditionerR = i;
                    airConditionerC = j;
                }
            }
        }

        // printMap();
        
        //logic
        for(int i = 0; i < T; i++) {
            // System.out.printf("T : %d\n", i);
            diffuse();
            // System.out.println("After Diffusing...");
            // printMap();
            purify();
            // System.out.println("After conditioning...");
            // printMap();
        }

        int count = 2;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                count += map[i][j];
            }
        }

        System.out.println(count);
    }

    private static void printMap() {
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if(map[r][c] == -1) {
                    System.out.print("  C");
                } else {
                    System.out.printf("%3d", map[r][c]);
                }
            }
            System.out.println();
        }
    }

		
    private static void purify() {
        int tmp;
        tmp = push(0, airConditionerR, airConditionerC + 1, 0, 1);
        tmp = push(tmp, airConditionerR - 1, C - 1, -1, 0);
        tmp = push(tmp, 0, C - 2, 0, -1);
        push(tmp, 1, 0, 1, 0);

        tmp = push(0, airConditionerR + 1, airConditionerC + 1, 0, 1);
        tmp = push(tmp, airConditionerR + 2, C - 1, 1, 0);
        tmp = push(tmp, R - 1, C - 2, 0, -1);
        push(tmp, R - 2, 0, -1, 0);

    }

		/**
     * r,c 부터 시작하여
     * dr, dc 방향으로 배열을 민다.
     * 밀어서 생기는 공백은 input으로 채우며,
     * 밀어서 생기는 나머지는 반환한다.
     */
    private static int push(int input, int r, int c, int dr, int dc) {
        int prev = input;
        int tmp;

        while(isCirculatable(r, c)) {
            // System.out.printf("Process Push [%d][%d]\n", r, c);
            // System.out.printf("PREV: %d\n", prev);
            //swap prev <-> map[r][c];
            tmp = map[r][c];
            map[r][c] = prev;
            prev = tmp;
   
            r += dr;
            c += dc;
        }
        return prev;
    }

    private static boolean isCirculatable(int r, int c) {
        return isInRange(r,c) && !isAirConditioner(r,c);
    }

    private static void diffuse() {
        Queue<Integer> q = new LinkedList<>();
        //find diffuse target
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if(map[r][c] > 0) {
                    q.add(r);
                    q.add(c);
                    q.add(map[r][c]);
                }
            }
        }

        //diffuse
        while(!q.isEmpty()) {
            int r = q.poll();
            int c = q.poll();
            int dust = q.poll();

            int diffuseCount = 0;
            for(int i = 0; i < 4; i++) {
                int dr = directions[2 * i];
                int dc = directions[2 * i + 1];

                if(isDiffusable(r + dr, c + dc)) {
                    diffuseCount++;
                    map[r + dr][c + dc] += dust / 5;
                }
            }

            map[r][c] -= diffuseCount * (dust / 5);
        }
    }

   

    private static boolean isDiffusable(int r, int c) {
        return isInRange(r,c) && !isAirConditioner(r,c);
    }

    private static boolean isAirConditioner(int r, int c) {
        return map[r][c] == -1;
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < R && c >=0 && c < C;
    }
}
