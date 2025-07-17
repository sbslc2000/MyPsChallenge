package problems.boj.solved.no_1515_continuous_numbers;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String numbers = br.readLine();
        int length = numbers.length();
        int p = 0;
        NextGen generator = new NextGen();

        while(p < length) {
            if (generator.next() == numbers.charAt(p)) {
                p++;
            }
        }

        System.out.println(generator.getCurrentNumber());
    }

    static class NextGen {
        Stack<Character> s = new Stack<>();
        int num = 0;
        int p = 0;

        private void push(int n) {
            while(n != 0) {
                int num = n % 10;
                s.push((char)(num + '0'));
                n /= 10;
            }
        }

        char next() {
            if (s.empty()) {
                push(++num);
                return next();
            } else {
                return s.pop();
            }
        }

        int getCurrentNumber() {
            return num;
        }
    }
}
