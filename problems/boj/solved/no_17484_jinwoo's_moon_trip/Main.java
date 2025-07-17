import java.util.*;

/**
 * 진우의 달 여행(small)
 * https://www.acmicpc.net/problem/17484
 */
public class Main {

    static int N, M;
    static int[][] map;
    static int min = Integer.MAX_VALUE;

    static boolean isInRange(int c) { 
        return c >= 0 && c < M;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < M; i++) {
            find(0, i, -2, 0);
        }

        System.out.println(min);

        sc.close();
    }

    /**
     * before : -1 => 좌측 상단에서 온 경우
     * before : 0 => 상단에서 온 경우
     * before : 1 => 우측 상단에서 온 경우
     * before : -2 => 시작 지점
     */
    private static void find(int pR, int pC, int before, int cost) {

        if (!isInRange(pC)) {
            return;
        }

        if (pR == N) {
            min = Math.min(min, cost);
            return;
        }

        int newCost = cost + map[pR][pC];

        if (before == -1) {
            find(pR + 1, pC, 0, newCost);
            find(pR + 1, pC - 1, 1, newCost);
        } else if (before == 0) {
            find(pR + 1, pC - 1, 1, newCost);
            find(pR + 1, pC + 1, -1, newCost);
        } else if (before == 1) {
            find(pR + 1, pC + 1, -1, newCost);
            find(pR + 1, pC, 0, newCost);
        } else {
            find(pR + 1, pC + 1, -1, newCost);
            find(pR + 1, pC, 0, newCost);
            find(pR + 1, pC - 1, 1, newCost);
        }
    }
}
