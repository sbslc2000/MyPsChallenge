package problems.boj.no1157_vocabulary;
import java.io.*;

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
