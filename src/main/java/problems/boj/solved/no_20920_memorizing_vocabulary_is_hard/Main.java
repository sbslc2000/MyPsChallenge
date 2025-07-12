package problems.boj.solved.no_20920_memorizing_vocabulary_is_hard;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;


/**
 * https://www.acmicpc.net/problem/20920
 */
public class Main {

    static int N, M;

    static class Word implements Comparable<Word>{
        String str;
        int count;
        int length;

        public Word(String str) {
            this.str = str;
            this.count = 0;
            this.length = str.length();
        }

        public int compareTo(Word w) {
            if(this.count < w.count) {
                return 1;
            } else if (this.count == w.count) {
                if (this.length < w.length) {
                    return 1;
                } else if (this.length == w.length) {
                    return this.str.compareTo(w.str);
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] splited;

        splited = br.readLine().split(" ");

        N = Integer.parseInt(splited[0]);
        M = Integer.parseInt(splited[1]);

        Map<String, Word> wordMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String _word = br.readLine();

            if (_word.length() < M) {
                continue;
            }

            Word findWord = wordMap.get(_word);

            if (findWord == null) {
                findWord = new Word(_word);
            }

            findWord.count++;
            wordMap.put(findWord.str, findWord);
        }

        List<Word> words = wordMap.entrySet().stream().map((d) -> {
            return d.getValue();
        }).sorted().collect(Collectors.toList());


        for(Word w : words) {
            bw.write(w.str);
            bw.write("\n");
        }
        bw.flush();
    }
}
