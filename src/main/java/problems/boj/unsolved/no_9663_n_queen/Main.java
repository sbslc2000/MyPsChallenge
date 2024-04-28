package problems.boj.unsolved.no_9663_n_queen;

import java.util.*;

/**
 * 문제 이름 : N-Queen
 * 링크 : https://www.acmicpc.net/problem/9663
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-22
 * 관련 링크 : 
 */
public class Main {

    static int N;
    static int count = 0;
    

    static int[][] board;

    public static void main(String[] args) {
        //input
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        if(N == 1) {
            System.out.println(1);
            return;
        }

        board = new int[N][N];

        //logic
        search(-1, N);

        System.out.println(count);
    }

    static void search(int selected, int left) {
     
        System.out.printf("selected : %d, left : %d\n", selected, left);
        if(left == 0) {
            count++;
            return;
        }

        //0 ~ 9
        //10 ~ 19

        for(int i = selected + 1; i < N * N; i++) {
            if(placeable(i)) {
                place(i);
                search(((i + N) / N) * N, left - 1);
                unplace(i);
            } 
        }
    }

    static void place(int point) {

        int x = point / N;
        int y = point % N;
        System.out.printf("Place at %d %d\n", x, y);

        for(int i = 0; i < N; i++) {
            board[x][i]++;
        }

        for(int i = 0; i < N; i++) {
            board[i][y]++;
        }

        // printBoard();

        int tx, ty;
        tx = x;
        ty = y;
        //좌상단
        while(isInRange(tx, ty)) {
            board[tx][ty]++;
            tx -= 1;
            ty -= 1;
        }

        //우상단
        tx = x;
        ty = y;
        while(isInRange(tx, ty)) {
            board[tx][ty]++;
            tx -= 1;
            ty += 1;
        }

        //좌하단
        tx = x;
        ty = y;
        while(isInRange(tx, ty)) {
            board[tx][ty]++;
            tx += 1;
            ty -= 1;
        }

        tx = x;
        ty = y;
        while(isInRange(tx, ty)) {
            board[tx][ty]++;
            tx += 1;
            ty += 1;
        }

        // printBoard();
    }

    static void unplace(int point) {

        int x = point / N;
        int y = point % N;
        System.out.printf("Unplace at %d %d\n", x, y);

        for(int i = 0; i < N; i++) {
            board[x][i]--;
        }

        for(int i = 0; i < N; i++) {
            board[i][y]--;
        }

        int tx, ty;
        tx = x;
        ty = y;
        //좌상단
        while(isInRange(tx, ty)) {
            board[tx][ty]--;
            tx -= 1;
            ty -= 1;
        }

        //우상단
        tx = x;
        ty = y;
        while(isInRange(tx, ty)) {
            board[tx][ty]--;
            tx -= 1;
            ty += 1;
        }

        //좌하단
        tx = x;
        ty = y;
        while(isInRange(tx, ty)) {
            board[tx][ty]--;
            tx += 1;
            ty -= 1;
        }

        tx = x;
        ty = y;
        while(isInRange(tx, ty)) {
            board[tx][ty]--;
            tx += 1;
            ty += 1;
        }

        // printBoard();
    }


    static boolean placeable(int point) {
        int x = point / N;
        int y = point % N;

        return board[x][y] == 0;
    }

    static boolean isInRange(int point) {
        return point >= 0 && point < N * N;
    }

    static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void printBoard() {
        // for(int i = 0; i < N; i++) {
        //     for(int j = 0; j < N; j++) {
        //         System.out.print(board[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }



}
