import java.io.*;
import java.util.*;

/**
 * 비슷한 단어
 * https://www.acmicpc.net/problem/2607
 */  
public class Main {

    static class Word {
        int length;
        int[] map = new int[26];

        public Word(String s) {
            length = s.length();

            for(int i = 0; i < s.length(); i++) {
                map[(int)(s.charAt(i) - 'A')]++;
            }
        }

        boolean isSimilar(Word w) {
            int diff = 0;

            for(int i = 0; i < 26; i++) {
                diff += Math.abs(this.map[i] - w.map[i]);
            }

            return diff <= 2 && Math.abs(this.length - w.length) <= 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Word> l = new ArrayList<>();

        Word standard = new Word(br.readLine());

        for (int i = 0; i < N - 1; i++) {
            l.add(new Word(br.readLine()));
        }

        int count = 0;

        for (Word w: l) {
            if(standard.isSimilar(w)) count++;
        }

        System.out.println(count);
    }
}
 