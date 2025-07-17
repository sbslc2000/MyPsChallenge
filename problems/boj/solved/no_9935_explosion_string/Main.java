package problems.boj.solved.no_9935_explosion_string;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 : 폭발 문자열
 * 링크 : https://www.acmicpc.net/problem/9935
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-06-09
 * 관련 링크 : 
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String exp = br.readLine();

        
        
        String from = str;
        String to;
        StringBuffer buffer = new StringBuffer();
    
        while(true) {
            int findCount = 0;
            int expIndex = 0;
            StringBuilder toBuilder = new StringBuilder();

            for (int i = 0; i < from.length(); i++) {
                if (from.charAt(i) == exp.charAt(expIndex)) {
                    // System.out.println(toBuilder.toString());
                    System.out.printf("from[%d] and exp[%d] are same!\n", i, expIndex);
                    buffer.append(from.charAt(i));
                    expIndex++;

                    if (expIndex == exp.length()) {
                        System.out.printf("find!\n");
                        findCount++;
                        buffer = new StringBuffer();
                        expIndex = 0;
                    }
                } else {
                    expIndex = 0;
                    toBuilder.append(buffer.toString());
                    buffer = new StringBuffer();
                    toBuilder.append(from.charAt(i));
                }

                System.out.println(toBuilder.toString());
            }

            if (findCount == 0) {
                break;
            }

            from = toBuilder.toString();
            System.out.println(from);
        }

        System.out.println(from);
    }




    // private String explode(String s, String exp) {
    //     int expLength = exp.length();
    //     int i = 0;
    //     while(i < s.length()) {
    //         int findIdx = s.indexOf(exp, i);

    //         s = s.substring(0, findIdx) + s.substring(findIdx + exp.length(), )
    //         if (findIdx == -1) {
    //             break;
    //         }
    //     }
    // }
}
