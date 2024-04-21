package problems.boj.solved.no_1111_iqtest;

import java.io.*;
import java.util.*;
import java.util.stream.*;
/**
 * 문제 이름 : IQ Test
 * 링크 : https://www.acmicpc.net/problem/1111
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-20
 * 관련 링크 : 
 */
public class Main {

    private static class Expression {

        private static final int RANGE = 200;

        final int multiplier;
        final int addend;

        private Expression(int multiplier, int addend) {
            this.multiplier = multiplier;
            this.addend = addend;
        }

        public static Expression of(int multiplier, int addend) {
            return new Expression(multiplier, addend);
        }

        public int calculate(int a) {
            return a * multiplier + addend;
        }

        public boolean matches(int a, int b) {
            return calculate(a) == b;
        }

        public static List<Expression> estimate(int[] nums) {

            List<Expression> candidates = new LinkedList<>();

            for(int a = -RANGE; a < RANGE; a++) {
                int b = nums[1] - nums[0] * a;
                candidates.add(Expression.of(a,b));
            }

            return candidates.stream()
                .filter((exp) -> Expression.matches(exp, nums))
                .collect(Collectors.toList());
        }

        public static boolean matches(Expression exp, int... nums) {
            boolean f = true;
            for(int i = 0; i < nums.length - 1; i++) {
                if(!exp.matches(nums[i], nums[i+1])) {
                    f = false;
                    break;
                }
            }

            return f;
        }

        // static class ExpressionCache() {

        // }

        @Override
        public String toString() {
            return String.format("%d * N + %d", multiplier, addend);
        }

    }

    static int N;
    static int[] nums;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        String[] splited = br.readLine().split(" ");
        for(int i = 0; i < splited.length; i++) {
            nums[i] = Integer.parseInt(splited[i]);
        }

        //logic
        if(N == 1) {
            System.out.println("A");
            return;
        } else {
            Set<Integer> res = new HashSet<>();
            List<Expression> estimated = Expression.estimate(nums);

            for(Expression exp : estimated) {
                res.add(exp.calculate(nums[N-1]));
            }
    
            if(res.size() > 1) {
                System.out.println("A");
            } else if (res.size() == 1) {
                System.out.println(estimated.get(0).calculate(nums[N - 1]));
            } else {
                System.out.println("B");
            }
        }
        
    }


}
