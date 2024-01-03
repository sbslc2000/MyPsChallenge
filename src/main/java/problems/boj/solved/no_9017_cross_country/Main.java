package problems.boj.solved.no_9017_cross_country;

import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * 문제 이름 : 크로스컨트리
 * 링크 : https://www.acmicpc.net/problem/9017
 * 알고리즘 분류 : ?
 * 작성 일시 : 01-03
 * 관련 링크 : 
 */
public class Main {

    static int T;
    static final int MAX_TEAM_SIZE = 200;

    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        int N;
        int[] count = null;
        char[] sumCount = null;
        int[] pointSum = null;
        int[] fifthVal = null;

        for(int t = 0; t < T; t++) {

            count = new int[MAX_TEAM_SIZE + 1];
            sumCount = new char[MAX_TEAM_SIZE + 1];
            pointSum = new int[MAX_TEAM_SIZE + 1];
            fifthVal = new int[MAX_TEAM_SIZE + 1];

            N = Integer.parseInt(br.readLine());
            List<Integer> data = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

            //count
            //val is range in 1 ~ 200
            for(int val: data) {
                count[val]++;
            }

            //calculate point
            int p;
            int point = 1;
            for(int n : data) {
                
                if(count[n] < 6) {
                    continue;
                }

                if(sumCount[n] < 4) {
                    pointSum[n] += point++;
                } else if (sumCount[n] == 4) {
                    fifthVal[n] = point++;
                } else {
                    point++;
                }

                sumCount[n]++;
            }

            //check winner
            int least = Integer.MAX_VALUE, winner = 0;
            p = 1;
            while(true) {

                if(p > 200 || count[p] == 0) {
                    break;
                } else if (count[p] != 6) {
                    p++;
                    continue;
                }

                if(pointSum[p] < least) {
                    least = pointSum[p];
                    winner = p;

                } else if (pointSum[p] == least) {
                    if(fifthVal[winner] > fifthVal[p]) {
                        winner = p;
                    }
                }

                p++;
            }

            System.out.println(winner);
        }
    
        br.close();
    }
}
