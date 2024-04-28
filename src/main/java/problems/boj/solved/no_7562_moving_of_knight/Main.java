package problems.boj.solved.no_7562_moving_of_knight;

import java.util.*;
import java.io.*;

public class Main {
    
    static int T, L;
    static int knightX, knightY;
    static int targetX, targetY;

    static boolean[][] visited;
    static int[] moves = {-2, -1, -2, 1, -1, -2, 1, -2, 1, 2, -1, 2, 2, 1, 2, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            read(br);
            visited = new boolean[L][L];

            Queue<Integer> q = new LinkedList<>();

            q.add(knightX);
            q.add(knightY);
            q.add(0);
            visited[knightX][knightY] = true;

            while(!q.isEmpty()) {

                int x = q.poll();
                int y = q.poll();
                int count = q.poll();

                if(x == targetX && y == targetY) {
                    System.out.println(count);
                    break;
                }

                for(int i = 0; i < 8; i++) {
                    int dx = moves[i * 2];
                    int dy = moves[i * 2 + 1];

                    int newX = x + dx;
                    int newY = y + dy;
  
                    if(isInRange(newX, newY) && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        q.add(newX);
                        q.add(newY);
                        q.add(count + 1);
                    }
                }
            }
        }

    }

    private static void read(BufferedReader br) throws Exception {
        L = Integer.parseInt(br.readLine());

        String[] splited = br.readLine().split(" ");
        knightX = Integer.parseInt(splited[0]);
        knightY = Integer.parseInt(splited[1]);

        splited = br.readLine().split(" ");
        targetX = Integer.parseInt(splited[0]);
        targetY = Integer.parseInt(splited[1]);
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < L && y >= 0 && y < L;
    }

    private static int readInt(BufferedReader br) throws Exception {
        return Integer.parseInt(br.readLine());
    }
}
