package problems.boj.solved.no_2512_budget;

import java.io.*;

public class Main {

    static int N, M;
    static int[] budgetRequests;

    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;

        N = Integer.parseInt(br.readLine());

        budgetRequests = new int[N];

        splited = br.readLine().split(" ");

        for (int i = 0; i < splited.length; i++) {
            budgetRequests[i] = Integer.parseInt(splited[i]);
        }

        M = Integer.parseInt(br.readLine());

        //logic

        int total = 0;
        int max = 0;
        for (int i = 0; i < N; i++) {
            total += budgetRequests[i];
            max = Math.max(max, budgetRequests[i]);
        }

        if (total < M) {
            System.out.println(max);
            return;
        }

        int op = findOptimal(0, max);

        if (calculate(op) > M) {
            System.out.println(op - 1);
        } else {
            System.out.println(op);
        }    
    }

    private static int findOptimal(int start, int end) {
        if (end == start) {
            return start;
        }

        int p = (end + start) / 2;

        int calculatedBudget = calculate(p);

        if (calculatedBudget < M) {
            return findOptimal(p + 1, end);
        } else {
            return findOptimal(start, p);
        }
    }

    private static int calculate(int p) {
        int budget = 0;
        for (int i = 0; i < budgetRequests.length; i++) {
            budget += budgetRequests[i] < p ? budgetRequests[i] : p;
        }

        return budget;
    }
}
