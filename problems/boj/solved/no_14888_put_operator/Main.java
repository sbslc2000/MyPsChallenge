package problems.boj.solved.no_14888_put_operator;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.io.*;
import java.util.function.*;

/**
 * 문제 이름 : 연산자 끼워넣기
 * 링크 : https://www.acmicpc.net/problem/14888
 * 알고리즘 분류 : ?
 * 작성 일시 : 03-06
 * 관련 링크 : 
 */
public class Main {

    static int N;
    static int[] arr;

    static final int SUM = 0;
    static final int SUB = 1;
    static final int MUL = 2;
    static final int DIV = 3;

    static int[] operators;

    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception {

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        String[] splited = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(splited[i]);
        }

        operators = new int[N-1];

        List<Integer> opNum = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

        int idx = 0;
        for(int i = 0; i < opNum.get(0) ; i++) {
            operators[idx++] = SUM;
        }

        for(int i = 0; i < opNum.get(1) ; i++) {
            operators[idx++] = SUB;
        }

        for(int i = 0; i < opNum.get(2) ; i++) {
            operators[idx++] = MUL;
        }

        for(int i = 0; i < opNum.get(3) ; i++) {
            operators[idx++] = DIV;
        }

        //logic

        bt(new boolean[N-1], new ArrayList<Integer>());

        System.out.println(MAX);
        System.out.println(MIN);
    }


    
    static void bt(boolean[] visited, List<Integer> selectedOps) {
        if(selectedOps.size() == N - 1) {
            int result = calculate(selectedOps);

            MAX = result > MAX ? result : MAX;
            MIN = result < MIN ? result : MIN;
        }

        for(int i = 0; i < N - 1; i++) {
            if(!visited[i]) {
                selectedOps.add(operators[i]);
                visited[i] = true;

                bt(visited, selectedOps);

                selectedOps.remove(selectedOps.size() - 1);
                visited[i] = false;
            }
        }
    }

    static BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
    static BiFunction<Integer, Integer, Integer> sub = (a, b) -> a - b;
    static BiFunction<Integer, Integer, Integer> mul = (a, b) -> a * b;
    static BiFunction<Integer, Integer, Integer> div = (a, b) -> a / b;
    static List<BiFunction<Integer, Integer, Integer>> calcList = List.of(add, sub, mul, div);

    static int calculate(List<Integer> selectedOps) {
        int result = arr[0];
        for(int i = 0; i < N - 1; i++) {
            result = calcList.get(selectedOps.get(i)).apply(result, arr[i+1]);
        }
        return result;
    }

}
