package problems.boj.solved.no_3107_IPv6;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 문제 이름 : IPv6
 * 링크 : https://www.acmicpc.net/problem/3107
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-05-01
 * 관련 링크 :
 * 
 * 시간 제한 : 1초
 * 메모리 제한 : 128M
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        // System.out.println(input);

        List<String> shorts = Arrays.stream(input.split(":",-1))
            .collect(Collectors.toList());

        // System.out.println(shorts.size());

        for(int i = 0; i < shorts.size(); i++) {
            if(shorts.get(i).equals("") && shorts.get(i+1).equals("")) {
                shorts.remove(i);
            }
        }

        // System.out.println(shorts);

        StringBuilder sb = new StringBuilder();

        for(String s : shorts) {

            if(s.equals("")) {
                for(int i = shorts.size(); i <= 8; i++) {
                    sb.append("0000:");
                }
                continue;
            }

            for(int i = s.length(); i < 4; i++) {
                sb.append("0");
            }

            sb.append(s);
            sb.append(":");
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }
}
