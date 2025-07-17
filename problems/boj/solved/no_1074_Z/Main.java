package problems.boj.solved.no_1074_Z;

import java.util.*;
/**
 * 문제 이름 : Z
 * 링크 : https://www.acmicpc.net/problem/1074
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-03-18
 * 관련 링크 : 
 */
public class Main {

    static class Pair {
        int a;
        int b;

        static Pair of(int a, int b) {
            Pair p = new Pair();
            p.a = a;
            p.b = b;
            return p;
        }
        
    }
     
    static int N;
    static int r;
    static int c;

    static Pair candidate;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
        sc.close();

        int powerOfN = (int)Math.pow(2, N);

        candidate = Pair.of(0, (int)Math.pow(powerOfN, 2) - 1);

        Pair rowBound = Pair.of(0, powerOfN - 1);
        Pair colBound = Pair.of(0, powerOfN - 1);

        int tr, tc;
        while(candidate.a != candidate.b) {
            tr = (rowBound.a + rowBound.b) / 2;
            tc = (colBound.a + colBound.b) / 2;

            if (r <= tr && c <= tc) {
                candidate = Pair.of(candidate.a, get1Q() - 1);
                rowBound = Pair.of(rowBound.a, (rowBound.a + rowBound.b) / 2);
                colBound = Pair.of(colBound.a, (colBound.a + colBound.b) / 2);
            } else if (r <= tr && c > tc) {
                candidate = Pair.of(get1Q(), get2Q() - 1);
                rowBound = Pair.of(rowBound.a, (rowBound.a + rowBound.b) / 2);
                colBound = Pair.of((colBound.a + colBound.b) / 2 + 1, colBound.b);
            } else if (r > tr && c <= tc) {
                candidate = Pair.of(get2Q(), get3Q() - 1);
                rowBound = Pair.of((rowBound.a + rowBound.b) / 2 + 1, rowBound.b);
                colBound = Pair.of(colBound.a, (colBound.a + colBound.b) / 2);
            } else {
                candidate = Pair.of(get3Q(), candidate.b);
                rowBound = Pair.of((rowBound.a + rowBound.b) / 2 + 1, rowBound.b);
                colBound = Pair.of((colBound.a + colBound.b) / 2 + 1, colBound.b);
            }
        }

        System.out.println(candidate.a);
    }

    private static int get1Q() {
        int qVal = (candidate.b - candidate.a) / 4 + 1;
        return candidate.a + qVal;
    }

    private static int get2Q() {
        return (candidate.a + candidate.b) / 2 + 1;
    }

    private static int get3Q() {
        int qVal = (candidate.b - candidate.a) / 4 + 1;
        return candidate.b - qVal + 1;
    }
}
