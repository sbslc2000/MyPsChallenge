package problems.boj.unsolved.no_20437_string_game_2;

import java.io.*;

/**
 * 문제 이름 : 문자열 게임
 * 링크 : https://www.acmicpc.net/problem/20437
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-09-01
 * 관련 링크 :
 * 
 * 메모리 제한 : 1024 MB
 * 시간 제한 : 1초
 */
public class Main {

    static int T;
    static String W;
    static int K;
    static int counter[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for(int i = T; i > 0; i--) {
            W = br.readLine();
            K = Integer.parseInt(br.readLine());
            boolean findFirstString = false;
            int longestStringSize = -1;


            for(int length = K; length <= W.length(); length++) {
                if (findFirstString) {
                    break;
                }

                for(int k = 0; k <= W.length() - length - 1; k++) {
                    if (findFirstString) {
                        break;
                    }
                    String substr = W.substring(k, k + length);
                    counter = new int[26];

                    for (int l = 0; l < substr.length(); l++) {
                        char _c = substr.charAt(l);
                        counter[_c - 'a']++;
                    }

                    for (int l = 0; l < counter.length; l++) {
                        if(counter[l] == K) {
                            System.out.printf("%d ",length);
                            // System.out.println(substr);
                            findFirstString = true;
                            break;
                        }
                    }
                }
            }

            for(int length = W.length(); length >= K; length--) {
                for(int k = 0; k <= W.length() - length - 1; k++) {
                    String substr = W.substring(k, k + length);

                    counter = new int[26];

                    for (int l = 0; l < substr.length(); l++) {
                        char _c = substr.charAt(l);
                        counter[_c - 'a']++;
                    }

                    for (int l = 0; l < counter.length; l++) {
                        if(counter[l] == K) {
                            if (substr.charAt(0) - 'a' == l && substr.charAt(substr.length() - 1) - 'a' == l) {
                                System.out.println(length);
                                break;
                            }
                        }
                    }
                }
            }
        }
    } 
}
