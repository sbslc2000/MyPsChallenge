package problems.boj.solved.no1157_vocabulary;
import java.io.*;

/**
 * 문제 이름 : 단어 공부
 * 링크 : https://www.acmicpc.net/problem/1157
 * 알고리즘 분류 : ?
 * 작성 일시 : 2023-11-17g
 * 관련 링크 :
 */
public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine().toUpperCase();

        int[] count = new int[26];

        for(int i = 0; i < s.length(); i++) 
            count[s.charAt(i) - 65]++;

        int maxCount = -1;
        int maxChar = -1; 
        boolean dup = false;

        for(int i = 0; i< count.length; i++) {
            if(count[i] > maxCount) {
                dup = false;
                maxCount = count[i];
                maxChar = i;
            } else if (count[i] == maxCount) {
                dup = true;
            }
        }

        System.out.printf("%c\n", dup ? '?' : maxChar+65);
    }
}
