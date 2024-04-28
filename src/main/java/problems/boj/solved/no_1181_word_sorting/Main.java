package problems.boj.solved.no_1181_word_sorting;

import java.io.*; 
import java.util.*;

/**
 * 문제 이름 : 단어 정렬
 * 링크 : https://www.acmicpc.net/problem/1181
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-28
 * 관련 링크 : 
 */
public class Main {

    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<String> words = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            words.add(br.readLine());
        }

        words.stream()
            .sorted((a, b) -> {
                int diff = a.length() - b.length();
                if(diff != 0) return diff;
                return a.compareTo(b);
            })
            .distinct()
            .forEach(System.out::println);
    }
}
