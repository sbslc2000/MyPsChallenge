package problems.boj.solved.no25757_mini_game;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * 문제 이름 : 임스와 함께하는 미니게임
 * 링크 : https://www.acmicpc.net/problem/25757
 * 알고리즘 분류 : ?
 * 작성 일시 : 2023-11-23
 * 관련 링크 : 
 * 
 * 메모리 제한 :  128 MB
 * 시간 제한 : 1초
 * 
 * distinct는 어떻게 구현하는가?
 */
public class Main {
    
    static int N;
    static char GAME;
    public static void main(String[] args) throws Exception{
        //var
        Set<String> set = new HashSet<>();
        int i;
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited = br.readLine().split(" ");

        N = Integer.parseInt(splited[0]);
        GAME = splited[1].charAt(0);

        //logic
        for(i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        //output
        int size = set.size();

        switch(GAME) {
            case 'Y': System.out.println(size); break;
            case 'F': System.out.println(size/2); break;
            default : System.out.println(size/3);
        }
    }
}
