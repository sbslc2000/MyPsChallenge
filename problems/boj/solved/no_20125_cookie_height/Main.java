package problems.boj.solved.no_20125_cookie_height;
// import java.util.*;
import java.io.*;

/**
 * 문제 이름 : 쿠키의 신체 측정
 * 링크 : https://www.acmicpc.net/problem/20125
 * 알고리즘 분류 : ?
 * 작성 일시 : 11-26
 * 관련 링크 : 
 */

public class Main {

    static int N;
    static char[][] arr;
    public static void main(String[] args) throws Exception {
        //var
        String input;
        int i,j;

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new char[N][N];

        for(i = 0; i < N; i++) {
            input = br.readLine();
            for(j = 0; j < N; j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        //logic
        int headCol, headRow, legStartRow;
        int leftArm = 0, rightArm = 0, waist = 0, leftLeg = 0, rightLeg = 0;

        //find HEAD
        i = 0;
        while(true) {
            if((headCol = find(arr[i])) != N) {
                headRow = i;
                break;
            }
            i++;
        }

        // System.out.println("headCol = "+headCol);
        // System.out.println("headRow = "+headRow);

        //find arms
        for(i = headCol - 1; i >= 0; i--) {
            if(arr[headRow + 1][i] != '*') {
                leftArm = headCol - 1 - i;
                break;
            } else if (i == 0) {
                leftArm = headCol;
            }
        }

        for(i = headCol + 1; i < N; i++) {
            if(arr[headRow + 1][i] != '*' ) {
                rightArm = i - headCol - 1;
                break;
            } else if (i == N-1) {
                rightArm = N - 1 - headCol;
            }
        }

        //find waist
        i = headRow + 1;
        while(true) {
            if(arr[i][headCol] != '*' || i == N-1) {
                waist = i - headRow - 2;
                legStartRow = i;
                break;
            }
            i++;
        }

        // System.out.println("waist = "+waist);
        // System.out.println("legStartRow = "+legStartRow);

        //find legs

        //left
        for(i = legStartRow; i < N; i++) {
            if(arr[i][headCol-1] != '*') {
                leftLeg = i -  legStartRow;
                break;
            } else if (i == N-1) {
                leftLeg = N - legStartRow;
            }
        }

        //right
        for(i = legStartRow; i < N; i++) {
            // System.out.println("i, headCol + 1: "+i+" "+(headCol+1));
            if(arr[i][headCol+1] != '*') {
                rightLeg = i -  legStartRow;
                break;
            } else if (i == N-1) {
                rightLeg = N - legStartRow;
            }
        }

        //output
        System.out.printf("%d %d\n",headRow+2, headCol+1);
        System.out.printf("%d %d %d %d %d\n",
        leftArm, rightArm, waist, leftLeg, rightLeg);

    }

    static int find(char[] line) {
        int i;
        for(i = 0; i < N; i++) {
            if(line[i] == '*')
                break;
        }

        return i;
    }
}
