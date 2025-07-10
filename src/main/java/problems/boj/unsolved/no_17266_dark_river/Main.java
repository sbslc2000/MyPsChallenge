import java.util.*;
import java.io.*;


/**
 * 문제 이름 : 어두운 굴다리
 * 링크 : https://www.acmicpc.net/problem/17266  
 * 알고리즘 분류 : ?
 * 작성 일시 : 2025-05-18
 * 관련 링크 :
 * 
 * 메모리 제한 : 256 MB
 * 시간 제한 : 1초
 */
/**
 */
public class Main {

    static int N;
    static int M;
    static int[] points;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;

        N = Integer.parseInt(br.readLine());
    
        M = Integer.parseInt(br.readLine());
        points = new int[M];


        splited = br.readLine().split(" ");

        for (int i = 0; i < M; i++) {
            points[i] = Integer.parseInt(splited[i]);
        }

        int maxDiff = 0;

        for (int i = 0; i < N; i++) {
            System.out.println("Loop "+ i);
            int diff;
            int idx = Arrays.binarySearch(points, i);

            if (idx >= 0) {
                diff = 0;
            } else {
                int invertedIdx = -idx - 1;

                System.out.println("Inverted Index: " + invertedIdx);
                int leftIdx;
                int rightIdx;

                if (invertedIdx < 0) {
                    leftIdx = i;
                } else {
                    leftIdx = invertedIdx;
                }

                int left = points[leftIdx];

                rightIdx = leftIdx == M - 1 ? leftIdx : leftIdx + 1;

                int right = points[rightIdx];

                diff = Math.max(i - left, right - i);
            }

            maxDiff = Math.max(maxDiff, diff);
        }

        System.out.println(maxDiff);
    }

    static boolean isInRange(int point) {
        return point >= 0 && point < N;
    }
}
