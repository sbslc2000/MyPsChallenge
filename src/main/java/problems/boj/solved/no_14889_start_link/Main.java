package problems.boj.solved.no_14889_start_link;

import java.util.*;
import java.io.*;

/**
 * 문제 이름 : 스타트와 링크
 * 링크 : https://www.acmicpc.net/problem/14889
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-03-08
 * 관련 링크 :
 * 
 * 메모리 제한 : 512 MB
 * 시간 제한 : 2초
 */
public class Main {

    static int N;
    static int[][] arr = new int[20][20];

    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        br.close();

        // logic

        bf(new ArrayList<Integer>());

        System.out.println(MIN);
    }

    static void bf(List<Integer> selected) {

        if (selected.size() == N / 2) {
            int diff = calcDiff(selected);
            MIN = diff < MIN ? diff : MIN;
            return;
        }

        Integer lastVal = selected.isEmpty() ? -1 : selected.get(selected.size() - 1);

        for (int i = lastVal + 1; i < N; i++) {
            selected.add(i);
            bf(selected);
            selected.remove(selected.size() - 1);
        }
    }

    static int calcDiff(List<Integer> selected) {

        List<Integer> unselected = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (!selected.contains(i)) {
                unselected.add(i);
            }
        }

        return Math.abs(calcScore(selected) - calcScore(unselected));
    }

    private static int calcScore(List<Integer> team) {
        int score = 0;
        for (Integer a : team) {
            for (Integer b : team) {
                score += arr[a][b];
            }
        }

        return score;
    }
}
