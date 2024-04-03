package problems.boj.solved.no_number_board_jump;

import java.util.*;
import java.io.*;

/**
 * 문제 이름 : 단어 공부
 * 링크 : https://www.acmicpc.net/problem/2210
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-03
 * 관련 링크 :
 */
public class Main {
    
    static Byte[][] map = new Byte[5][5];

    static Set<String> s = new HashSet<>();

    static int[] directions = new int[]{1,0, -1,0, 0,1, 0,-1};

    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i = 0; i < 5; i++) {
            String[] splited = br.readLine().split(" ");
            for(int j = 0; j < 5; j++) {
                map[i][j] = Byte.parseByte(splited[j]);
            }
        }

        //logic
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                search(i, j, 0, "");
                // System.out.printf("search for %d,%d\n",i,j);
            }
        }

        // System.out.println(s);

        System.out.println(s.size());
    }

    private static void search(int r, int c, int depth, String prevDigits) {
        String digits = prevDigits + Byte.toString(map[r][c]);

        if(depth == 5) {
            s.add(digits);
            return;
        }

        for(int d = 0; d < 4; d++) {
            int dx = directions[d * 2];
            int dy = directions[d * 2 + 1];
            // System.out.printf("%d %d\n", dx, dy);
            
            if(isInRange(r + dx, c + dy)) {
                search(r + dx, c + dy, depth + 1, digits);
            }
        }
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }
}
