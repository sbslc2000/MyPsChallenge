package problems.boj.solved.no_1986_chess;

import java.io.*;
public class Main {

    //0 : Movable, 1 : Unmovable, 2: Queen, 3: Knight, 4 : Pawn
    static int[][] board = new int[1001][1001];
    static int N;
    static int M;

    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;

        splited = br.readLine().split(" ");
        N = Integer.parseInt(splited[0]);
        M = Integer.parseInt(splited[1]);

        splited = br.readLine().split(" ");
        for(int i = 0; i < Integer.parseInt(splited[0]); i++) {
            int r = Integer.parseInt(splited[i * 2 + 1]);
            int c = Integer.parseInt(splited[i * 2 + 2]);

            board[r][c] = 2;
        }

        splited = br.readLine().split(" ");
        for(int i = 0; i < Integer.parseInt(splited[0]); i++) {
            int r = Integer.parseInt(splited[i * 2 + 1]);
            int c = Integer.parseInt(splited[i * 2 + 2]);

            board[r][c] = 3;
        }

        splited = br.readLine().split(" ");
        for(int i = 0; i < Integer.parseInt(splited[0]); i++) {
            int r = Integer.parseInt(splited[i * 2 + 1]);
            int c = Integer.parseInt(splited[i * 2 + 2]);

            board[r][c] = 4;
        }

        for(int r = 1; r <= N; r++) {
            for(int c = 1; c <= M; c++) {
                switch(board[r][c]) {
                    case 0 : break;
                    case 1 : break;
                    case 4 : break;
                    case 2 : processQueen(r, c); break;
                    case 3 : processKnight(r, c); 
                }
            }
        }

        int count = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(board[i][j] == 0) count++;
            }
        }

        System.out.println(count);
    }

    private static void processQueen(int r, int c) {
        int diff;

        //일직선 4방향
        //위
        diff = 1;
        while(isMoveValid(r - diff, c)) {
            board[r-diff][c] = 1;
            diff++;
        }

        //아래
        diff = 1;
        while(isMoveValid(r + diff, c)) {
            board[r + diff][c] = 1;
            diff++;
        }

        //오른쪽
        diff = 1;
        while(isMoveValid(r, c + diff)) {
            board[r][c + diff] = 1;
            diff++;
        }

        //왼쪽
        diff = 1;
        while(isMoveValid(r, c - diff)) {
            board[r][c - diff] = 1;
            diff++;
        }

        //대각선 4방향
        //오른 위
        diff = 1;
        while(isMoveValid(r + diff, c + diff)) {
            board[r + diff][c + diff] = 1;
            diff++;
        }

        //오른아래
        diff = 1;
        while(isMoveValid(r - diff, c + diff)) {
            board[r - diff][c + diff] = 1;
            diff++;
        }

        //왼위
        diff = 1;
        while(isMoveValid(r + diff, c - diff)) {
            board[r + diff][c - diff] = 1;
            diff++;
        }

        //왼아래
        diff = 1;
        while(isMoveValid(r - diff, c - diff)) {
            board[r - diff][c - diff] = 1;
            diff++;
        }
    }

  

    private static final int[][] KNIGHT_MOVE = {{-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}, {1, -2}, {1, 2}, {2, 1}, {2, -1}};

    private static void processKnight(int r, int c) {
        for(int i = 0; i < KNIGHT_MOVE.length; i++) {
            int tr = r + KNIGHT_MOVE[i][0];
            int tc = c + KNIGHT_MOVE[i][1];

            if(isMoveValid(tr, tc)) {
                board[tr][tc] = 1;
            }
        }
    }

    //이동이 가능한가? (범위안에 있으면서, 막히지 않았다면)
    private static boolean isMoveValid(int r, int c) {
        return isInActiveRange(r, c) && !isBlocked(r, c);
    }

    /**
     * 2, 3, 4는 block으로 처리 (퀸, 나이트, 폰)
     */
    private static boolean isBlocked(int r, int c) {
        int val = board[r][c];
        return val >= 2;
    }

    private static boolean isInActiveRange(int r, int c) {
        boolean res = r > 0 && r <= N && c > 0 && c <= M;
        return res;
    }
}
