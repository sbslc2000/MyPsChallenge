package problems.boj.unsolved.no_14999_dice_spin;

import java.util.*;
import java.io.*;

/**
 * 문제 이름 : 주사위 굴리기
 * 링크 : https://www.acmicpc.net/problem/14496
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-05-08
 * 관련 링크 :
 * 
 * 메모리 제한 : 512 MB
 * 시간 제한 : 2초
 */
public class Main {

    static class Dice {

        public Dice() {
            nums = new int[7];
            // Arrays.fill(nums, 0);

            for(int i = 0; i < 7; i++) {
                nums[i] = 0;
            }
        }

        private int[] nums = new int[6];
  

        public int top() {
            return nums[1];
        }

        public void setBottom(int num) {
            nums[6] = num;
        }

        public int bottom() {
            return nums[6];
        }

        public void turnToEast() {
            //1위치에 4가
            //4위치에 6이
            //6위치에 3이
            //3위치에 1이
            swap(nums, 1, 4);
            swap(nums, 4, 6);
            swap(nums, 6, 3);
        }

        public void turnToWest() {
            for(int i = 0; i < 3; i++) {
                turnToEast();
            }
        }

        public void turnToSouth() {
            swap(nums, 1, 2);
            swap(nums, 2, 6);
            swap(nums, 6, 5);
        }

        public void turnToNorth() {
            for(int i = 0; i < 3; i++) {
                turnToSouth();
            }
        }

        private void swap(int[] nums, int i1, int i2) {
            int tmp = nums[i1];
            nums[i1] = nums[i2];
            nums[i2] = tmp;
        }

        public void print() {
            for(int i = 1; i < 7; i++) {
                System.out.print(nums[i] + " ");
            }
            System.out.println();
        }
    }

    static int N, M, X, Y, K;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited = br.readLine().split(" ");

        N = toInt(splited[0]);
        M = toInt(splited[1]);

        X = toInt(splited[2]);
        Y = toInt(splited[3]);

        K = toInt(splited[4]);

        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        int posX = X;
        int posY = Y;
        
        int[] commands = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        Dice dice = new Dice();

        for(int i = 0; i < K; i++) {
            int direction = commands[i];
            // System.out.printf("Current Position X : %d, Y : %d\n", posX, posY);

            switch(direction) {
                case 1:
                    if(isInRange(posX + 1, posY)) {
                        posX++;
                        dice.turnToEast();
                        exchange(dice, posX, posY);
                        System.out.println(dice.top());
                    }
                    break;
                case 2:
                    if(isInRange(posX - 1, posY)) {
                        posX--;
                        dice.turnToWest();
                        exchange(dice, posX, posY);
                        System.out.println(dice.top());
                    }
                    break;
                case 3:
                    if(isInRange(posX, posY - 1)) {
                        posY--;
                        dice.turnToNorth();
                        exchange(dice, posX, posY);
                        System.out.println(dice.top());
                    }
                    break;
                case 4:
                    if(isInRange(posX, posY + 1)) {
                        posY++;
                        dice.turnToSouth();
                        exchange(dice, posX, posY);
                        System.out.println(dice.top());
                    }
            }
        }
    } 
        


    private static void exchange(Dice dice, int x, int y) {
        if(map[y][x] == 0) {
            map[y][x] = dice.bottom();
        } else {
            dice.setBottom(map[y][x]);
            map[y][x] = 0;
        }
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
