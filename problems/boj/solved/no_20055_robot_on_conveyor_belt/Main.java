package problems.boj.solved.no_20055_robot_on_conveyor_belt;

import java.io.*;

/**
 * 문제 이름 : 컨베이어 벨트 위의 로봇
 * 링크 : https://www.acmicpc.net/problem/20055
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-08-31
 * 관련 링크 :
 * 
 * 메모리 제한 : 512 MB
 * 시간 제한 : 1초
 */
public class Main {

    private static int N;
    private static int K;
    private static int[] durability;
    private static boolean[] robots;


    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String splited[];
        splited = br.readLine().split(" ");

        N = Integer.parseInt(splited[0]);
        K = Integer.parseInt(splited[1]);

        robots = new boolean[2 * N];

        splited = br.readLine().split(" ");
        durability = new int[2 * N];
        for(int i = 0; i < 2 * N; i++) {
            durability[i] = Integer.parseInt(splited[i]);
        }
        

        int cur = 0;
        int round = 0;

        while(true) {
            round++;
            cur = before(cur);
            int unloadPos = (cur + N - 1) % (2 * N);
            // System.out.printf("Round %d: LoadPos is %d, UnloadPos is %d\n", round, cur, unloadPos);

            if (robots[unloadPos]) {
                // System.out.printf("Unload at %d\n", unloadPos);
                robots[unloadPos] = false;
            }


            int s = before(unloadPos);
            while(true) {
                if (s == cur) break;

                if (robots[s]) {
                    int _n = next(s);
                    if (isMovable(_n)) {
                        robots[s] = false;
                        put(_n);

                        if (_n == unloadPos) {
                            robots[_n] = false;
                        }
                    }
                }

                s = before(s);
            }
            
            if (durability[cur] > 0) {
                put(cur);
            }
       
            if (K <=  0) break;
        }

        System.out.println(round);
    }

    private static boolean isMovable(int pos) {
        return !robots[pos] && durability[pos] > 0;
    }

    private static int next(int p) {
        return (p + 1) % (2 * N);
    }

    private static int before(int p) {
        int _before = p - 1;
        return _before == -1 ? 2 * N - 1 : _before;
    }

    private static void put(int at) {
        // System.out.printf("Put robot at %d.\n", at);
        robots[at] = true;
        durability[at]--;
        // System.out.printf("Durability of [%d] is %d\n", at, durability[at]);

        if (durability[at] == 0) {
            K--;
        }
    }
}
